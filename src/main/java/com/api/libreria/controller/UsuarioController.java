package com.api.libreria.controller; // Asegúrate de que el paquete coincida con tu estructura

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.libreria.models.UsuarioModel;
import com.api.libreria.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return this.usuarioService.obtenerUsuarios();
    }

    @PostMapping
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> obtenerPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@RequestBody UsuarioModel request, @PathVariable("id") Long id) {
        return this.usuarioService.actualizarUsuario(request, id);
    }

    @DeleteMapping("/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con ID: " + id;
        } else {
            return "No se pudo eliminar el usuario con ID: " + id;
        }
    }
}