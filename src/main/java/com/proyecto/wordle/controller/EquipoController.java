package com.proyecto.wordle.controller;

import com.proyecto.wordle.repositories.EquipoRepository;
import com.proyecto.wordle.repositories.JugadorRepository;
import com.proyecto.wordle.model.Equipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    //Obtener todos los equipos
    @GetMapping("/equipos")
    public ResponseEntity<Object> getAllEquipos(){
        List<Equipo> equipos = equipoRepository.findAll();
        if(equipos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(equipos);
        }
    }

    //Obtener un equipo por el id
    @GetMapping("/equipo/{id}")
    public ResponseEntity<Object> getEquipo(@PathVariable Integer id){
        Optional<Equipo> equipoBuscado = equipoRepository.findByid(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(equipoBuscado);
        }
    }

    //Crear un Equipo
    @PostMapping("equipo")
    public ResponseEntity<?> createEquipo(@RequestBody Equipo newEquipo){
        List<Equipo> equipoExiste = equipoRepository.findBynombre(newEquipo.getnombre());
        if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            newEquipo.setlogo("");
            newEquipo.setpuntos("");
            equipoRepository.save(newEquipo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    //Eliminar un equipo por el id
    @DeleteMapping("equipo/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Integer id){
        Optional<Equipo> equipoBuscado = equipoRepository.findByid(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build;
        }
        else{
            equipoRepository.delete(equipoBuscado.get());
            return ResponseEntity.noContent().build();
        }
    }

    //Modificar un equipo por el id
    @PutMapping("equipo{id}")
    public ResponseEntity<?> modifyEquipo(@PathVariable Integer id, RequestBody Equipo newEquipo){
        Optional<Equipo> equipoBuscado = equipoRepository.findByid(id);
        List<Equipo> equipoExiste = equipoRepository.findBynombre(newEquipo.getnombre());
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            if (newEquipo.getNombre() != null
                    && !newEquipo.getnombre().isEmpty()
                    && !newEquipo.getnombre().equals(equipoBuscado.get().getnombre())) {
                equipoBuscado.get().setnombre(newEquipo.getnombre());
            }
            if (newEquipo.getlogo() != null
                    && !newEquipo.getlogo().equals(equipoBuscado.get().getlogo())) {
                equipoBuscado.get().setlogo(newEquipo.getlogo());
            }
            Equipo equipo = equipoBuscado.get();
            equipoRepository.save(equipo);
            return ResponseEntity.ok(equipo);
        }
    }
}
