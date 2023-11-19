package com.example.mycevicheriaapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.core.ConexionAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText clienteIdTxt,
            usuariTxt,
            contraUsuario,
            nombresTxt,
            apellidoTxt,
            imagenImg,
            dniTxt,
            correoTxt,
            celularTxt,
            generoTxt;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iniciarRegistrarUsuarioActivity();


    }

    private void iniciarRegistrarUsuarioActivity() {
        clienteIdTxt = findViewById(R.id.txtClieUsuaId);
        usuariTxt = findViewById(R.id.txtUsuarioR);
        contraUsuario = findViewById(R.id.txtContrasenaR);
        nombresTxt = findViewById(R.id.txtNombres);
        apellidoTxt = findViewById(R.id.txtApellidos);
        imagenImg = findViewById(R.id.imgImagen);
        dniTxt = findViewById(R.id.txtDNI);
        correoTxt = findViewById(R.id.txtCorreo);
        celularTxt = findViewById(R.id.numCelular);
        generoTxt = findViewById(R.id.txtGenero);

        // instancioando el objeto
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onPause() {
        // Limpiar los campos
        clienteIdTxt.setText("");
        usuariTxt.setText("");
        contraUsuario.setText("");
        nombresTxt.setText("");
        apellidoTxt.setText("");
        imagenImg.setText("");
        dniTxt.setText("");
        correoTxt.setText("");
        celularTxt.setText("");
        generoTxt.setText("");

        super.onPause();
    }

    public void registrarUsuarioCliente(View view) {
        // Obtener los valores de los campos
        String usuario = usuariTxt.getText().toString().trim();
        String contrasena = contraUsuario.getText().toString().trim();
        String nombres = nombresTxt.getText().toString().trim();
        String apellidos = apellidoTxt.getText().toString().trim();
        String imagen = imagenImg.getText().toString().trim();
        String dni = dniTxt.getText().toString().trim();
        String correo = correoTxt.getText().toString().trim();
        String celular = celularTxt.getText().toString().trim();
        String genero = generoTxt.getText().toString().trim();

        // Verificar si algún campo está vacío
        if (usuario.isEmpty() || contrasena.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() ||
                imagen.isEmpty() || dni.isEmpty() || correo.isEmpty() || celular.isEmpty() || genero.isEmpty()) {
            // Mostrar un mensaje de error si algún campo está vacío
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Todos los campos están llenos, proceder con el registro del usuario
            registrarUsuario();
        }
    }

    // Método para registrar el usuario
    public void registrarUsuario() {
        String url = ConexionAPI.URL_BASE + "usuario";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String usuaId;
                        //Toast.makeText(getApplicationContext(), "registrado correctamente: ", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Obtén el objeto "credenciales" del objeto principal
                            JSONObject credencialesObject = jsonObject.getJSONObject("credenciales");

                            // Una vez registrado el usuario con éxito, proceder con el registro del cliente
                            registrarCliente();
                            Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();

                            Intent pasarMensaje = new Intent(RegisterActivity.this, LoginActivity.class);
                            //activamos el  Intent
                            startActivity(pasarMensaje);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        //System.out.println(error.getMessage());
                    }


                }

        ) {
            //cargando los datos a enviar
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<>();

                parametros.put("usa_tipousa_id", "5");
                parametros.put("usa_usanombre", usuariTxt.getText().toString());
                parametros.put("usa_contra", contraUsuario.getText().toString());

                return parametros;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionAPI.AUTH);
                return params;
            }
        };
        requestQueue.add(request);
    }

    // Método para registrar el cliente
    public void registrarCliente() {
        String url = ConexionAPI.URL_BASE + "cliente";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "registrado correctamente: ", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al registrar cliente", Toast.LENGTH_SHORT).show();
                        //System.out.println(error.getMessage());
                    }
                }
        ) {
            //cargando los datos a enviar
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<>();

                parametros.put("clien_usua_id", clienteIdTxt.getText().toString());
                parametros.put("clien_nombres", nombresTxt.getText().toString());
                parametros.put("clien_apellidos", apellidoTxt.getText().toString());
                parametros.put("clien_foto", imagenImg.getText().toString() + ".jpg");
                parametros.put("clien_dni", dniTxt.getText().toString());
                parametros.put("clie_correo", correoTxt.getText().toString());
                parametros.put("clien_celular", celularTxt.getText().toString());
                parametros.put("clien_genero", generoTxt.getText().toString());

                return parametros;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionAPI.AUTH);
                return params;
            }
        };
        requestQueue.add(request);
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));

    }

}