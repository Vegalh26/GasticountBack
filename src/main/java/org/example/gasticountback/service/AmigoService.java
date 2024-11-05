package org.example.gasticountback.service;

import org.example.gasticountback.entity.Amigo;
import org.example.gasticountback.repository.IAmigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmigoService implements IAmigoService{

    @Autowired
    private IAmigoRepository amigoRepository;

    @Override
    public List<Amigo> findAmigos(Integer idUsuario) {
        return amigoRepository.findAmigosByUsuarioId(idUsuario);
    }
}
