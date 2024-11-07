// Idioma.java
package com.challenge.literAlura_catalogo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "libro_idiomas")
@Data
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idioma")
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}
