package org.example.tablaHash;

import org.example.Libro.Libro;

import java.util.stream.IntStream;

public class TablaHash {
    private Libro[] tabla;
    private int tamanioTabla;
    private int primoMenor;
    private int contadorLibros;

    public TablaHash(int tamanioTabla) {
        this.tamanioTabla = tamanioTabla;
        this.tabla = new Libro[tamanioTabla];
        this.primoMenor = encontrarPrimoMenor(tamanioTabla);
        this.contadorLibros = 0;
    }

    private int hash(String codigo) {
        int suma = IntStream.range(0, codigo.length()).map(i -> Character.getNumericValue(codigo.charAt(i))).sum();  // posición
        /*
        int suma = 0;  // posición
        for (int i = 0; i < codigo.length(); i++) {
            suma += Character.getNumericValue(codigo.charAt(i));
        }
        return suma % this.tamanioTabla;
         */
        return suma % this.tamanioTabla;
    }

    private int hashDobleDispersion(String codigo, int posicionInicial) {
        int salto;
        salto = posicionInicial + Integer.parseInt(codigo) % this.primoMenor;
        return salto;
    }

    private int encontrarPrimoMenor(int numero) {
        for (int i = numero - 1; i > 1; i--)
            if (esPrimo(i))
                return i;
        return -1;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(numero); i++)
            if (numero % i == 0)
                return false;
        return true;
    }

    private boolean hayColision(int posicion) {
        return tabla[posicion] != null;
    }

    private void compruebaCargaTabla() {
        if( contadorLibros >= tamanioTabla / 2)
            rehashTabla(tamanioTabla * 2);
    }

    public void agregarLibro(Libro libro) {
        int hash;
        compruebaCargaTabla(); // para cada libro nuevo comprobamos la carga antes de insertarlo
        System.out.println("Tamaño de tabla: " + this.tamanioTabla);
        hash = this.hash(libro.getIsbn());
        while (hayColision(hash)) {
            hash = hashDobleDispersion(libro.getIsbn(), hash);

            // en caso de que el hash generado resulte mayor que el tamaño de la tabla, rehasheamos la tabla para que
            // no haya un ArrayIndexOutOfBoundsException
            if( hash >= tamanioTabla ){

                rehashTabla(hash + 1);
            }
        }

        tabla[hash] = libro;
        contadorLibros++;
    }

    public void eliminarLibro(String isbn) {
        int posicion = posicicionLibro(isbn);
        tabla[posicion] = null;
    }

    public Libro buscarLibroPorISBN(String isbn) {
        return tabla[posicicionLibro(isbn)];
    }

    private int posicicionLibro(String isbn) {
        int posicion = hash(isbn);
        while (isbn.equals(tabla[posicion].getIsbn())) {
            posicion = hashDobleDispersion(isbn, posicion);
        }
        return posicion;
    }

    public void mostrarTodosLibros() {
        for (Libro libro : this.tabla)
            if( libro != null)
                System.out.println(libro + "\n");
    }

    public void rehashTabla(int nuevoTamanio) {
        // creamos la nueva tabla y la intercambiamos con la vieja
        Libro[] tablaNueva = new Libro[nuevoTamanio];
        Libro[] tablaVieja;
        tablaVieja = tabla;
        tabla = tablaNueva;
        // reajustamos los parámetros a la nueva configuración
        this.tamanioTabla = nuevoTamanio;
        this.primoMenor = encontrarPrimoMenor(nuevoTamanio);
        this.contadorLibros = 0;

        // migramos los libros de la tabla vieja a la nueva
        for (Libro libro : tablaVieja)
            if (libro != null)
                agregarLibro(libro);
    }
}
