package com.proyectoaccenture.demo.models.entities.clientes;

import com.proyectoaccenture.demo.models.entities.Persistente;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Domicilio;
import com.proyectoaccenture.demo.models.entities.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Cliente extends Persistente {

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio direccion;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(name = "puntajeActual")
    private Double puntajeActual;

    @Column(name = "dni")
    private String dni;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<CategoriaClienteHistorico> historicoCategorias;

    @ManyToOne
    @JoinColumn(name = "categoria_actual_id")
    private CategoriaCliente categoriaActual;

}