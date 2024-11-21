package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.*;
import org.example.gasticountback.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/detalles/{grupoId}")
    public GrupoListarDTO verGrupo(@PathVariable Integer grupoId) {
        GrupoListarDTO grupoListarDTO = grupoService.verGrupo(grupoId);
        if (grupoListarDTO != null) {
            return grupoListarDTO;
        } else {
            return null;
        }
    }

    @PostMapping("/nuevo")
    public GrupoCrearDTO crearGrupo(@RequestBody GrupoCrearDTO grupoCrearDTO) {
        GrupoCrearDTO grupoCreado = grupoService.saveGrupo(grupoCrearDTO);
        if (grupoCreado != null) {
            return grupoCreado;
        } else {
            return null;
        }
    }


    @PutMapping("/{grupoId}/participantes/{usuarioId}")
    public AnyadirUsuarioDTO anyadirParticipante(@PathVariable Integer grupoId, @PathVariable Integer usuarioId) {
        AnyadirUsuarioDTO participante = grupoService.anyadirUsuarioGrupo(grupoId, usuarioId);
        if (participante != null) {
            return participante;
        } else {
            return null;
        }
    }


    @GetMapping("/{grupoId}/participantes")
    public List<UsuariosListarDTO> verParticipantesGrupo(@PathVariable Integer grupoId) {
        List<UsuariosListarDTO> participantes = grupoService.verUsuariosGrupo(grupoId);
        if (participantes != null) {
            return participantes;
        } else {
            return null;
        }
    }


    @DeleteMapping("/participantes/eliminar")
    public List<UsuariosListarDTO> eliminarParticipante(@RequestBody EliminarUsuarioDTO eliminarUsuarioDTO) {
        return grupoService.eliminarUsuariosGrupo(eliminarUsuarioDTO);
    }


    @GetMapping("/{idUsuario}")
    public List<GrupoListarDTO> listarGrupos(@PathVariable Integer idUsuario) {
        return grupoService.findGrupos(idUsuario);
    }



    // grupo con id más alto
    @GetMapping("/ultimo")
    public GrupoId grupoUltimo() {
        return grupoService.obtenerGrupoConIdMasAlto();
    }

}
