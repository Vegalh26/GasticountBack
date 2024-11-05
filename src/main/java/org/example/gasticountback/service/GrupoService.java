package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.AnyadirParticipanteDTO;
import org.example.gasticountback.DTOs.GrupoCrearDTO;
import org.example.gasticountback.DTOs.GrupoListarDTO;
import org.example.gasticountback.entity.Grupo;
import org.example.gasticountback.entity.Participante;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.enumerar.Moneda;
import org.example.gasticountback.repository.IGrupoRepository;
import org.example.gasticountback.repository.IParticipanteRepository;
import org.example.gasticountback.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GrupoService implements IGrupoService {

    @Autowired
    private IGrupoRepository grupoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IParticipanteRepository participanteRepository;

    @Override
    public GrupoCrearDTO saveGrupo(GrupoCrearDTO grupoCrearDTO) {
        Grupo grupo = new Grupo();
        grupo.setConcepto(grupoCrearDTO.getConcepto());
        grupo.setMoneda(Moneda.valueOf(grupoCrearDTO.getMoneda()));
        Grupo grupoGuardado = grupoRepository.save(grupo);
        if (grupoGuardado != null) {
            GrupoCrearDTO grupoCreado = new GrupoCrearDTO();
            grupoCreado.setId(grupoGuardado.getId());
            grupoCreado.setConcepto(grupoGuardado.getConcepto());
            grupoCreado.setMoneda(String.valueOf(grupoGuardado.getMoneda()));
            return grupoCreado;
        } else {
            return null;
        }
    }


    public AnyadirParticipanteDTO anyadirParticipanteGrupo(Integer idGrupo, Integer idParticipante) {
        Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);
        Participante participante = participanteRepository.findById(idParticipante).orElse(null);

        if (grupo != null && participante != null) {
            participante.setGrupo(grupo);
            participanteRepository.save(participante);
            AnyadirParticipanteDTO anyadirParticipanteDTO = new AnyadirParticipanteDTO();
            anyadirParticipanteDTO.setId(participante.getId());
            anyadirParticipanteDTO.setNombre(participante.getNombre());
            anyadirParticipanteDTO.setGrupoId(grupo.getId());
            anyadirParticipanteDTO.setConcepto(grupo.getConcepto());
            return anyadirParticipanteDTO;
        } else {
            return null;
        }
    }





    @Override
    public List<GrupoListarDTO> findGrupos(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        if (usuario != null) {
            Set<Grupo> grupos = usuario.getGrupos();
            List<GrupoListarDTO> grupoListarDTOS = new ArrayList<>();

            for (Grupo grupo : grupos) {
                GrupoListarDTO grupoListarDTO = new GrupoListarDTO();
                grupoListarDTO.setId(grupo.getId());
                grupoListarDTO.setConcepto(grupo.getConcepto());
                grupoListarDTOS.add(grupoListarDTO);
            }

            return grupoListarDTOS;
        } else {
            return null;
        }
    }
}