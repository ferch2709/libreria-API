package com.api.libreria.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.repositories.IPrestamoRepository; // Asegúrate de crear este repositorio

@Service
public class PrestamoService {
    
    @Autowired
    private IPrestamoRepository prestamoRepository;

    public ArrayList<PrestamoModel> obtenerPrestamos() {
        return (ArrayList<PrestamoModel>) prestamoRepository.findAll();
    }

    public PrestamoModel guardarPrestamo(PrestamoModel prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Optional<PrestamoModel> obtenerPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    // Nota: Reemplacé "buscarPorAutor" por un ejemplo útil para préstamos,
    // como buscar todos los préstamos de un usuario específico.
    // (Para que esto no marque error, deberás agregar findByUsuarioId en el IPrestamoRepository)
    public ArrayList<PrestamoModel> buscarPorUsuarioId(Long usuarioId) {
    return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public PrestamoModel actualizarPrestamo(PrestamoModel request, Long id) {
        PrestamoModel prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el préstamo con ID: " + id));
        
        // Actualizamos los campos de Préstamo
        prestamo.setLibro(request.getLibro());
        prestamo.setUsuario(request.getUsuario());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setFechaDevolucion(request.getFechaDevolucion());
        
        return prestamoRepository.save(prestamo);
    }

    public boolean eliminarPrestamo(Long id){
        try {
            prestamoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}