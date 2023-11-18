package com.example.mycevicheriaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycevicheriaapp.conexion.ConexionAPI;
import com.example.mycevicheriaapp.MainActivity;
import com.example.mycevicheriaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtPass;
    private Context context;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarLoginActivity();
    }

    private void iniciarLoginActivity(){
        txtUsername = findViewById (R.id.txtUsuario);
        txtPass = findViewById(R.id.txtContra);

        context = getApplicationContext();

        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    protected void onPause() {
        // Limpiar los campos
        txtUsername.setText("");
        txtPass.setText("");

        super.onPause();
    }
    public void iniciarSesion(View vista) {

        String usuario = txtUsername.getText().toString().trim();
        String contra = txtPass.getText().toString().trim();

        String url = ConexionAPI.URL_BASE + "usuario/" + usuario + "&" + contra;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String valor = response.get("Detalles").toString();
                            JSONArray arreglo = new JSONArray(valor);
                            JSONObject objeto = new JSONObject(arreglo.get(0).toString());

                            String usuarioBd = objeto.getString("usa_usanombre");
                            guardarDatosUsuario(usuarioBd);

                            //A donde quiero que se vaya
                            Intent pasarMainActivity = new Intent(context, MainActivity.class);
                            //activamos el  Intent
                            startActivity(pasarMainActivity);

                            Toast.makeText(getApplicationContext(), "Bienvenido " + usuarioBd, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error en la red", Toast.LENGTH_SHORT).show();
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);
    }

    private void guardarDatosUsuario(String usuarioBd) {
        // Obtener la instancia de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Guardar el nombre de usuario en SharedPreferences
        editor.putString("usuario", usuarioBd);

        // Puedes guardar más datos del usuario aquí según tus necesidades

        editor.apply();  // Aplicar cambios
    }


    public void pasarRegister(View view) {

        startActivity(new Intent(this, RegisterActivity.class ));

    }
}