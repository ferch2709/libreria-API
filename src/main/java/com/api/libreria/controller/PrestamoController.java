package com.api.libreria.controller;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.services.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    
    @Autowired
    private PrestamoService prestamoService;

    // Obtener todos los préstamos
    @GetMapping
    public ArrayList<PrestamoModel> obtenerPrestamos() {
        return this.prestamoService.obtenerPrestamos();
    }

    // Crear un nuevo préstamo
    @PostMapping
    public PrestamoModel guardarPrestamo(@RequestBody PrestamoModel prestamo) {
        return this.prestamoService.guardarPrestamo(prestamo);
    }

    // Obtener un préstamo por su ID
    @GetMapping("/{id}")
    public Optional<PrestamoModel> obtenerPorId(@PathVariable("id") Long id) {
        return this.prestamoService.obtenerPorId(id);
    }

    // Obtener préstamos de un usuario específico (Equivalente al de autor)
    @GetMapping("/usuario/{usuarioId}")
    public ArrayList<PrestamoModel> buscarPorUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
        // IMPORTANTE: Asegúrate de descomentar el método buscarPorUsuarioId en PrestamoService 
        // que te dejé comentado en el paso anterior.
        return this.prestamoService.buscarPorUsuarioId(usuarioId);
    }

    // Actualizar un préstamo (por ejemplo, para registrar la fecha de devolución)
    @PutMapping("/{id}")
    public PrestamoModel actualizarPrestamo(@RequestBody PrestamoModel request, @PathVariable("id") Long id) {
        return this.prestamoService.actualizarPrestamo(request, id);
    }

    // Eliminar un registro de préstamo
    @DeleteMapping("/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.prestamoService.eliminarPrestamo(id);
        if (ok) {
            return "Se eliminó el préstamo con ID: " + id;
        } else {
            return "No pudo eliminar el préstamo con ID: " + id;
        }
    }
}