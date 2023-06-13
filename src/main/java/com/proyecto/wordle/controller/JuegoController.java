package com.proyecto.wordle.controller;

import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.repositories.EquipoRepository;
import com.proyecto.wordle.repositories.JugadorRepository;
import com.proyecto.wordle.repositories.PartidaRepository;
import com.proyecto.wordle.repositories.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
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

    @GetMapping("/juego/{nombre}")
    public ResponseEntity<Object> getJuego(@PathVariable String nombre){
        List<Juego> juegoBuscado = juegoRepository.findByNombre(nombre);
        if(juegoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(juegoBuscado);
        }
    }
}
