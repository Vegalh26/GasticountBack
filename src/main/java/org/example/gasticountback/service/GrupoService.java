package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.*;
import org.example.gasticountback.entity.Grupo;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.enumerar.Moneda;
import org.example.gasticountback.repository.IGastoRepository;
import org.example.gasticountback.repository.IGrupoRepository;
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
    private IGastoRepository gastoRepository;


    public GrupoListarDTO verGrupo(Integer grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId).orElse(null);
        if (grupo != null) {
            GrupoListarDTO grupoDetalleDTO = new GrupoListarDTO();
            grupoDetalleDTO.setId(grupo.getId());
            grupoDetalleDTO.setConcepto(grupo.getConcepto());
            return grupoDetalleDTO;
        } else {
            return null;
        }
    }


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


    public AnyadirUsuarioDTO anyadirUsuarioGrupo(Integer idGrupo, Integer idUsuario) {
        Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);
        Usuario usuario = usuarioRepository.findById(idUsuario);

        if (grupo != null && usuario != null) {
            usuario.getGrupos().add(grupo);
            usuarioRepository.save(usuario);
            System.out.println("Usuario añadido: ID = " + usuario.getId() + ", Nombre = " + usuario.getNombre() + ", Grupo = " + grupo.getConcepto());
            AnyadirUsuarioDTO anyadirUsuarioDTO = new AnyadirUsuarioDTO();
            anyadirUsuarioDTO.setId(usuario.getId());
            anyadirUsuarioDTO.setNombre(usuario.getNombre());
            anyadirUsuarioDTO.setGrupoId(grupo.getId());
            anyadirUsuarioDTO.setConcepto(grupo.getConcepto());
            return anyadirUsuarioDTO;
        } else {
            return null;
        }
    }


    public List<UsuariosListarDTO> verUsuariosGrupo(Integer idGrupo) {
        Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);
        if (grupo != null) {
            Set<Usuario> usuarios = grupo.getUsuarios();
            List<UsuariosListarDTO> usuariosListarDTOS = new ArrayList<UsuariosListarDTO>();

            // for para mos
            for (Usuario usuario : usuarios) {
                UsuariosListarDTO usuariosListarDTO = new UsuariosListarDTO();
                usuariosListarDTO.setId(usuario.getId());
                usuariosListarDTO.setNombreUsuario(usuario.getNombre());
                usuariosListarDTO.setFoto(usuario.getFoto());
                usuariosListarDTOS.add(usuariosListarDTO);
            }

            return usuariosListarDTOS;
        } else {
            return null;
        }
    }


    public List<UsuariosListarDTO> eliminarUsuariosGrupo(EliminarUsuarioDTO eliminarUsuarioDTO) {
        Grupo grupo = grupoRepository.findById(eliminarUsuarioDTO.getGrupoId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(eliminarUsuarioDTO.getUsuarioId());

        if (grupo != null && usuario != null) {
            usuario.getGrupos().remove(grupo);
            usuarioRepository.save(usuario);
            System.out.println("Usuario eliminado: ID = " + usuario.getId() + ", Nombre = " + usuario.getNombre() + ", Grupo = " + grupo.getConcepto());
            Set<Usuario> usuarios = grupo.getUsuarios();
            List<UsuariosListarDTO> usuariosListarDTOS = new ArrayList<UsuariosListarDTO>();

            for (Usuario usuario1 : usuarios) {
                UsuariosListarDTO usuariosListarDTO = new UsuariosListarDTO();
                usuariosListarDTO.setId(usuario1.getId());
                usuariosListarDTO.setNombreUsuario(usuario1.getNombre());
                usuariosListarDTOS.add(usuariosListarDTO);
            }

            return usuariosListarDTOS;
        } else {
            return null;
        }
    }


    @Override
    public List<GrupoListarDTO> findGrupos(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario);
        if (usuario != null) {
            Set<Grupo> grupos = usuario.getGrupos();
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


    public GrupoId obtenerGrupoConIdMasAlto() {
        List<Grupo> grupos = grupoRepository.findAll();
        Integer idMasAlto = 0;
        for (Grupo grupo : grupos) {
            if (grupo.getId() > idMasAlto) {
                idMasAlto = grupo.getId();
            }
        }
        GrupoId grupoId = new GrupoId(idMasAlto);
        return grupoId;
    }

}