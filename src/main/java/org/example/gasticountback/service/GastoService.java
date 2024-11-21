package org.example.gasticountback.service;

import org.example.gasticountback.DTOs.AnyadirGastoDTO;
import org.example.gasticountback.DTOs.GastosListarDTO;
import org.example.gasticountback.entity.Gasto;
import org.example.gasticountback.entity.Grupo;
import org.example.gasticountback.entity.Usuario;
import org.example.gasticountback.repository.IGastoRepository;
import org.example.gasticountback.repository.IGrupoRepository;
import org.example.gasticountback.repository.IBalanceRepository;
import org.example.gasticountback.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GastoService {

    @Autowired
    private IGastoRepository gastoRepository;

    @Autowired
    private IGrupoRepository grupoRepository;

    @Autowired
    private IBalanceRepository balanceRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<GastosListarDTO> verGastos(Integer grupoId) {
        List<Gasto> gastos = gastoRepository.findByGrupoId(grupoId);
        List<GastosListarDTO> gastosListarDTOS = new ArrayList<>();

        for (Gasto gasto : gastos) {
            GastosListarDTO gastosListarDTO = new GastosListarDTO();
            gastosListarDTO.setId(gasto.getId());
            gastosListarDTO.setConcepto(gasto.getConcepto());
            gastosListarDTO.setCategoria(String.valueOf(gasto.getCategoria()));
            gastosListarDTO.setPrecio(gasto.getPrecio());
            gastosListarDTO.setUsuario(gasto.getUsuario().getNombre());
            gastosListarDTOS.add(gastosListarDTO);
        }

        return gastosListarDTOS;
    }


    public List<GastosListarDTO> anyadirGastoGrupo(AnyadirGastoDTO anyadirGastoDTO) {
        Grupo grupo = grupoRepository.findById(anyadirGastoDTO.getGrupoId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(anyadirGastoDTO.getUsuarioId());

        if (grupo != null && usuario != null) {
            Gasto gasto = new Gasto();
            gasto.setCategoria(anyadirGastoDTO.getCategoria());
            gasto.setFecha(anyadirGastoDTO.getFecha());
            gasto.setConcepto(anyadirGastoDTO.getConcepto());
            gasto.setPrecio(anyadirGastoDTO.getPrecio());
            gasto.setGrupo(grupo);
            gasto.setUsuario(usuario);
            Gasto gastoGuardado = gastoRepository.save(gasto);

            System.out.println("Gasto insertado: id: " + gasto.getId() + " / concepto: " + gasto.getConcepto());

            return verGastos(gasto.getGrupo().getId());
        } else {
            return null;
        }
    }


}