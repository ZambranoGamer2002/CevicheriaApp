package com.example.mycevicheriaapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.core.ConexionAPI;
import com.example.mycevicheriaapp.data.model.HomeHorModel;
import com.example.mycevicheriaapp.data.model.HomeVerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;

    private static final String KEY_TOTAL_REGISTROS = "Total de registros";
    private static final String KEY_DETALLES = "Detalles";

    private ArrayList<HomeHorModel> listaCompleta;

    private RequestQueue requestQueue;
    ArrayList<HomeHorModel> list;

    public interface OnItemClickLister {
        void OnItemClick(String Item);

        void onItemClick(String name);
    }

    int row_index = -1;

    OnItemClickLister listener;


    /*public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list, OnItemClickLister listener) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
        this.listaCompleta = new ArrayList<>(list); // Crear una copia de la lista original
        this.listener = listener;
        this.requestQueue = Volley.newRequestQueue(activity);
    }*/
    List<HomeHorModel> homeHorModelList;
    public HomeHorAdapter(List<HomeHorModel> homeHorModelList){
        this.homeHorModelList = homeHorModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_platos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(homeHorModelList.get(position).getImage());
        holder.name.setText(homeHorModelList.get(position).getName());
        // Obtén el elemento actual de la lista
        HomeHorModel selectedItem = homeHorModelList.get(position);

        // Lógica de selección de fondo
        /*if (row_index == position) {
            holder.cardView.setBackgroundResource(R.drawable.seleccioncolor_bg);
        } else {
            holder.cardView.setBackgroundResource(R.drawable.default_bg);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                listener.onItemClick(selectedItem.getName());

                // Lógica para determinar qué tipo de plato fue seleccionado
                if (selectedItem.getName().equals("Ceviches") ||
                        selectedItem.getName().equals("Combos") ||
                        selectedItem.getName().equals("Menus") ||
                        selectedItem.getName().equals("Bebidas")) {

                    Log.d("HomeCevicheAdapter", "Tipo de Plato seleccionado: " + selectedItem.getName());
                    cargarPlatosDesdeBaseDeDatos(selectedItem.getName());
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return homeHorModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.platosCevicheIMG);
            name = itemView.findViewById(R.id.platosCevicheTXT);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    // Método para cargar los platos desde la base de datos según el tipo
    private void cargarPlatosDesdeBaseDeDatos(String tipoPlato) {
        String url = Uri.parse(ConexionAPI.URL_BASE + "/plato").buildUpon().build().toString();
        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int totalRegistros = response.getInt("Total de registros");

                            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                            JSONArray detalleArray = response.getJSONArray("Detalle");

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
                                    homeVerModels.add(platos);
                                }
                            }

                            // Llama al método de la interfaz para actualizar el RecyclerView
                            int position = 0;
                            updateVerticalRec.callBack(position, homeVerModels);

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

}