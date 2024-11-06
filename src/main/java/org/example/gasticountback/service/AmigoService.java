package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.AmigosListarDTO;
import org.example.gasticountback.entity.Amigo;
import org.example.gasticountback.repository.IAmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmigoService implements IAmigoService {

    @Autowired
    private IAmigoRepository amigoRepository;

    @Override
    public List<AmigosListarDTO> findAmigos(Integer idUsuario) {
        List<Amigo> amigos = amigoRepository.findByUsuarioId(idUsuario);
        List<AmigosListarDTO> amigosListarDTOs = new ArrayList<>();

        for (Amigo amigo : amigos) {
            AmigosListarDTO dto = new AmigosListarDTO();
            dto.setIdAmigo(amigo.getAmigo().getId());
            dto.setNombreAmigo(amigo.getAmigo().getNombre());
            amigosListarDTOs.add(dto);
        }

        return amigosListarDTOs;
    }
}