package com.example.mycevicheriaapp.data.remote;

import com.example.mycevicheriaapp.data.model.HomeVerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DatabaseService {
    @GET("platos/tipo/{tipoId}")
    Call<List<HomeVerModel>> getPlatosByTipoId(@Path("tipoId") String tipoId);
}
