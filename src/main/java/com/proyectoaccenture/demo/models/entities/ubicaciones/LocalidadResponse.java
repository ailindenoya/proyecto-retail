package com.proyectoaccenture.demo.models.entities.ubicaciones;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
@Setter
@Getter
public class LocalidadResponse {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Localidad> localidades;

    public Optional<Localidad> LocalidadId(int id){
        return this.localidades.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    private class Parametro {
        public List<String> campos;
    }
}
