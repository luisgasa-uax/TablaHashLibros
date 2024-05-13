package org.example.Libro;

public class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final int anio;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libro libro = (Libro) o;

        return isbn.equals(libro.isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anio=" + anio +
                '}';
    }
}
