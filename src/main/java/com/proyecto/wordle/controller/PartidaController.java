package com.proyecto.wordle.controller;

import com.proyecto.wordle.dto.PartidaDTO;
import com.proyecto.wordle.dto.PartidaModifDTO;
import com.proyecto.wordle.dto.converter.PartidaConverterDTO;
import com.proyecto.wordle.model.Juego;
import com.proyecto.wordle.model.Jugador;
import com.proyecto.wordle.model.Partida;
import com.proyecto.wordle.repositories.EquipoRepository;
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
    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final JuegoRepository juegoRepository;

    //Obtener todas las partidas
    @GetMapping("/partidas")
    public ResponseEntity<List<?>> getAllPartidas(){
        List<Partida> partidas = partidaRepository.findAll();
        if(partidas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<PartidaDTO> DTOPartidaList = partidas.stream().map(PartidaConverterDTO::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    //Obtener una partida por su id
    @GetMapping("/partida/{id}")
    public ResponseEntity<List<?>> getPartidaById(@PathVariable Integer id){
        System.out.println(id);
        List<Partida> partidaBuscada = partidaRepository.findByJugador_idJugador(id);
        if(partidaBuscada.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<PartidaDTO> DTOPartidaList = partidaBuscada.stream().map(PartidaConverterDTO::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    //Crear una partida
    @PostMapping("/partida")
    public ResponseEntity<?> createPartida(@RequestBody PartidaDTO newPartida){
        Partida nuevaPartida = new Partida();
        nuevaPartida.setFecha(LocalDateTime.now());
        nuevaPartida.setPalabra(newPartida.getPalabra_jugada());
        nuevaPartida.setPuntos(0);
        nuevaPartida.setIntentos(0);
        Optional<Juego> juego = juegoRepository.findById(newPartida.getJuego_idjuego());
        nuevaPartida.setJuego(juego.get());
        Optional<Jugador> jugador = jugadorRepository.findById(newPartida.getJugador_idjugador());
        nuevaPartida.setJugador(jugador.get());
        partidaRepository.save(nuevaPartida);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/partida/{id}")
    public ResponseEntity<?> updatePartida(@RequestBody PartidaModifDTO newPartida, @PathVariable Integer id){
        System.out.println("Antes de partida buscada");
        Optional<Partida> partidaBuscada = partidaRepository.findById(id);
        System.out.println("Despues de buscar partida");
        Optional<Partida> partidaExiste = partidaRepository.findById(id);
        System.out.println("comprobar partida");
        if(partidaBuscada.isEmpty()){
            System.out.println("Partida Encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!partidaExiste.isEmpty() && !Objects.equals(partidaBuscada.get().getIdPartida(), id)){
            System.out.println("Partida no encontrada");
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

            return ResponseEntity.ok(partida);
        }
    }
}


