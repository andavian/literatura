package com.andavian.literatura.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvierteDatos {
    private final ObjectMapper objectMapper = new ObjectMapper();

//    public <T> T obtenerDatos(String json, TypeReference<T> typeReference) {
//        try {
//            return objectMapper.readValue(json, typeReference);
//        } catch (Exception e) {
//            throw new RuntimeException("Error al convertir datos", e);
//        }
//    }

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir datos", e);
        }
    }
}


