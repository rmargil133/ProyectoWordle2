package com.proyecto.wordle.repositories;

import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JuegoRepository extends JpaRepository<Juego, Integer> {
    List<Juego> findByNombre(String nombre);

    Optional<Juego> findByIdJuego(Integer id);
}