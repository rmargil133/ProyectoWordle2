package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PartidaDTO {
    private Integer idPartida;
    private LocalDateTime datetime;
    private Integer puntos;
    private String palabra_jugada;
    private Integer numero_intentos;
    private Integer jugador_idjugador;
    private Integer juego_idjuego;

}
