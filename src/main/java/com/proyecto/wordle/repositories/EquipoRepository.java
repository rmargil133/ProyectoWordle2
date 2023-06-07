package com.proyecto.wordle.repositories;

import com.proyecto.wordle.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EquipoRepository extends JpaRepository<Equipo, Long>{
    List<Equipo> findByNombre(String nombre);

    List<Equipo> findById(Integer equipo);
}
