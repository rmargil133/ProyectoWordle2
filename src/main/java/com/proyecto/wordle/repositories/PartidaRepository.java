package com.proyecto.wordle.repositories;

import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
    Optional<Partida> findByIdPartida(Integer idpartida);


}
