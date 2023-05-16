package com.proyecto.wordle.dto.converter;

import com.proyecto.wordle.dto.JugadorDTO;
import com.proyecto.wordle.model.Jugador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
@RequiredArgsConstructor
public class JugadorConverterDTO {

    private final ModelMapper modelMapper;

    public static JugadorDTO convertToDTO(Jugador jugador){
        return modelMapper.map(jugador,JugadorDTO.class);
    }
}