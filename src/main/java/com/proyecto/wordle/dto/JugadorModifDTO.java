package com.proyecto.wordle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JugadorModifDTO {

    private Boolean admin;
    private String nombre;
    private String avatar;
    private Integer puntos;
    private Long equipo_idequipo;
}
