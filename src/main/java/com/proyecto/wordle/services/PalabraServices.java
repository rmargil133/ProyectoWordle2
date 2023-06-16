package com.proyecto.wordle.services;

import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class PalabraServices {
    private List<String> listaPalabras = new ArrayList<>();

    public PalabraServices() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/palabras.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                listaPalabras.add(line.trim());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar las palabras.txt desde el archivo.");
        }
    }
    //Metodo que devuelve una lista de palabras.txt de forma aleatoria
    public List<String> palabraAleatoria(Long tamaño) throws IOException{
        List<String> listaAleatoria = new ArrayList<>();
        if(tamaño > listaPalabras.size()){
            tamaño = (long) listaPalabras.size();
        }
        for(int x = 0; x < tamaño; x++){
            int random = (int) (Math.random() * listaPalabras.size());
            listaAleatoria.add(listaPalabras.get(random));
        }
        return listaAleatoria;
    }

    //Metodo que devuelve una lista de palabras.txt que empiezan por una cadena
    public List<String> palabraEmpieza(String cadena) throws IOException{
        List<String> listaEmpieza = new ArrayList<>();
        for (String listaPalabra : listaPalabras) {
            if (listaPalabra.startsWith(cadena)) {
                listaEmpieza.add(listaPalabra);
            }
        }
        return listaEmpieza;
    }

    //Metodo que devuelve una lista de palabras.txt que terminan por una cadena
    public List<String> palabraTermina(String cadena) throws IOException{
        List<String> listaTermina = new ArrayList<>();
        for (String listaPalabra : listaPalabras) {
            if (listaPalabra.endsWith(cadena)) {
                listaTermina.add(listaPalabra);
            }
        }
        return listaTermina;
    }

    //Metodo que devuelve una lista de palabras.txt que contiene una cadena
    public List<String> palabraContiene(String cadena) throws IOException{
        List<String> listaContiene = new ArrayList<>();
        for (String listaPalabra : listaPalabras) {
            if (listaPalabra.contains(cadena)) {
                listaContiene.add(listaPalabra);
            }
        }
        return listaContiene;
    }

}
