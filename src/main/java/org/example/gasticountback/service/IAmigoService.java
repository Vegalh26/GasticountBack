package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.AmigosListarDTO;

import java.util.List;

public interface IAmigoService {
    List<AmigosListarDTO> findAmigos(Integer idUsuario);
}