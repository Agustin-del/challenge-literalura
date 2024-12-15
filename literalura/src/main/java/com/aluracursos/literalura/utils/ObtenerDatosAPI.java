package com.aluracursos.literalura.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ObtenerDatosAPI {
    private HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "http://gutendex.com/books/";
    public String getDatosPorTitulo(String titulo) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?search=" + URLEncoder.encode(titulo)))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
