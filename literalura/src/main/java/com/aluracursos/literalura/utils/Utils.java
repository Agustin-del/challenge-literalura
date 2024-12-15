package com.aluracursos.literalura.utils;

import com.aluracursos.literalura.models.*;
import com.aluracursos.literalura.repositorios.AutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Utils {
    private final Scanner scanner = new Scanner(System.in);
    private final ObtenerDatosAPI api = new ObtenerDatosAPI();
    private final ConvertirDatos convertidor = new ConvertirDatos();

    private final AutorRepository autorRepository;

    public Utils(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void menu() {
        while(true) {
            System.out.println("""
                ------------------
                Elija la opción a través de su número: 
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. Salir                
                ------------------
                """);
            handleOptions();
        }

    }

    private void handleOptions() {
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el título del libro que desea buscar");
                String titulo = scanner.nextLine();
                Optional<Libro> libro = autorRepository.findByBookName(titulo);
                if(libro.isPresent()) {
                    System.out.println(libro.get());
                } else {
                    String json = obtenerPorTitulo(titulo);
                    if (json.contains("\"results\":[]")) {
                        System.out.println("Libro no encontrado");
                    } else {
                        Autor autor = new Autor(convertidor.obtenerDatosAutor(json));
                        Libro libroAPI = new Libro(convertidor.obtenerDatosLibro(json));
                        if(autorRepository.existsByNombre(autor.getNombre())) {
                            autor = autorRepository.findByNombre(autor.getNombre());
                            autor.addLibro(libroAPI);
                            autorRepository.save(autor);
                        } else {
                            autor.addLibro(libroAPI);
                            autorRepository.save(autor);
                        }
                    }
                }
                break;
            case 2:
                List<Libro> libros = autorRepository.findAllLibros();
                libros.forEach(System.out::println);
                break;
            case 3:
                List<Autor> autores = autorRepository.findAll();
                autores.forEach(System.out::println);
                break;
            case 4:
                System.out.println("Ingrese el año que desea buscar: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                autores = autorRepository.findByYear(year);
                if(autores.size() > 0) {
                    autores.forEach(System.out::println);
                } else {
                    System.out.println("No hay registro de autores vivos en el año " + year);
                }
                break;
            case 5:
                System.out.println("""
                        -------------
                        Introduzca el número por el idioma que desea buscar:
                        1. es = español
                        2. en = inglés
                        3. fr = frances
                        """);
                int idioma =scanner.nextInt();
                scanner.nextLine();
                if(idioma == 1) {
                    libros = autorRepository.findByIdioma("es");
                    if(libros.size() > 0) {
                        libros.forEach(System.out::println);
                    } else {
                        System.out.println("No hay libros en español");
                    }
                }else if(idioma == 2) {
                    libros = autorRepository.findByIdioma("en");
                    if(libros.size() > 0) {
                        libros.forEach(System.out::println);
                    } else {
                        System.out.println("No hay libros en inglés");
                    }
                } else if(idioma == 3) {
                    libros = autorRepository.findByIdioma("fr");
                    if(libros.size() > 0) {
                        libros.forEach(System.out::println);
                    } else {
                        System.out.println("No hay libros en frances");
                    }
                } else {
                    System.out.println("Opcion no valida");
                }
                break;
            case 0:
                System.out.println("Hasta pronto");
                System.exit(0);
                break;
        }
    }

    private String obtenerPorTitulo(String titulo){
        return api.getDatosPorTitulo(titulo);
    }

}
