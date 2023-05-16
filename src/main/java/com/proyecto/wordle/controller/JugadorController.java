package com.proyecto.wordle.controller;

import com.proyecto.wordle.dto.JugadorDTO;
import com.proyecto.wordle.dto.JugadorModifDTO;
import com.proyecto.wordle.dto.converter.JugadorConverterDTO;
import com.proyecto.wordle.model.Equipo;
import com.proyecto.wordle.repositories.EquipoRepository;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.repositories.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorRepository jugadorRepository;
    private final JugadorConverterDTO jugadorConverterDTO;
    private final EquipoRepository equipoRepository;

    //Obtener todos los jugadores
    @GetMapping("/jugadores")
    public ResponseEntity<List<?>> getAllJugadores() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        if (jugadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JugadorDTO> DTOJugadorList = jugadores.stream().map(JugadorConverterDTO::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOJugadorList);
        }
    }

    //Obtener un jugador por su id
    @GetMapping("/jugador{id}")
    public ResponseEntity<Object> getJugadorByid(@PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findByid(id);
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.ok(jugadorBuscado);
        } else {
            System.out.println(jugadorBuscado.get().getEquipo());
            return ResponseEntity.ok(jugadorBuscado);
        }
    }

    //Borrar un jugador por su id
    @DeleteMapping("jugador{id}")
    public ResponseEntity<?> deleteJugadorById(@PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findByid(id);
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            jugadorRepository.deleteByid(id);
            return ResponseEntity.noContent().build();
        }
    }

    //Crear un jugador
    @PostMapping("jugador")
    public ResponseEntity<?> createJugador(@RequestBody JugadorModifDTO newJugador) {
        List<Jugador> jugadorExiste = jugadorRepository.findBynombre(newJugador.getnombre);
        if (!jugadorExiste.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Jugador jugadorCreado = new Jugador();
            jugadorCreado.setnombre(newJugador.getNombre());
            jugadorCreado.setadmin(newJugador.getAdmin());
            jugadorCreado.setpuntos(0);
            jugadorCreaado.setavatar("");
            jugadorCreado.setequipo(null);
            jugadorRepository.save(jugadorCreado);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    //Eliminar un jugador por el id
    @DeleteMapping("jugador{id}")
    public ResponseEntity<?> deleteJugador(@PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findByid(id);
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build;
        } else {
            jugadorRepository.delete(jugadorBuscado.get());
            return ResponseEntity.noContent().build();
        }
    }

    //Modificar un jugador por el id
    @PutMapping("jugador{id}")
    public ResponseEntity<?> modifyJugador(@PathVariable Integer id, RequestBody Jugador newJugador) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findByid(id);
        List<Jugador> jugadorExiste = jugadorRepository.findBynombre(newJugador.getNombre());
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!jugadorExiste.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            if (newJugador.getNombre() != null
                    && !newJugador.getNombre().isEmpty()
                    && !newJugador.getNombre().equals(newJugador.get().getNombre())) {
                jugadorBuscado.get().setnombre(newJugador.getNombre());
            }
            if (newJugador.getImagen() != null
                    && !newJugador.getAvatar().equals(jugadorBuscado.get().getavatar())) {
                jugadorBuscado.get().setavatar(newJugador.getAvatar());
            }
            Jugador jugador = jugadorBuscado.get();
            jugadorRepository.save(jugador);
            return ResponseEntity.ok(jugador);
        }
    }
}


