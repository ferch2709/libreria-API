package com.api.libreria.services;

import java.util.ArrayList;          // Requerido para List
import java.util.Optional;      // Requerido para Optional (el que tienes en rojo)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.libreria.models.LibroModel;
import com.api.libreria.repositories.ILibroRepository; // Requerido para que reconozca el repositorio
@Service
public class LibroService {
    
    @Autowired
    private ILibroRepository libroRepository;

    public ArrayList<LibroModel> obtenerLibros() {
        return (ArrayList<LibroModel>) libroRepository.findAll();
    }

    public LibroModel guardarLibro(LibroModel libro) {
        return libroRepository.save(libro);
    }

    public Optional<LibroModel> obtenerPorId(Long isbn) {
        return libroRepository.findById(isbn);
    }

    public ArrayList<LibroModel> buscarPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    public LibroModel actualizarLibro(LibroModel request, Long isbn) {
        LibroModel libro = libroRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("No existe el libro con ISBN: " + isbn));
        
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setEditorial(request.getEditorial());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setNumEjemplares(request.getNumEjemplares());
        
        return libroRepository.save(libro);
    }

    public boolean eliminarLibro(Long isbn){
        try {
            libroRepository.deleteById(isbn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}