package com.proyecto.wordle.dto;

import com.proyecto.wordle.model.Equipo;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class JugadorDTO {
    private Integer idjugador;
    private Boolean admin;
    private String nombre;
    private String avatar;
    private Integer puntos;
    private Integer equipo_idequipo;

}
