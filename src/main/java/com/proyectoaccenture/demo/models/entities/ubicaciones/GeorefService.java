package com.proyectoaccenture.demo.models.entities.ubicaciones;

import com.proyectoaccenture.demo.models.repositories.LocalidadRepository;
import com.proyectoaccenture.demo.models.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class GeorefService{

    private final IRetrofit georefApi;
    @Autowired
    private ProvinciaRepository provinciaRepository;
    @Autowired
    private LocalidadRepository localidadRepository;


    public GeorefService() {
        georefApi = GeorefAPIClient.getClient().create(IRetrofit.class);
    }

    public void cargarProvinciasYLocalidades() throws IOException {
        Call<ProvinciasResponse> requestProvinciasArgentinas = georefApi.obtenerProvincias();
        Response<ProvinciasResponse> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();

        List<Provincia> provincias = responseProvinciasArgentinas.body().getProvincias();

        for (Provincia provincia : provincias) {
            Provincia provinciaAIterar = new Provincia();
            provinciaAIterar.setNombre(provincia.getNombre());
            provinciaRepository.save(provinciaAIterar);

            Call<LocalidadResponse> requestLocalidades = georefApi.obtenerLocalidades(provincia.getNombre(), 1);
            Response<LocalidadResponse> responseLocalidades = requestLocalidades.execute();

            List<Localidad> localidades = responseLocalidades.body().getLocalidades();

            for (Localidad localidad : localidades) {
                Provincia provinciaGuardada = provinciaRepository.findByNombre(provincia.getNombre());
                Localidad localidadAIterar = new Localidad();
                localidadAIterar.setNombre(localidad.getNombre());
                localidadAIterar.setProvincia(provinciaGuardada);
                localidadRepository.save(localidadAIterar);
            }
        }

    }
    public List<Provincia> obtenerProvincias() {
        return this.provinciaRepository.findAll();
    }
    public List<Localidad> obtenerLocalidades(){
        return this.localidadRepository.findAll();
    }

}