package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.*;
import org.example.gasticountback.entity.Gasto;
import org.example.gasticountback.entity.Grupo;
import org.example.gasticountback.entity.Participante;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.enumerar.Moneda;
import org.example.gasticountback.repository.IGastoRepository;
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

    @Autowired
    private IGastoRepository gastoRepository;

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


    public List<ParticipantesListarDTO> verParticipantesGrupo(Integer idGrupo) {
        Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);
        if (grupo != null) {
            Set<Participante> participantes = grupo.getParticipantes();
            List<ParticipantesListarDTO> participantesListarDTOS = new ArrayList<>();

            for (Participante participante : participantes) {
                ParticipantesListarDTO participantesListarDTO = new ParticipantesListarDTO();
                participantesListarDTO.setConcepto(grupo.getConcepto());
                participantesListarDTO.setNombreParticipante(participante.getNombre());
                participantesListarDTOS.add(participantesListarDTO);
            }

            return participantesListarDTOS;
        } else {
            return null;
        }
    }


    public List<ParticipantesListarDTO> eliminarParticipantesGrupo(EliminarParticipanteDTO eliminarParticipanteDTO) {
        Integer idGrupo = eliminarParticipanteDTO.getGrupoId();
        Integer idParticipante = eliminarParticipanteDTO.getParticipanteId();

        Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);
        Participante participante = participanteRepository.findById(idParticipante).orElse(null);

        if (grupo != null && participante != null && participante.getGrupo().equals(grupo)) {
            participante.setGrupo(null);
            participanteRepository.save(participante);
            System.out.println("Participante eliminado: ID = " + participante.getId() + ", Nombre = " + participante.getNombre() + ", Grupo = " + grupo.getConcepto());
        }

        return verParticipantesGrupo(idGrupo);
    }


    @Override
    public List<GrupoListarDTO> findGrupos(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        if (usuario != null) {
            List<Grupo> grupos = usuario.getGrupos();
            List<GrupoListarDTO> grupoListarDTOS = new ArrayList<GrupoListarDTO>();

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