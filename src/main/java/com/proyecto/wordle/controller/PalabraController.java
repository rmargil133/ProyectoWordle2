package com.proyecto.wordle.controller;

import com.proyecto.wordle.services.PalabraServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PalabraController {
    private PalabraServices palabraServices;

    @GetMapping("palabra/aleatoria/{cantidad}")
    public ResponseEntity<Object> getPalabraAleatoria(@PathVariable Long cantidad) throws Exception {
        if (cantidad < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            List<String> listaPalabraAleatoria = palabraServices.palabraAleatoria(cantidad);
            return ResponseEntity.ok(listaPalabraAleatoria);
        }
    }

    @GetMapping("palabra/empieza/{cadena}")
    public ResponseEntity<Object> getPalabraEmpieza(@PathVariable String cadena) throws Exception {
        if (cadena.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            List<String> listaPalabraEmpieza = palabraServices.palabraEmpieza(cadena);
            if (listaPalabraEmpieza.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(listaPalabraEmpieza);
            }
        }
    }

    @GetMapping("palabra/termina/{cadena}")
    public ResponseEntity<Object> getPalabraTermina(@PathVariable String cadena) throws Exception {
        if (cadena.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            List<String> listaPalabraTermina = palabraServices.palabraTermina(cadena);
            if (listaPalabraTermina.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(listaPalabraTermina);
            }
        }
    }

    @GetMapping("palabra/contiene/{cadena}")
    public ResponseEntity<Object> getPalabraContiene(@PathVariable String cadena) throws Exception{
        if (cadena.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            List<String> listaPalabraContiene = palabraServices.palabraContiene(cadena);
            if (listaPalabraContiene.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.ok(listaPalabraContiene);
            }
        }
    }
}
