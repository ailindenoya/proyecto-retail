package com.proyectoaccenture.demo.models.entities.ubicaciones;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeorefAPIClient {

    private static Retrofit retrofit;

    //@Value("georef.api.base-url")
    private static final String BASE_URL = "https://apis.datos.gob.ar/georef/api/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
