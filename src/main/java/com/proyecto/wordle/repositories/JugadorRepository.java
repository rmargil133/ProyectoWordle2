package com.proyecto.wordle.repositories;
import com.proyecto.wordle.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
    List<Jugador> findByNombre(String nombre);

    Optional<Jugador> findById(Integer id);
}
