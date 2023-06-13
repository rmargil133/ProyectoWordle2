package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.dto.JugadorDTO;
import com.proyecto.wordle.model.Jugador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JugadorConverterDTO {
    public static JugadorDTO convertToDTO(Jugador jugador) {
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setIdjugador(jugador.getId());
        jugadorDTO.setEquipo_idequipo(jugador.getId());
        jugadorDTO.setAdmin(jugador.getAdmin());
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setAvatar(jugador.getAvatar());
        jugadorDTO.setPuntos(jugador.getPuntos());
        return jugadorDTO;
    }
}