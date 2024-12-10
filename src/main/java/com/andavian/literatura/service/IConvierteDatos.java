package com.andavian.literatura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class <T> clase);
}
