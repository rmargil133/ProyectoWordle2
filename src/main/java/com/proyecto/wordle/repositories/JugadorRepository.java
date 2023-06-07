package com.proyecto.wordle.repositories;
import com.proyecto.wordle.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
    List<Jugador> findByNombre(String nombre);
}
