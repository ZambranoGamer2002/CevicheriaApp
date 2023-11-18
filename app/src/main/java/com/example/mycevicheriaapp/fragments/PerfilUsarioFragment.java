package com.example.mycevicheriaapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mycevicheriaapp.R;

public class PerfilUsarioFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private TextView txtNombres, txtApellidos, txtDNI, numCelular, txtGenero, txtClieUsuaId;
    private Button btnEditarPerfil;

    public PerfilUsarioFragment() {



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pefil_usario, container, false);

        // Obtener la instancia de SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);

        // Cargar usua_id desde SharedPreferences
        String usuaId = sharedPreferences.getString("usua_id", "");

        // Obtén los datos del usuario desde SharedPreferences
        txtNombres = view.findViewById(R.id.txtNombres);
        txtApellidos = view.findViewById(R.id.txtApellidos);
        txtDNI = view.findViewById(R.id.txtDNI);
        numCelular = view.findViewById(R.id.numCelular);
        txtGenero = view.findViewById(R.id.txtGenero);
        txtClieUsuaId = view.findViewById(R.id.txtClieUsuaId);
        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfil);

        // Mostrar datos del usuario en las vistas
        mostrarDatosUsuario();

        // Configurar el botón de editar perfil (si es necesario)
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para editar perfil
                // Puedes abrir una nueva actividad o fragmento para la edición del perfil
            }
        });

        return view;
    }

    private void mostrarDatosUsuario() {
        // Obtener datos del SharedPreferences
        String nombres = sharedPreferences.getString("nombres", "");
        String apellidos = sharedPreferences.getString("apellidos", "");
        String dni = sharedPreferences.getString("dni", "");
        String celular = sharedPreferences.getString("celular", "");
        String genero = sharedPreferences.getString("genero", "");
        String clieUsuaId = sharedPreferences.getString("clieUsuaId", "");

        // Log para verificar los datos obtenidos desde SharedPreferences
        Log.d("PerfilUsarioFragment", "Nombres: " + nombres);
        Log.d("PerfilUsarioFragment", "Apellidos: " + apellidos);
        Log.d("PerfilUsarioFragment", "DNI: " + dni);
        Log.d("PerfilUsarioFragment", "Celular: " + celular);
        Log.d("PerfilUsarioFragment", "Género: " + genero);
        Log.d("PerfilUsarioFragment", "clieUsuaId: " + clieUsuaId);

        // Mostrar datos en las vistas solo si no son nulos o vacíos
        if (!nombres.isEmpty()) {
            txtNombres.setText(nombres);
        }

        if (!apellidos.isEmpty()) {
            txtApellidos.setText(apellidos);
        }

        if (!dni.isEmpty()) {
            txtDNI.setText(dni);
        }

        if (!celular.isEmpty()) {
            numCelular.setText(celular);
        }

        if (!genero.isEmpty()) {
            txtGenero.setText(genero);
        }

        if (!clieUsuaId.isEmpty()) {
            txtClieUsuaId.setText(clieUsuaId);
        }
    }
}