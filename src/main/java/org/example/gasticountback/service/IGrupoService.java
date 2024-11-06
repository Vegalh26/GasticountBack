package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.GrupoCrearDTO;
import org.example.gasticountback.DTOs.GrupoListarDTO;

import java.util.List;

public interface IGrupoService {

    GrupoCrearDTO saveGrupo(GrupoCrearDTO grupoCrearDTO);

    List<GrupoListarDTO> findGrupos(Integer idUsuario);

}