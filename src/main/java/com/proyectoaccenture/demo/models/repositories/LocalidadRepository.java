package com.proyectoaccenture.demo.models.repositories;

import com.proyectoaccenture.demo.models.entities.ubicaciones.Localidad;
import com.proyectoaccenture.demo.models.entities.ubicaciones.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

        List<Localidad> findByProvincia(Provincia provincia);

}
