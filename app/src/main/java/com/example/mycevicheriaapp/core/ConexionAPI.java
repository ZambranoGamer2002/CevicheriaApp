package com.example.mycevicheriaapp.core;

import android.util.Base64;

public class ConexionAPI {
    public static final String URL_BASE = "https://cevicherias.informaticapp.com/";
    public static String AUTH = "Basic YXNkYXNkYXNkYXNkOmFzZGFzZGFzZGFzZGFz";

    public static String desencriptarAuth(String username, String password) {
        String creds = String.format("%s:%s", username, password);
        String authConEspacio = Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
        String AuthSinEspacios = authConEspacio.replaceAll("\\s+", "");
        return "Basic " + AuthSinEspacios;
    }
}
