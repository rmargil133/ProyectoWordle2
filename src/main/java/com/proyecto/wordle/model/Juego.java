package com.proyecto.wordle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "juego")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idjuego")
    private Integer idJuego;

    @Column(name="nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name="dificultad_juego", length = 45, nullable = false)
    private String dificultad;

    @Column(name="intentos_max", nullable = false)
    private Integer intentos;

    @Column(name="instrucciones", length = 45, nullable = false)
    private String instrucciones;

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Partida> partidas;
}