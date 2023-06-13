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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    //Obtener todos los jugadores
    @GetMapping("/jugador")
    public ResponseEntity<List<?>> getAllJugadores() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        if (jugadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JugadorDTO> jugadoresDTO = jugadores.stream()
                    .map(JugadorConverterDTO::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(jugadoresDTO);
        }
    }

    //Obtener un jugador por su id
    @GetMapping("/jugador/{id}")
    public ResponseEntity<Object> getJugadorByid(@PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findById(id);
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(jugadorBuscado.get().getEquipo());
            return ResponseEntity.ok(jugadorBuscado.get());
        }
    }

    //Borrar un jugador por su id
    @DeleteMapping("jugador/{id}")
    public ResponseEntity<?> deleteJugadorById(@PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findById(id);
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            jugadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    //Crear un jugador
    @PostMapping("/jugador")
    public ResponseEntity<?> createJugador(@RequestBody JugadorModifDTO newJugador) {
        List<Jugador> jugadorExiste = jugadorRepository.findByNombre(newJugador.getNombre());
        if (!jugadorExiste.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Jugador jugadorCreado = new Jugador();
            jugadorCreado.setNombre(newJugador.getNombre());
            jugadorCreado.setAdmin(newJugador.getAdmin());
            jugadorCreado.setPuntos(newJugador.getPuntos());
            jugadorCreado.setAvatar(newJugador.getAvatar());
            // Obtener la lista de equipos por su ID
            List<Equipo> equipos = equipoRepository.findById(newJugador.getEquipo_idequipo());
            if (equipos.isEmpty()) {
                // Manejar el caso en el que no se encuentre el equipo
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El jugador ya existe");
            }

            Equipo equipo = equipos.get(0);
            jugadorCreado.setEquipo(equipo);

            jugadorRepository.save(jugadorCreado);
            return ResponseEntity.status(HttpStatus.CREATED).body("Jugador creado exitosamente.");
        }
    }

    //Modificar un jugador por el id
    @PutMapping("/jugador/{id}")
    public ResponseEntity<?> modifyJugador(@RequestBody JugadorModifDTO newJugador, @PathVariable Integer id) {
        Optional<Jugador> jugadorBuscado = jugadorRepository.findById(id);
        List<Jugador> jugadorExiste = jugadorRepository.findByNombre(newJugador.getNombre());
        if (jugadorBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!jugadorExiste.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            if (newJugador.getNombre() != null
                    && !newJugador.getNombre().isEmpty()) {
                jugadorBuscado.get().setNombre(newJugador.getNombre());
            }
            if (newJugador.getAvatar() != null
                    && !newJugador.getAvatar().equals(jugadorBuscado.get().getAvatar())) {
                jugadorBuscado.get().setAvatar(newJugador.getAvatar());
            }
            if (newJugador.getPuntos() != null
                    && !newJugador.getPuntos().equals(jugadorBuscado.get().getAvatar())){
                jugadorBuscado.get().setPuntos(newJugador.getPuntos());
            }
            Jugador jugador = jugadorBuscado.get();
            jugadorRepository.save(jugador);
            return ResponseEntity.ok(jugador);
        }
    }
}


