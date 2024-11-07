package com.challenge.literAlura_catalogo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

/***
 * Clase entidad que representa un autor.
 */
@Entity
@Table(name = "autores")
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer añoNacimiento;
    private Integer añoMuerte;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Libro> libros;

    /***
     * Verifica si el autor está vivo.
     *
     * @return true si el autor está vivo, false en caso contrario
     */
    public boolean isVivo() {
        return añoMuerte == null;
    }
}