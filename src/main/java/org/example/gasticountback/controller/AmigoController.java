package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.AmigosListarDTO;
import org.example.gasticountback.service.AmigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/amigos")
public class AmigoController {

    @Autowired
    private AmigoService amigoService;

    @GetMapping("/{idUsuario}")
    public List<AmigosListarDTO> listarAmigos(@PathVariable Integer idUsuario) {
        List<AmigosListarDTO> amigos = amigoService.findAmigos(idUsuario);
        return amigos;
    }
}