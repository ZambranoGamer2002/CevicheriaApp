package com.example.mycevicheriaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.adapters.HomeMesaAdapter;
import com.example.mycevicheriaapp.core.ConexionAPI;
import com.example.mycevicheriaapp.data.model.HomeMesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EleccionMesaActivity extends AppCompatActivity {
    private List<HomeMesas> mesas;
    private RequestQueue requestQueue;
    private RecyclerView mesaRecycler;
    private HomeMesaAdapter homeMesaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_mesa);

        iniciarActivity();
        cargarMesas();
    }

    private void iniciarActivity(){
        mesas = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        mesaRecycler = findViewById(R.id.recyclerMesa);

        mesaRecycler.setLayoutManager(new LinearLayoutManager(this));
        homeMesaAdapter = new HomeMesaAdapter(this, mesas);
        mesaRecycler.setAdapter(homeMesaAdapter);
    }

    private void cargarMesas() {
        String url = Uri.parse(ConexionAPI.URL_BASE + "/mesa")
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

                                String mesaId = objeto.getString("mesa_id");
                                String mesaRestId = objeto.getString("mesa_resta_id");
                                String mesaNumero = objeto.getString("mesa_numero");
                                String mesaCantidadPersonas = objeto.getString("mesa_cantidad_asientos");
                                String mesaEstado = objeto.getString("mesa_estado");

                                HomeMesas mesa = new HomeMesas(mesaId, mesaRestId, mesaNumero, mesaCantidadPersonas, mesaEstado);

                                mesas.add(mesa);
                                homeMesaAdapter.notifyItemRangeInserted(mesas.size(), 1);
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
}