package com.challenge.literAlura_catalogo.service;

import com.challenge.literAlura_catalogo.model.Autor;
import com.challenge.literAlura_catalogo.model.Libro;
import com.challenge.literAlura_catalogo.repository.AutorRepository;
import com.challenge.literAlura_catalogo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/***
 * Clase de servicio para operaciones del catálogo.
 */
@Service
public class CatalogoService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    /***
     * Busca un libro por su título.
     *
     * @param titulo el título del libro
     * @return un Optional que contiene el libro si se encuentra, o vacío si no se encuentra
     */
    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    /***
     * Guarda un libro en el catálogo.
     *
     * @param libro el libro a guardar
     * @return el libro guardado
     */
    @Transactional
    public Libro guardarLibro(Libro libro) {
        Optional<Libro> existingLibro = buscarLibroPorTitulo(libro.getTitulo());
        if (existingLibro.isPresent()) {
            return existingLibro.get();
        }

        List<Autor> autores = libro.getAutores().stream()
                .map(autor -> {
                    if (autor.getId() == null) {
                        return autorRepository.save(autor);
                    }
                    return autor;
                })
                .collect(Collectors.toList());
        libro.setAutores(autores);
        return libroRepository.save(libro);
    }

    /***
     * Lista todos los libros registrados.
     *
     * @return una lista de todos los libros registrados
     */
    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    /***
     * Lista todos los autores registrados.
     *
     * @return una lista de todos los autores registrados
     */
    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    /***
     * Lista todos los autores registrados en una cadena formateada.
     *
     * @return una cadena formateada de todos los autores registrados
     */
    public String listarAutoresFormatted() {
        List<Autor> autores = listarAutores();

        if (autores.isEmpty()) {
            return "No existen registros.";
        }

        return autores.stream()
                .map(autor -> String.format(
                        "********************************\n" +
                                "**** Resultado del Autor ****\n" +
                                "********************************\n" +
                                "Nombre: %s\n" +
                                "Año de Nacimiento: %d\n" +
                                "Año de Muerte: %s\n" +
                                "********************************\n",
                        autor.getNombre(),
                        autor.getAñoNacimiento(),
                        autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "N/A"))
                .collect(Collectors.joining("\n"));
    }

    /***
     * Lista todos los autores vivos para un año dado.
     *
     * @param año el año para filtrar autores
     * @return una lista de autores vivos para el año dado
     */
    @Transactional(readOnly = true)
    public List<Autor> listarAutoresVivosPorAño(int año) {
        return autorRepository.findAll().stream()
                .filter(autor -> autor.isVivo() || (autor.getAñoMuerte() != null && autor.getAñoMuerte() > año))
                .collect(Collectors.toList());
    }

    /***
     * Lista todos los autores vivos para un año dado en una cadena formateada.
     *
     * @param año el año para filtrar autores
     * @return una cadena formateada de autores vivos para el año dado
     */
    public String listarAutoresVivosPorAñoFormatted(int año) {
        List<Autor> autores = autorRepository.findAll().stream()
                .filter(autor -> autor.getAñoNacimiento() <= año && (autor.getAñoMuerte() == null || autor.getAñoMuerte() > año))
                .collect(Collectors.toList());

        if (autores.isEmpty()) {
            return "No existen registros.";
        }

        return autores.stream()
                .map(autor -> String.format(
                        "********************************\n" +
                                "**** Resultado del Autor ****\n" +
                                "********************************\n" +
                                "Nombre: %s\n" +
                                "Año de Nacimiento: %d\n" +
                                "Año de Muerte: %s\n" +
                                "********************************\n",
                        autor.getNombre(),
                        autor.getAñoNacimiento(),
                        autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "N/A"))
                .collect(Collectors.joining("\n"));
    }

    /***
     * Lista todos los idiomas disponibles.
     *
     * @return una lista de todos los idiomas disponibles
     */
    @Transactional(readOnly = true)
    public List<String> listarIdiomasDisponibles() {
        return libroRepository.findAll().stream()
                .flatMap(libro -> libro.getIdiomas().stream())
                .map(idioma -> idioma.getIdioma())
                .distinct()
                .collect(Collectors.toList());
    }

    /***
     * Lista todos los libros para un idioma dado.
     *
     * @param idioma el idioma para filtrar libros
     * @return una lista de libros para el idioma dado
     */
    @Transactional(readOnly = true)
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findAll().stream()
                .filter(libro -> libro.getIdiomas().stream()
                        .anyMatch(idiomaObj -> idiomaObj.getIdioma().equals(idioma)))
                .collect(Collectors.toList());
    }
}