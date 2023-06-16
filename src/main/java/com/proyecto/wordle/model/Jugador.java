package com.proyecto.wordle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    private Integer id;

    @Column(name = "admin", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean admin = false;

    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name = "avatar", length = 45)
    private String avatar = "";

    @Column(name = "puntos", nullable = false)
    private Integer puntos = 0;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="equipo_idequipo")
    private Equipo equipo = null;
}
