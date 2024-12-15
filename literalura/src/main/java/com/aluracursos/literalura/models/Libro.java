package com.aluracursos.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private List<String> idiomas;
    private int descargas;
    @ManyToOne
    private Autor autor;

    public Libro() {}

    public Libro (DatosLibro libro) {
        this.titulo = libro.titulo();
        this.idiomas = libro.idiomas();
        this.descargas = libro.descargas();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor.getNombre() + ", idiomas=" + idiomas  + "]";
    }
}
