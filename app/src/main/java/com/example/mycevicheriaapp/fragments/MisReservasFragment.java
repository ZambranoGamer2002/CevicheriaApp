package com.example.mycevicheriaapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.mycevicheriaapp.activities.LoginActivity;
import com.example.mycevicheriaapp.adapters.ReservaAdapter;
import com.example.mycevicheriaapp.core.ConexionAPI;
import com.example.mycevicheriaapp.data.model.ReservaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MisReservasFragment extends Fragment {

    private List<ReservaModel> reservaModels;
    private RequestQueue requestQueue;
    private RecyclerView reservaRecycler;
    private ReservaAdapter reservaAdapter;

    private String tipoFiltrado = "";

    public MisReservasFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mis_reservas, container, false);
        iniciarActivity(rootView);
        filtrar("1");
        return rootView;
    }
    private void iniciarActivity(View rootView){
        reservaModels = new ArrayList<>();
        reservaRecycler = rootView.findViewById(R.id.recyclerMisReservas);
        reservaRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        reservaAdapter = new ReservaAdapter(getActivity(),reservaModels);
        reservaRecycler.setAdapter(reservaAdapter);
        cargarListaReserva();
    }
    public void filtrar(String filtrado){
        tipoFiltrado = filtrado; // Cambiar a tu tipo específico de producto
        // Filtrar la lista y actualizar el RecyclerView
        filtrarProductosPorTipo(filtrado);
    }
    private void filtrarProductosPorTipo(String tipo) {
        ArrayList<ReservaModel> reservaFiltrado = new ArrayList<>();
        for (ReservaModel reservaModel : reservaModels) {
            if (reservaModel.getReseId().equals(tipo)) {
                reservaFiltrado.add(reservaModel);
            }
        }
        reservaAdapter.filtrar(reservaFiltrado);
    }
    private void cargarListaReserva() {
        requestQueue = Volley.newRequestQueue(requireContext());
        SharedPreferences preferences = requireContext().getSharedPreferences("MisDatosUsuario", Context.MODE_PRIVATE);
        String clieIdRecibido = preferences.getString("clienIdPf", "No existe la información");
        if(!clieIdRecibido.equals("No existe la información")){

            String url = Uri.parse(ConexionAPI.URL_BASE + "/ReservaFiltrada/" + clieIdRecibido)
                    .buildUpon()
                    .build().toString();

            JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                    url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String Status = response.getString("Status");
                                if(Status.equals("200")){
                                    int totalRegistros = response.getInt("Total de registros");

                                    for (int i = 0; i < totalRegistros; i++) {

                                        String valor = response.get("Detalles").toString();
                                        JSONArray arreglo = new JSONArray(valor);
                                        JSONObject objeto = new JSONObject(arreglo.get(i).toString());

                                        String reseId = objeto.getString("reserva_id");
                                        String reseMesaId = objeto.getString("reserva_mesa_id");
                                        String reseClienId = objeto.getString("reserva_clien_id");
                                        String reseNumPerso = objeto.getString("reserva_asiento");
                                        String reseFecha = objeto.getString("reserva_fecha");
                                        String reseHora = objeto.getString("reserva_hora");
                                        String mesaNumeroCell = objeto.getString("reserva_numero_cell");

                                        // Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

                                        ReservaModel reservaModel = new ReservaModel(reseId, reseMesaId, reseClienId, reseNumPerso, reseFecha, reseHora, mesaNumeroCell);

                                        reservaModels.add(reservaModel);
                                        reservaAdapter.notifyItemRangeInserted(reservaModels.size(), 1);
                                    }
                                }else if(Status.equals("404")){
                                    Toast.makeText(requireContext(), "No tienes reservas", Toast.LENGTH_SHORT).show();
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
        }else{
            Toast.makeText(requireContext(), "Debes iniciar sesión", Toast.LENGTH_SHORT).show();

            Intent pasarMensaje = new Intent(requireContext(), LoginActivity.class);
            //activamos el  Intent
            startActivity(pasarMensaje);
        }
    }
}