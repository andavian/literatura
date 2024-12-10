package com.andavian.literatura.repository;


import com.andavian.literatura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Autor> listAuthorsAliveInYear(int year);

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.birthYear = :birthYear")
    List<Autor> findByBirthYear(@Param("birthYear") int birthYear);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Autor> findByNameContainingIgnoreCase(@Param("nombre") String nombre);
}


