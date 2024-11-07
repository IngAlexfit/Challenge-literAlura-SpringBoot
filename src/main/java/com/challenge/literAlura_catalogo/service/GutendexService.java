package com.challenge.literAlura_catalogo.service;

import com.challenge.literAlura_catalogo.model.Libro;
import com.challenge.literAlura_catalogo.model.Autor;
import com.challenge.literAlura_catalogo.model.Idioma;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        if (response != null && !response.getResults().isEmpty()) {
            GutendexBook gutendexBook = response.getResults().get(0);
            Libro libro = new Libro();
            libro.setId(gutendexBook.getId()); // Set the ID from the API
            libro.setTitulo(gutendexBook.getTitle());

            List<Idioma> idiomas = gutendexBook.getLanguages().stream()
                    .map(language -> {
                        Idioma idioma = new Idioma();
                        idioma.setIdioma(language);
                        idioma.setLibro(libro);
                        return idioma;
                    })
                    .collect(Collectors.toList());
            libro.setIdiomas(idiomas);

            List<Autor> autores = gutendexBook.getAuthors().stream()
                    .map(author -> {
                        Autor autor = new Autor();
                        autor.setNombre(author.getName());
                        autor.setAñoNacimiento(author.getBirthYear());
                        autor.setAñoMuerte(author.getDeathYear());
                        return autor;
                    })
                    .collect(Collectors.toList());

            libro.setAutores(autores);

            return libro;
        }
        return null;
    }
}