package com.challenge.literAlura_catalogo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Clase entidad que representa un libro.
 */
@Entity
@Table(name = "libros")
@Data
public class Libro {
    @Id
    private Long id;
    private String titulo;

    @OneToMany(mappedBy = "libro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Idioma> idiomas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "libro_autores",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @ToString.Exclude
    private List<Autor> autores;

    @Override
    public String toString() {
        String autoresInfo = autores.stream()
            .map(autor -> String.format("  - Nombre: %s\n    Nacimiento: %d\n    Muerte: %s",
                autor.getNombre(),
                autor.getAñoNacimiento(),
                autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "N/A"))
            .collect(Collectors.joining("\n"));

        String idiomasInfo = idiomas.stream()
            .map(Idioma::getIdioma)
            .collect(Collectors.joining(", "));

        return String.format(
            "********************************\n" +
            "**** Resultado del Libro ****\n" +
            "********************************\n" +
            "Titulo: %s\n" +
            "**** Autores ****\n%s\n" +
            "**** Idiomas ****\n%s\n" +
            "********************************\n",
            titulo, autoresInfo, idiomasInfo);
    }
}