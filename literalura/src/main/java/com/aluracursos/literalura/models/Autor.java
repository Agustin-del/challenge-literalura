package com.aluracursos.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int añoDeNacimiento;
    private int añoDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor (DatosAutor autor) {
        this.nombre = autor.nombre();
        this.añoDeNacimiento = autor.añoDeNacimiento();
        this.añoDeFallecimiento = autor.añoDeFallecimiento();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void addLibro(Libro libro) {
        this.libros.add(libro);
        libro.setAutor(this);
    }

    public int getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(int añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }

    public int getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(int añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Autor [nombre= " + nombre + ", fechaNacimiento= " + getAñoDeNacimiento() + ", fechaFallecimiento= " + getAñoDeFallecimiento() + ", libros= " + libros + "]";
    }
}
