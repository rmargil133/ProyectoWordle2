package com.proyecto.wordle.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "juega_partida_a")

public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpartida")
    private Integer idPartida;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "puntos", nullable = false)
    private Integer puntos;

    @Column(name = "palabra_jugada", length = 45, nullable = false)
    private String palabra;

    @Column(name = "numero_intentos", nullable = false)
    private Integer intentos;

    @ManyToOne
    @JoinColumn(name = "jugador_idJugador")
    @JsonBackReference
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "juego_idJuego")
    @JsonBackReference
    private Juego juego;
}
