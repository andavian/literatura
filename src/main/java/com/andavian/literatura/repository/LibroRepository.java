package com.andavian.literatura.repository;

import com.andavian.literatura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE LOWER(l.idioma) = LOWER(:idioma)")
    List<Libro> findByIdiomaIgnoreCase(@Param("idioma") String idioma);

    @Query(value = "SELECT * FROM libros ORDER BY cantidad_descargas DESC LIMIT 10", nativeQuery = true)
    List<Libro> findTop10LibrosDescargados();
}

