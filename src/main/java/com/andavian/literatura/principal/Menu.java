package com.andavian.literatura.principal;

import com.andavian.literatura.dto.LibroDTO;
import com.andavian.literatura.models.DatosLibro;
import com.andavian.literatura.service.ConsumoAPI;
import com.andavian.literatura.service.ConvierteDatos;
import com.andavian.literatura.service.SeleccionadorDeLibros;
import com.andavian.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final SeleccionadorDeLibros seleccionador;
    private final LibroService libroService;

    @Autowired
    public Menu(SeleccionadorDeLibros seleccionador, LibroService libroService) {
        this.seleccionador = seleccionador;
        this.libroService = libroService;
    }

    public final Scanner keyboard = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();


    public String getOptionMenu() {
        return """
                
                **********************************************************************
                    ** Sea bienvenido/a al buscador de Libros **
                
                        1 - Buscar libro por título
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en determinado año
                        5 - Listar libros por idioma
                        6 - Buscar los 10 libros más descargados
                        7 - Buscar autor por nombre
                        8 - Buscar autor por año de nacimiento
                        9 - Salir
                
                        Selecciona una opción válida
                
                ***********************************************************************
                """;
    }


    public void selectionOption(int option) {
        switch (option) {
            case 1 -> searchBook();
            case 2 -> listBooks();
            case 3 -> listAuthors();
            //case 4 -> listAuthorsAliveInYear();
//            case 5 -> listBooksByLanguage();
//            case 6 -> listTopDownloadedBooks();
//            case 7 -> searchAuthorByName();
//            case 8 -> searchAuthorByBirthYear();
            case 9 -> System.out.println("Saliendo de la aplicación...");
            default -> System.out.println("Opción no válida, intente de nuevo.");
        }
    }

    private void searchBook() {
        keyboard.nextLine();
        System.out.println("Por favor escribe el nombre del libro que deseas buscar");
        var bookName = keyboard.nextLine();
        String url = URL_BASE + "?search=" + bookName.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(url);
        DatosLibro libroSeleccionado = seleccionador.buscarYSeleccionar(json);
        if (libroSeleccionado != null) {
            // Guardar en la base de datos
            LibroDTO libroDTO = LibroDTO.fromDatosLibro(libroSeleccionado);
            libroService.guardarLibro(libroDTO);
            System.out.println("El libro se ha guardado en la base de datos.");
        } else {
            System.out.println("No se seleccionó ningún libro.");
        }
    }

        private void listBooks() {
            System.out.println("Listando todos los libros registrados en la base de datos...");
            libroService.mostrarLibrosBuscados();
            }

    private void listAuthors() {
        System.out.println("Listando todos los autores registrados en la base de datos...");
        libroService.mostrarAutoresBuscados();
    }



































































    






    }

