package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.UsuarioDatosDTO;
import org.example.gasticountback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public UsuarioDatosDTO getUsuarioById(@PathVariable Integer idUsuario) {
        return usuarioService.mostrarDatosUsuario(idUsuario);
    }
}
