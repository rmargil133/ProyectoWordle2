package com.proyecto.wordle.controller;

import com.proyecto.wordle.repositories.EquipoRepository;
import com.proyecto.wordle.repositories.JugadorRepository;
import com.proyecto.wordle.model.Equipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    //Obtener todos los equipos
    @GetMapping("/equipo")
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
        List<Equipo> equipoBuscado = equipoRepository.findById(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(equipoBuscado);
        }
    }

    //Eliminar un equipo por el id
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Integer id){
        List<Equipo> equipoBuscado = equipoRepository.findById(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            equipoRepository.delete(equipoBuscado.get(0));
            return ResponseEntity.noContent().build();
        }
    }

    //Crear un Equipo
    @PostMapping("/equipo")
    public ResponseEntity<?> createEquipo(@RequestBody Equipo newEquipo){
        List<Equipo> equipoExiste = equipoRepository.findByNombre(newEquipo.getNombre());
        if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El equipo ya existe");
        }
        else{
            newEquipo.setLogo(newEquipo.getLogo());
            newEquipo.setPuntos(newEquipo.getPuntos());
            equipoRepository.save(newEquipo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Equipo creado exitosamente");
        }
    }


    //Modificar un equipo por el id
    @PutMapping("/equipo/{id}")
    public ResponseEntity<?> modifyEquipo(@PathVariable Integer id, @RequestBody Equipo newEquipo){
        List<Equipo> equipoBuscado = equipoRepository.findById(id);
        List<Equipo> equipoExiste = equipoRepository.findByNombre(newEquipo.getNombre());
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            if (newEquipo.getNombre() != null
                    && !newEquipo.getNombre().isEmpty()
                    && !newEquipo.getNombre().equals(equipoBuscado.get(0).getNombre())) {
                equipoBuscado.get(0).setNombre(newEquipo.getNombre());
            }
            if (newEquipo.getLogo() != null
                    && !newEquipo.getLogo().equals(equipoBuscado.get(0).getLogo())) {
                equipoBuscado.get(0).setLogo(newEquipo.getLogo());
            }
            Equipo equipo = equipoBuscado.get(0);
            equipoRepository.save(equipo);
            return ResponseEntity.ok(equipo);
        }
    }
}