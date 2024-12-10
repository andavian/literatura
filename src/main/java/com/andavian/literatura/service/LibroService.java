package com.andavian.literatura.service;

import com.andavian.literatura.dto.AutorDTO;
import com.andavian.literatura.dto.LibroDTO;
import com.andavian.literatura.models.Autor;
import com.andavian.literatura.models.Libro;
import com.andavian.literatura.repository.AutorRepository;
import com.andavian.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }



//    public LibroDTO obtenerPorId(Long id) {
//        return libroRepository.findById(id)
//                .map(this::convertirADTO)
//                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
//    }

    public Libro guardarLibro(LibroDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.titulo());
        libro.setIdioma(libroDTO.idioma());
        libro.setCantidadDescargas(libroDTO.cantidadDescargas());

        List<Autor> autores = libroDTO.autores().stream()
                .map(this::obtenerOcrearAutor)
                .toList();

        libro.setAutores(new HashSet<>(autores));
        return libroRepository.save(libro);
    }

    private Autor obtenerOcrearAutor(AutorDTO autorDTO) {
        return autorRepository.findByNombreIgnoreCase(autorDTO.nombre())
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setNombre(autorDTO.nombre());
                    nuevoAutor.setBirthYear(autorDTO.birthYear());
                    nuevoAutor.setDeathYear(autorDTO.deathYear());
                    return autorRepository.save(nuevoAutor);
                });
    }

//    private List<LibroDTO> convierteDatos(List<Libro> libros) {
//        return libros.stream()
//                .map(this::convertirADTO)
//                .collect(Collectors.toList());
//    }

//    private LibroDTO convertirADTO(Libro libro) {
//        List<AutorDTO> autores = libro.getAutores().stream()
//                .map(autor -> new AutorDTO(
//                        autor.getNombre(),
//                        autor.getBirthYear(),
//                        autor.getDeathYear()))
//                .collect(Collectors.toList());
//
//        return new LibroDTO(
//                libro.getTitulo(),
//                libro.getIdioma(),
//                libro.getCantidadDescargas(),
//                autores
//        );
//    }

    public void mostrarLibrosBuscados() {
        List<Libro> libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);

    }

    public void mostrarAutoresBuscados() {
        List<Autor> autores = autorRepository.findAll();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);

    }

        public List<Autor> listAuthorsAliveInYear(int year) {
        return autorRepository.listAuthorsAliveInYear(year);
    }

    public List<Libro> findBooksByLanguage(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    public List<Libro> obtenerTop10LibrosDescargados() {
        return libroRepository.findTop10LibrosDescargados();
    }

    public List<Autor> buscarAutoresPorNombre(String nombre) {
        return autorRepository.findByNameContainingIgnoreCase(nombre);
    }

    public List<Autor> buscarAutoresPorFechaDeNacimiento(int birthYear) {
        return autorRepository.findByBirthYear(birthYear);
    }
}
