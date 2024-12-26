package com.proyectoaccenture.demo.models.entities.ubicaciones;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
@Setter
@Getter
public class ProvinciasResponse {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Provincia> provincias;

    public Optional<Provincia> provinciaDeId(int id){
        return this.provincias.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    private class Parametro {
        public List<String> campos;
    }
}
