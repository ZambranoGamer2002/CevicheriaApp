package com.example.mycevicheriaapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.activities.EleccionMesaActivity;
import com.example.mycevicheriaapp.adapters.HomeHorAdapter;
import com.example.mycevicheriaapp.adapters.HomeVerAdapter;
import com.example.mycevicheriaapp.adapters.PlatoAdapterRecycler;
import com.example.mycevicheriaapp.adapters.UpdateVerticalRec;
import com.example.mycevicheriaapp.core.ConexionAPI;
import com.example.mycevicheriaapp.data.model.Platos;
import com.example.mycevicheriaapp.databinding.FragmentHomeBinding;
import com.example.mycevicheriaapp.data.model.HomeHorModel;
import com.example.mycevicheriaapp.data.model.HomeVerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements UpdateVerticalRec {


    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    TextView txtUser;
    RequestQueue requestQueue;
    private Button pasarVistaReserva;
    private String tipoCevicheSeleccionado = "Ceviches"; // Tipo predeterminado
    private HomeVerAdapter homeVerAdapter;
    private PlatoAdapterRecycler platoAdapterRecycler;

    //Vertical
    ArrayList<HomeVerModel> homeVerModelList = new ArrayList<>();

    private List<Platos> listaPlatos;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        txtUser = root.findViewById(R.id.tvUserLogin);

        //pasarVistaDeLaReserva(root);
        iniciarRecyclerViewProductos(root);
        cargarPlatosDesdeBaseDeDatos("5");
        recyclerViewTypeCeviche(root);
        //Ceviche RecuyclerView
        //recyclerViewTypeCeviche();
        loadData();

        return root;
    }

    private void iniciarRecyclerViewProductos(View rootView) {

        //listaFiltrada = new ArrayList<>();

        listaPlatos = new ArrayList<>();

        homeVerticalRec = rootView.findViewById(R.id.home_ver_rec);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        platoAdapterRecycler = new PlatoAdapterRecycler(listaPlatos);
        homeVerticalRec.setAdapter(platoAdapterRecycler);
        cargarListaPlatos();

    }
    private void cargarListaPlatos() {
        requestQueue = Volley.newRequestQueue(requireContext());

        String url = Uri.parse(ConexionAPI.URL_BASE + "plato")
                .buildUpon()
                .build().toString();
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            int totalRegistros = response.getInt("Total de registros");

                            for (int i = 0; i < totalRegistros; i++) {

                                String valor = response.get("Detalles").toString();
                                JSONArray arreglo = new JSONArray(valor);
                                JSONObject objeto = new JSONObject(arreglo.get(i).toString());

                                String platoId = objeto.getString("plato_id");
                                String platiTipoPlatoId = objeto.getString("plato_tipoplato_id");
                                String platoNombre = objeto.getString("plato_nombre");
                                String pedidoDescrip = objeto.getString("pedido_descrip");
                                String platoPrecio = objeto.getString("plato_precio");
                                String platoFoto = objeto.getString("plato_foto");
                                String getPlatiTipoPlatoId = objeto.getString("tipoplato_id");
                                String tipoPlatoNombre = objeto.getString("tipoplato_nombre");

                                // Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

                                Platos plato = new Platos(platoId, platiTipoPlatoId, platoNombre, pedidoDescrip, platoPrecio, platoFoto, getPlatiTipoPlatoId,tipoPlatoNombre);
                                ///String tipoEspecifico = "1";
                                //if (producto.getProdId().equals(tipoEspecifico)) {
                                listaPlatos.add(plato);
                                //
                                platoAdapterRecycler.notifyItemRangeInserted(listaPlatos.size(),1);
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionAPI.AUTH);
                return params;
            }
        };

        requestQueue.add(requerimiento);
    }


    private void cargarPlatosDesdeBaseDeDatos(String tipoPlato) {
        String url = Uri.parse(ConexionAPI.URL_BASE + "plato").buildUpon().build().toString();
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int totalRegistros = response.getInt("Total de registros");

                            JSONArray detalleArray = response.getJSONArray("Detalles");
                            Log.d("MiTag", response.getString("Detalles"));
                            for (int i = 0; i < totalRegistros; i++) {
                                JSONObject objeto = detalleArray.getJSONObject(i);

                                String platotipoId = objeto.getString("tipoplato_id");

                                if (platotipoId.equals(tipoPlato)) {
                                    String platoid = objeto.getString("plato_id");
                                    String plato = objeto.getString("plato_nombre");
                                    String descripcion = objeto.getString("pedido_descrip");
                                    String precio = objeto.getString("plato_precio");
                                    String imageView = objeto.getString("plato_foto");

                                    HomeVerModel platos = new HomeVerModel(platoid, plato, descripcion, precio, imageView);
                                    homeVerModelList.add(platos);
                                }
                            }

                            // Llama al método de la interfaz para actualizar el RecyclerView
                            int position = 0;
                        } catch (Exception e) {
                            Log.e("HomeCevicheAdapter", "Error parsing JSON: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("HomeCevicheAdapter", "Volley Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ConexionAPI.AUTH);
                return headers;
            }
        };

        requestQueue.add(requerimiento);
    }

    public void recyclerViewTypeCeviche(View rootView) {
        homeHorModelList = new ArrayList<>();

        //Colocar la respectiva carta a elegir
        homeHorModelList.add(new HomeHorModel(R.drawable.ceviches_img, "Ceviches"));
        homeHorModelList.add(new HomeHorModel(R.drawable.combos_img, "Combos"));
        homeHorModelList.add(new HomeHorModel(R.drawable.menus_img, "Menus"));
        homeHorModelList.add(new HomeHorModel(R.drawable.bebidas_img, "Bebidas"));

        homeHorizontalRec = rootView.findViewById(R.id.home_ceviches_rec);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        homeHorAdapter = new HomeHorAdapter(homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);



        /*homeHorAdapter = new HomeHorAdapter(this, getActivity(), homeHorModelList, new HomeHorAdapter.OnItemClickLister() {
            @Override
            public void OnItemClick(String Item) {
                onItemSelected(Item);
                cargarPlatosDesdeBaseDeDatos("Ceviches");
                homeVerAdapter.updateList(homeVerModelList);
            }

            @Override
            public void onItemClick(String name) {

            }
        });*/

        //homeHorizontalRec.setAdapter(homeHorAdapter);
        //homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

    }

    private void onItemSelected(String item) {
        Toast.makeText(requireActivity(), item, Toast.LENGTH_SHORT).show();
        tipoCevicheSeleccionado = item;
        homeVerAdapter.filtrar(item);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MisDatosUsuario", Context.MODE_PRIVATE);
        txtUser.setText(sharedPreferences.getString("usuario", ""));
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }

}