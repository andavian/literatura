package com.andavian.literatura.dto;

import com.andavian.literatura.models.DatosLibro;

import java.util.List;

public record LibroDTO(
        String titulo,
        String idioma,
        Integer cantidadDescargas,
        List<AutorDTO> autores
) {
    public static LibroDTO fromDatosLibro(DatosLibro datosLibro) {
        List<AutorDTO> autoresDTO = datosLibro.autores().stream()
                .map(autor -> new AutorDTO(autor.name(), autor.birthYear(), autor.deathYear()))
                .toList();
        return new LibroDTO(
                datosLibro.title(),
                datosLibro.languages().get(0), // Supongamos que toma el primer idioma
                datosLibro.downloadCount(),
                autoresDTO);
    }
}
