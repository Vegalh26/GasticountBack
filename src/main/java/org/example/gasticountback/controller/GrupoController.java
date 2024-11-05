package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.GrupoCrearDTO;
import org.example.gasticountback.DTOs.GrupoListarDTO;
import org.example.gasticountback.entity.Grupo;
import org.example.gasticountback.enumerar.Moneda;
import org.example.gasticountback.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping("/nuevo")
    public GrupoCrearDTO crearGrupo(@RequestBody GrupoCrearDTO grupoCrearDTO) {
        GrupoCrearDTO grupoCreado = grupoService.saveGrupo(grupoCrearDTO);
        if (grupoCreado != null) {
            return grupoCreado;
        } else {
            return null;
        }
    }

    @GetMapping("/{idUsuario}")
    public List<GrupoListarDTO> listarGrupos(@PathVariable Integer idUsuario) {
        List<GrupoListarDTO> grupos = grupoService.findGrupos(idUsuario);
        if (grupos != null) {
            return grupos;
        } else {
            return null;
        }
    }
}
