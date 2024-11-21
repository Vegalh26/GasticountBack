package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.UsuarioDatosDTO;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public UsuarioDatosDTO mostrarDatosUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        if (usuario != null) {
            UsuarioDatosDTO usuarioDatosDTO = new UsuarioDatosDTO();
            usuarioDatosDTO.setIdUsuario(usuario.getId());
            usuarioDatosDTO.setNombre(usuario.getNombre());
            usuarioDatosDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDatosDTO.setEmail(usuario.getEmail());
            usuarioDatosDTO.setFoto(usuario.getFoto());
            return usuarioDatosDTO;
        } else {
            return null;
        }
    }
}