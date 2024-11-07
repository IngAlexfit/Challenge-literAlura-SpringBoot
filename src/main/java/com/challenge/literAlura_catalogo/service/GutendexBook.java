package com.challenge.literAlura_catalogo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GutendexBook {
    private Long id;

    private String title;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("authors")
    private List<Author> authors;

    @Data
    public static class Author {
        private String name;
        @JsonProperty("birth_year")
        private int birthYear;
        @JsonProperty("death_year")
        private int deathYear;
    }
}