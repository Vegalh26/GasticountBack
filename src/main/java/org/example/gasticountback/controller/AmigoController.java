package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.entity.Amigo;
import org.example.gasticountback.service.AmigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/amigos")
public class AmigoController {

    @Autowired
    private AmigoService amigoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Amigo> listarAmigos(@PathVariable Integer idUsuario) {
        List<Amigo> amigos = amigoService.findAmigos(idUsuario);
        if (amigos != null) {
            return amigos;
        } else {
            return null;
        }
    }
}
