package org.example;

import org.example.Libro.Libro;
import org.example.tablaHash.TablaHash;

public class Main {
    public static void main(String[] args) {

        TablaHash biblio = new TablaHash(5);
        biblio.agregarLibro(new Libro("1234", "Quijote", "Cervantes", 1653));
        biblio.agregarLibro(new Libro("2345", "Platero", "Jimenez", 1902));
        biblio.agregarLibro(new Libro("3456", "Alfanhui", "Ferlosio", 1953));
        biblio.agregarLibro(new Libro("4567", "Camino", "Delibes", 1983));
        biblio.mostrarTodosLibros();
        biblio.agregarLibro(new Libro("7456", "Cazador", "Delibes", 1985));

        // Agregar libros a la tabla hash
        biblio.agregarLibro(new Libro("9780306406157", "Libro Uno", "Autor Uno", 1980));
        biblio.agregarLibro(new Libro("9780316769488", "Libro Dos", "Autor Dos", 1951));
        biblio.agregarLibro(new Libro("9780451524935", "Libro Tres", "Autor Tres", 1949));
        biblio.agregarLibro(new Libro("4567", "Camino", "Delibes", 1983));

        // Agregar más libros con colisiones
        biblio.agregarLibro(new Libro("1114", "Libro Cuatro", "Autor Cuatro", 2020));  // 1+1+1+4 = 7
        biblio.agregarLibro(new Libro("1231", "Libro Cinco", "Autor Cinco", 2021));   // 1+2+3+1 = 7
        biblio.agregarLibro(new Libro("2230", "Libro Seis", "Autor Seis", 2022));     // 2+2+3+0 = 7
        biblio.agregarLibro(new Libro("5501", "Libro Siete", "Autor Siete", 2023));   // 5+5+0+1 = 11
        biblio.agregarLibro(new Libro("6401", "Libro Ocho", "Autor Ocho", 2024));     // 6+4+0+1 = 11

        // Buscar un libro por ISBN
        String isbnBuscar = "9780316769488";
        Libro libroEncontrado = biblio.buscarLibroPorISBN(isbnBuscar);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado);
        } else {
            System.out.println("Libro no encontrado.");
        }

        biblio.eliminarLibro("9780316769488");

    }
}