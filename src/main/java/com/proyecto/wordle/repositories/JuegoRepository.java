package com.proyecto.wordle.repositories;

import com.proyecto.wordle.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepository extends JpaRepository<Juego, Integer> {
    List<Juego> findByNombre(String nombre);
}