package com.proyecto.wordle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipo")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipo")
    private Long id;

    @Column(name = "nombre", length = 45, nullable = true, unique = true)
    private String nombre;

    @Column(name = "puntos", nullable = true)
    private Integer puntos = 0;

    @Column(name = "logo", length = 45)
    private String logo = "";
}