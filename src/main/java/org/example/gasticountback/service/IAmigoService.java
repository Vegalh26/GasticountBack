package org.example.gasticountback.service;

import org.example.gasticountback.entity.Amigo;

import java.util.List;

public interface IAmigoService {
    List<Amigo> findAmigos(Integer idUsuario);
}
