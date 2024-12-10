package com.andavian.literatura.service;

import com.andavian.literatura.dto.LibroDTO;
import com.andavian.literatura.models.DatosAutor;
import com.andavian.literatura.models.DatosLibro;
import com.andavian.literatura.models.RespuestaLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
@Service
public class SeleccionadorDeLibros {
    private final ConvierteDatos convierteDatos;
    private final LibroService libroService;

    @Autowired
    public SeleccionadorDeLibros(ConvierteDatos convierteDatos, LibroService libroService) {
        this.convierteDatos = convierteDatos;
        this.libroService = libroService;
    }


    /**
     * Busca y selecciona un libro de una lista obtenida desde una respuesta JSON.
     *
     * @param jsonRespuesta Respuesta JSON con los datos de libros.
     * @return El libro seleccionado por el usuario o null si no hay selección válida.
     */
    public DatosLibro buscarYSeleccionar(String jsonRespuesta) {
        if (jsonRespuesta == null || jsonRespuesta.isBlank()) {
            throw new RuntimeException("El JSON proporcionado está vacío o es nulo.");
        }

        try {
            // Convertir JSON en el objeto raíz que contiene la lista de libros
            RespuestaLibros respuestaLibros = convierteDatos.obtenerDatos(jsonRespuesta, RespuestaLibros.class);

            // Extraer la lista de libros desde la respuesta
            List<DatosLibro> libros = respuestaLibros.resultados();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros.");
                return null;
            }

            // Mostrar títulos de los libros
            System.out.println("Libros encontrados:");
            for (int i = 0; i < libros.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, libros.get(i).title());
            }

            // Pedir selección al usuario
            System.out.println("Seleccione un libro por su número:");
            Scanner scanner = new Scanner(System.in);
            int seleccion = scanner.nextInt();

            if (seleccion > 0 && seleccion <= libros.size()) {
                DatosLibro libroSeleccionado = libros.get(seleccion - 1);

                // Imprimir información del libro seleccionado
                System.out.println("Has seleccionado: " + libroSeleccionado.title());
                System.out.println("Autor(es):");
                for (DatosAutor autor : libroSeleccionado.autores()) {
                    System.out.println(" - " + autor.name());
                }
                System.out.println("Idioma: " + libroSeleccionado.languages());
                System.out.println("Total descargas: " + libroSeleccionado.downloadCount());
                return libroSeleccionado; // Devolver el libro seleccionado
            } else {
                System.out.println("Selección inválida.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
