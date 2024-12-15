package com.aluracursos.literalura.utils;

import com.aluracursos.literalura.models.DatosAutor;
import com.aluracursos.literalura.models.DatosLibro;
import com.aluracursos.literalura.models.DatosLibros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos {

    private ObjectMapper mapper = new ObjectMapper();

    private DatosLibros convertir(String json) {
        try {
            return mapper.readValue(json, DatosLibros.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public DatosLibro obtenerDatosLibro(String json) {
        if(json == null) {
            return null;
        } else {
            DatosLibros datosLibros = convertir(json);
            if (datosLibros.datosLibros().size() == 0) {
                return null;
            } else {
                return datosLibros.datosLibros().get(0);
            }
        }
    }

    public DatosAutor obtenerDatosAutor(String json) {
        if (json == null) {
            return null;
        } else {
            DatosLibros datosLibros = convertir(json);
            if(datosLibros.datosLibros().size() == 0) {
                return null;
            } else {
                DatosLibro datosLibro = obtenerDatosLibro(json);
                return datosLibro.datosAutor().get(0);

            }
        }
    }
}
