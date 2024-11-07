package com.challenge.literAlura_catalogo;

import com.challenge.literAlura_catalogo.model.Libro;
import com.challenge.literAlura_catalogo.service.CatalogoService;
import com.challenge.literAlura_catalogo.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * Clase principal de la aplicación LiterAlura Catalogo.
 */
@SpringBootApplication
public class LiterAluraCatalogoApplication implements CommandLineRunner {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private GutendexService gutendexService;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraCatalogoApplication.class, args);
    }

    /***
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * @param args argumentos de línea de comandos
     * @throws Exception si ocurre un error durante la ejecución
     */
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo(scanner);
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosPorAño(scanner);
                        break;
                    case 5:
                        listarLibrosPorIdioma(scanner);
                        break;
                    case 6:
                        salir();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 6.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    /***
     * Imprime el menú de opciones en la consola.
     */
    private void printMenu() {
        System.out.println("1. Buscar libro por titulo");
        System.out.println("2. Listar libros registrados");
        System.out.println("3. Listar autores de libros registrados");
        System.out.println("4. Listar autores vivos de libros registrados en un determinado año");
        System.out.println("5. Listar libros registrados por idioma");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /***
     * Busca un libro por su título y lo guarda en el catálogo si se encuentra.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario
     */
    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        Libro libro = gutendexService.buscarLibroPorTitulo(titulo);
        if (libro != null) {
            System.out.println(libro.toString());
            catalogoService.guardarLibro(libro);
            System.out.println("Libro guardado exitosamente.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    /***
     * Lista todos los libros registrados en el catálogo.
     */
    private void listarLibrosRegistrados() {
        List<Libro> libros = catalogoService.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    /***
     * Lista todos los autores registrados en el catálogo.
     */
    private void listarAutoresRegistrados() {
        System.out.println(catalogoService.listarAutoresFormatted());
    }

    /***
     * Lista todos los autores vivos para un año dado.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario
     */
    private void listarAutoresVivosPorAño(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        int año = scanner.nextInt();
        System.out.println(catalogoService.listarAutoresVivosPorAñoFormatted(año));
    }

    /***
     * Lista todos los libros registrados por idioma.
     *
     * @param scanner el objeto Scanner para leer la entrada del usuario
     */
    private void listarLibrosPorIdioma(Scanner scanner) {
        List<String> idiomas = catalogoService.listarIdiomasDisponibles();
        if (idiomas.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (int i = 0; i < idiomas.size(); i++) {
            System.out.println((i + 1) + ". " + idiomas.get(i));
        }
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Seleccione un idioma: ");
            try {
                int idiomaOpcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (idiomaOpcion > 0 && idiomaOpcion <= idiomas.size()) {
                    String idiomaSeleccionado = idiomas.get(idiomaOpcion - 1);
                    catalogoService.listarLibrosPorIdioma(idiomaSeleccionado).forEach(System.out::println);
                    validInput = true;
                } else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    /***
     * Sale de la aplicación.
     */
    private void salir() {
        System.out.println("Saliendo...");
        System.exit(0);
    }
}