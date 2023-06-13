package com.proyecto.wordle.repositories;

import com.proyecto.wordle.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
    List<Partida> findByJugador_idJugador(Integer jugador_idJugador);
}
