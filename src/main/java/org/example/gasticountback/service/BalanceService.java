package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.VerBalanceDTO;
import org.example.gasticountback.entity.Balance;
import org.example.gasticountback.entity.Participante;
import org.example.gasticountback.repository.IBalanceRepository;
import org.example.gasticountback.repository.IParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private IBalanceRepository balanceRepository;

    @Autowired
    private IParticipanteRepository participanteRepository;

    public List<VerBalanceDTO> verBalances(Integer grupoId) {
        List<Balance> balances = balanceRepository.findByGrupoId(grupoId);
        List<Participante> participantes = participanteRepository.findByGrupoId(grupoId);
        List<VerBalanceDTO> verBalanceDTOList = new ArrayList<>();

        Participante participanteConId1 = participanteRepository.findById(1).orElse(null);

        for (Balance balance : balances) {
            for (Participante participanteDebe : balance.getParticipantesDebe()) {
                VerBalanceDTO dto = new VerBalanceDTO();
                dto.setNombre_participante_debe(participanteDebe.getNombre());
                dto.setCantidad_debe(balance.getTotal());

                for (Participante participanteRecibe : balance.getParticipantesRecibe()) {
                    dto.setNombre_participante_recibe(participanteRecibe.getNombre());
                }

                if (participanteConId1 != null) {
                    dto.setNombre_participante_recibe(participanteConId1.getNombre());
                }

                verBalanceDTOList.add(dto);
            }
        }
        return verBalanceDTOList;
    }
}