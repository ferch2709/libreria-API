package com.api.libreria.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.libreria.models.UsuarioModel;
import com.api.libreria.repositories.IUsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel actualizarUsuario(UsuarioModel request, Long id) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el usuario con ID: " + id));
        
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setContrasena(request.getContrasena());
        usuario.setRol(request.getRol());
        usuario.setEstado(request.getEstado());
        
        return usuarioRepository.save(usuario);
    }

    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}