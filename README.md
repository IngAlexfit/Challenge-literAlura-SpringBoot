<h1 align="center">ONE - Challenge LiterAlura</h1>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=black)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Proyecto Terminado](https://img.shields.io/badge/Estado-Proyecto%20Terminado-brightgreen)

## 📚 Descripción del Proyecto

LiterAlura es una aplicación de consola para la gestión de bibliotecas digitales. Desarrollada con Spring Boot, JPA y PostgreSQL, permite realizar operaciones como:

- Búsqueda de libros por título
- Gestión de catálogo de libros
- Administración de autores
- Consultas especializadas por año e idioma

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 11 o superior
- PostgreSQL
- Maven

### Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/IngAlexfit/Challenge-literAlura-SpringBoot.git
cd Challenge-literAlura-SpringBoot
```

2. Configurar base de datos:
    - Editar `src/main/resources/application.properties`
    - Ajustar credenciales de PostgreSQL

## 🔍 Funcionalidades

- Búsqueda de libros por título
- Listado de libros registrados
- Gestión de autores
- Filtrado de autores por año
- Categorización por idioma


### 📋 Ejemplo de Uso

```plaintext
*************** LiterAlura - Gestión de Biblioteca ***************
1. Buscar libro por titulo
2. Listar libros registrados
3. Listar autores de libros registrados
4. Listar autores vivos de libros registrados en un determinado año
5. Listar libros registrados por idioma
6. Salir
Seleccione una opción: 1

Ingrese el título del libro: Twenty years after
********************************
**** Resultado del Libro ****
********************************
Titulo: Twenty years after
**** Autores ****
    - Nombre: Dumas, Alexandre
        Nacimiento: 1802
        Muerte: 1870
    - Nombre: Maquet, Auguste
        Nacimiento: 1813
        Muerte: 1888
**** Idiomas ****
en
********************************

Libro guardado exitosamente.
```

## 🛠 Tecnologías

- Java
- Spring Boot
- JPA/Hibernate
- PostgreSQL
- Gutendex API

## 📖 API

Este proyecto utiliza la API Gutendex para obtener datos de libros. Consulta la [documentación oficial](https://gutendex.com/) para más información.

## 👤 Autor

Dev. Alex Puello.

