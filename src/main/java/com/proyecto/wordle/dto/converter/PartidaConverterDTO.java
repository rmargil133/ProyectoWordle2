package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.dto.PartidaDTO;
import com.proyecto.wordle.model.Partida;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartidaConverterDTO {
    public static PartidaDTO convertToDTO(Partida partida){
        PartidaDTO partidaDTO = new PartidaDTO();
        partidaDTO.setIdPartida(partida.getIdPartida());
        partidaDTO.setDatetime(partida.getFecha());
        partidaDTO.setNumero_intentos(partida.getIntentos());
        partidaDTO.setPuntos(partida.getPuntos());
        partidaDTO.setPalabra_jugada(partida.getPalabra());
        partidaDTO.setJugador_idjugador(partida.getJugador().getId());
        partidaDTO.setJuego_idjuego(partida.getJuego().getIdJuego());
        return partidaDTO;
    }

}

