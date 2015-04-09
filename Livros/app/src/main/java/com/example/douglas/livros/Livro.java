package com.example.douglas.livros;

/**
 * Created by Douglas on 4/8/2015.
 */
public class Livro implements Comparable {
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Livro(String titulo, String autor, String genero, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
    }

    @Override
    public int compareTo(Object another) {
        Livro outro = (Livro) another;
        return titulo.toLowerCase().compareTo(outro.getTitulo().toLowerCase());
    }
}
