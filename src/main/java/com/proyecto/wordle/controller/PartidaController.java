package com.proyecto.wordle.controller;

import com.proyecto.wordle.dto.PartidaDTO;
import com.proyecto.wordle.dto.PartidaModifDTO;
import com.proyecto.wordle.dto.converter.PartidaConverterDTO;
import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.model.Partida;
import com.proyecto.wordle.repositories.JuegoRepository;
import com.proyecto.wordle.repositories.JugadorRepository;
import com.proyecto.wordle.repositories.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaConverterDTO partidaConverterDTO;
    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final JuegoRepository juegoRepository;

    //Obtener todas las partidas
    @GetMapping("/partida")
    public ResponseEntity<List<?>> getAllPartidas(){
        List<Partida> partidas = partidaRepository.findAll();
        if(partidas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<PartidaDTO> DTOPartidaList = partidas.stream().map(partidaConverterDTO::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    //Obtener una partida por su id
    @GetMapping("/partida/{id}")
    public ResponseEntity<List<?>> getPartidaById(@PathVariable Integer id){
        System.out.println(id);
        Optional<Partida> partidaBuscada = partidaRepository.findByIdPartida(id);
        if(partidaBuscada.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<PartidaDTO> DTOPartidaList = partidaBuscada.stream().map(partidaConverterDTO::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    @DeleteMapping("partida/{id}")
    public ResponseEntity<?> deletePartidaById(@PathVariable Integer id) {
        Optional<Partida> partidaBuscada = partidaRepository.findByIdPartida(id);
        if (partidaBuscada.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            jugadorRepository.deleteById(id);
            String mensaje = "La partida con ID " + id + " ha sido borrada exitosamente.";
            return ResponseEntity.ok(mensaje);
        }
    }

    //Crear una partida
    @PostMapping("/partida")
    public ResponseEntity<?> createPartida(@RequestBody PartidaDTO newPartida){
        Partida nuevaPartida = new Partida();
        nuevaPartida.setFecha(LocalDateTime.now());
        nuevaPartida.setPalabra(newPartida.getPalabra_jugada());
        nuevaPartida.setPuntos(newPartida.getPuntos());
        nuevaPartida.setIntentos(newPartida.getNumero_intentos());
        Optional<Juego> juego = juegoRepository.findById(newPartida.getJuego_idjuego());
        nuevaPartida.setJuego(juego.get());
        Optional<Jugador> jugador = jugadorRepository.findById(newPartida.getJugador_idjugador());
        nuevaPartida.setJugador(jugador.get());
        partidaRepository.save(nuevaPartida);
        String mensaje = "La partida ha sido creada exitosamente.";
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/partida/{id}")
    public ResponseEntity<?> updatePartida(@RequestBody PartidaModifDTO newPartida, @PathVariable Integer id){
        System.out.println("Antes de partida buscada");
        Optional<Partida> partidaBuscada = partidaRepository.findById(id);
        System.out.println("Despues de buscar partida");
        Optional<Partida> partidaExiste = partidaRepository.findById(id);
        System.out.println("comprobar partida");
        if(partidaBuscada.isEmpty()){
            System.out.println("Bienvenido");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!partidaExiste.isEmpty() && !Objects.equals(partidaBuscada.get().getIdPartida(), id)){
            System.out.println("Hasta pronto");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            System.out.println("Estas aqui");
            if(newPartida.getPuntos() != null || newPartida.getPuntos() <= -1){
                System.out.println("1 if");
                partidaBuscada.get().setPuntos(newPartida.getPuntos());
            }
            if(newPartida.getNumero_intentos() != null || newPartida.getNumero_intentos() > 0){
                System.out.println("2 if");
                partidaBuscada.get().setIntentos(newPartida.getNumero_intentos());
            }
            Partida partida = partidaBuscada.get();
            System.out.println(partida.getIdPartida());

            String mensaje = "La partida con ID " + id + " ha sido modificada exitosamente.";
            return ResponseEntity.ok(mensaje);
        }
    }
}