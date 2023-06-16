package com.proyecto.wordle.controller;

import com.proyecto.wordle.model.Equipo;
import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.repositories.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoRepository juegoRepository;

    @GetMapping("/juego")
    public ResponseEntity<Object> getAllJuegos(){
        List<Juego> juegos = juegoRepository.findAll();
        if(juegos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(juegos);
        }
    }

    @GetMapping("/juego/{id}")
    public ResponseEntity<Object> getJuego(@PathVariable Integer id){
        Optional<Juego> juegoBuscado = juegoRepository.findByIdJuego(id);
        if(juegoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(juegoBuscado);
        }
    }

    @DeleteMapping("juego/{id}")
    public ResponseEntity<?> deleteJuegoById(@PathVariable Integer id) {
        Optional<Juego> juegoBuscado = juegoRepository.findByIdJuego(id);
        if (juegoBuscado.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            juegoRepository.deleteById(id);
            String mensaje = "El juego con ID " + id + " ha sido borrado exitosamente.";
            return ResponseEntity.ok(mensaje);
        }
    }
}





