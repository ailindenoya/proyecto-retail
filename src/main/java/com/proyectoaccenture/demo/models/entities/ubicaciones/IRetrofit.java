package com.proyectoaccenture.demo.models.entities.ubicaciones;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IRetrofit {

    // Endpoint para obtener las provincias
    // Endpoint para obtener las localidades por provincia
    @GET("provincias")
    Call<ProvinciasResponse> obtenerProvincias();

    @GET("localidades")
    Call<LocalidadResponse> obtenerLocalidades(@Query("provincia") String provincia, @Query("max") int max);


}