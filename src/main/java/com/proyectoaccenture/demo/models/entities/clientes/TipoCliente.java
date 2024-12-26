package com.proyectoaccenture.demo.models.entities.clientes;

public enum TipoCliente {
    PARTICULAR,
    ORGANIZACION;

    public static TipoCliente fromString(String tipo) {
        try {
            return TipoCliente.valueOf(tipo.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de cliente inválido: " + tipo);
        }
    }
}
