package com.proyecto.wordle.dto;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class JugadorDTO {
    private Long idjugador;
    private Boolean admin;
    private String nombre;
    private String avatar;
    private Integer puntos;

    private Long equipo_idequipo;

}
