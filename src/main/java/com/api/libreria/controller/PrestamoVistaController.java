package com.api.libreria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.libreria.models.PrestamoModel;
import com.api.libreria.services.PrestamoService;

@Controller
public class PrestamoVistaController {

    private final PrestamoService prestamoService;

    // Inyección de dependencias por constructor
    public PrestamoVistaController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // Cambiamos la ruta a /vista/prestamos y quitamos "/" para evitar conflictos
    @GetMapping("/vista/prestamos")
    public String mostrarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoService.obtenerPrestamos());
        
        if (!model.containsAttribute("prestamo")) {
            model.addAttribute("prestamo", new PrestamoModel());
        }
        
        // Retorna a la vista HTML que deberá llamarse "prestamos.html"
        return "prestamos"; 
    }

    @PostMapping("/vista/prestamos/guardar")
    public String guardarPrestamo(@ModelAttribute PrestamoModel prestamo, RedirectAttributes atributos) {
        prestamoService.guardarPrestamo(prestamo);
        atributos.addFlashAttribute("mensaje", "Préstamo registrado correctamente.");
        return "redirect:/vista/prestamos";
    }

    // Cambiamos la variable {isbn} por {id}
    @PostMapping("/vista/prestamos/{id}/actualizar")
    public String actualizarPrestamo(@PathVariable Long id, @ModelAttribute PrestamoModel prestamo,
            RedirectAttributes atributos) {
        try {
            prestamoService.actualizarPrestamo(prestamo, id);
            atributos.addFlashAttribute("mensaje", "Préstamo actualizado correctamente.");
        } catch (IllegalArgumentException exception) {
            atributos.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/vista/prestamos";
    }

    // Cambiamos la variable {isbn} por {id}
    @PostMapping("/vista/prestamos/{id}/eliminar")
    public String eliminarPrestamo(@PathVariable Long id, RedirectAttributes atributos) {
        if (prestamoService.eliminarPrestamo(id)) {
            atributos.addFlashAttribute("mensaje", "Préstamo eliminado correctamente.");
        } else {
            atributos.addFlashAttribute("error", "No se pudo eliminar el préstamo.");
        }
        return "redirect:/vista/prestamos";
    }
}