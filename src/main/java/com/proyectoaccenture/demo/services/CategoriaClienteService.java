package com.proyectoaccenture.demo.services;

import com.proyectoaccenture.demo.exceptions.NotFoundException;
import com.proyectoaccenture.demo.models.dtos.CategoriaClienteInputDTO;
import com.proyectoaccenture.demo.models.dtos.CategoriaClienteOutputDTO;
import com.proyectoaccenture.demo.models.entities.clientes.CategoriaCliente;
import com.proyectoaccenture.demo.models.repositories.CategoriaClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaClienteService {
    private final CategoriaClienteRepository categoriaClienteRepository;

    public CategoriaClienteService(CategoriaClienteRepository categoriaClienteRepository) {
        this.categoriaClienteRepository = categoriaClienteRepository;
    }

    public List<CategoriaClienteOutputDTO> obtenerTodasLasCategorias() {
        return categoriaClienteRepository.findAll().stream().map(CategoriaClienteOutputDTO::new).toList();
    }

    public CategoriaClienteOutputDTO crearCategoria(CategoriaClienteInputDTO categoriaInput) {

        CategoriaCliente siguiente = null;

        if (categoriaInput.getSiguienteCategoria_id() != null) {
            siguiente = this.categoriaClienteRepository.findById(categoriaInput.getSiguienteCategoria_id())
                    .orElse(null);
        }

        var categoriaCliente = CategoriaCliente
                .builder()
                .nivel(categoriaInput.getNivel())
                .factorPuntosVenta(categoriaInput.getFactorPuntosVenta())
                .umbralMinRequerido(categoriaInput.getUmbralMinRequerido())
                .puntosParaSiguiente(categoriaInput.getPuntosParaSiguiente())
                .siguienteCategoria(siguiente)
                .build();


        this.categoriaClienteRepository.save(categoriaCliente);

        return new CategoriaClienteOutputDTO(categoriaCliente);
    }


    //todo Aca actualiza con la categoria siguiente
    public CategoriaCliente actualizarCategoria(Long id, CategoriaCliente categoriaActualizada) {
        CategoriaCliente categoriaExistente = categoriaClienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));

        categoriaExistente.setNivel(categoriaActualizada.getNivel());
        categoriaExistente.setUmbralMinRequerido(categoriaActualizada.getUmbralMinRequerido());
        categoriaExistente.setPuntosParaSiguiente(categoriaActualizada.getPuntosParaSiguiente());
        categoriaExistente.setFactorPuntosVenta(categoriaActualizada.getFactorPuntosVenta());
        categoriaExistente.setSiguienteCategoria(categoriaActualizada.getSiguienteCategoria());


        return categoriaClienteRepository.save(categoriaExistente);
    }

    public void eliminarCategoria(Long id) {
        CategoriaCliente categoria = categoriaClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoriaClienteRepository.delete(categoria);
    }
}