package com.proyectoaccenture.demo.externals;

import com.proyectoaccenture.demo.models.entities.clientes.Cliente;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class VentaOutputDTO {

    private Long ventaId;
    private BigDecimal precioTotalDeCompra;
    private UUID clienteId;

}