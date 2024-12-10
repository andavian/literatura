package com.andavian.literatura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        String title,
        @JsonAlias("authors") List<DatosAutor> autores, // Asigna un alias para obtener directamente el nombre del autor
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") int downloadCount
) {
}
