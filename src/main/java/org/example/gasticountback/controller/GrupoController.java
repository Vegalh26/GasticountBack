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

    @PostMapping("/nuevo")
    public GrupoCrearDTO crearGrupo(@RequestBody GrupoCrearDTO grupoCrearDTO) {
        GrupoCrearDTO grupoCreado = grupoService.saveGrupo(grupoCrearDTO);
        if (grupoCreado != null) {
            return grupoCreado;
        } else {
            return null;
        }
    }


    @PutMapping("/{grupoId}/participantes/{participanteId}")
    public AnyadirParticipanteDTO anyadirParticipante(@PathVariable Integer grupoId, @PathVariable Integer participanteId) {
        AnyadirParticipanteDTO participante = grupoService.anyadirParticipanteGrupo(grupoId, participanteId);
        if (participante != null) {
            return participante;
        } else {
            return null;
        }
    }


    @GetMapping("/{grupoId}/participantes")
    public List<ParticipantesListarDTO> verParticipantesGrupo(@PathVariable Integer grupoId) {
        List<ParticipantesListarDTO> participantes = grupoService.verParticipantesGrupo(grupoId);
        if (participantes != null) {
            return participantes;
        } else {
            return null;
        }
    }


    @DeleteMapping("/participantes/eliminar")
    public List<ParticipantesListarDTO> eliminarParticipante(@RequestBody EliminarParticipanteDTO eliminarParticipanteDTO) {
        return grupoService.eliminarParticipantesGrupo(eliminarParticipanteDTO);
    }


    @GetMapping("/{idUsuario}")
    public List<GrupoListarDTO> listarGrupos(@PathVariable Integer idUsuario) {
        return grupoService.findGrupos(idUsuario);
    }
}
