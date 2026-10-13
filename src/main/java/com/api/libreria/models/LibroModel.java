package com.api.libreria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class LibroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    @Column
    private String titulo;
    
    @Column
    private String autor;
    
    @Column
    private String editorial;
    
    @Column
    private String anioPublicacion; // Antes: añopublicacion
    
    @Column
    private Integer numEjemplares; // Antes: numejemplares

    // Getters y Setters
    public Long getIsbn() { return isbn; }
    public void setIsbn(Long isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public String getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(String anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public Integer getNumEjemplares() { return numEjemplares; }
    public void setNumEjemplares(Integer numEjemplares) { this.numEjemplares = numEjemplares; }
}