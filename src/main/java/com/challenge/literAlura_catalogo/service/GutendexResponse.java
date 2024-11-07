package com.challenge.literAlura_catalogo.service;

import lombok.Data;

import java.util.List;

@Data
public class GutendexResponse {
    private List<GutendexBook> results;
}