package com.example.mycevicheriaapp.conexion;

import com.example.mycevicheriaapp.models.HomeVerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DatabaseService {
    @GET("platos/tipo/{tipoId}")
    Call<List<HomeVerModel>> getPlatosByTipoId(@Path("tipoId") String tipoId);
}
