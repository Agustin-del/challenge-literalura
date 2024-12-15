package com.aluracursos.literalura.repositorios;

import com.aluracursos.literalura.models.Autor;
import com.aluracursos.literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Boolean existsByNombre(String autor);

    Autor findByNombre(String autor);

    @Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.titulo ILIKE %:name% ")
    Optional<Libro> findByBookName(String name);

    @Query("SELECT l FROM Autor a JOIN a.libros l")
    List<Libro> findAllLibros();

    @Query("SELECT a FROM Autor a WHERE a.añoDeNacimiento <= :year AND a.añoDeFallecimiento >= :year")
    List<Autor> findByYear(int year);

    @Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.idiomas CONTAINS :idioma")
    List<Libro> findByIdioma(String idioma);
}
