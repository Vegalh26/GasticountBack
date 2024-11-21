package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.AnyadirGastoDTO;
import org.example.gasticountback.DTOs.GastosListarDTO;
import org.example.gasticountback.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping("/{grupoId}")
    public List<GastosListarDTO> verGastosGrupo(@PathVariable Integer grupoId) {
        List<GastosListarDTO> gastos = gastoService.verGastos(grupoId);
        if (gastos != null) {
            return gastos;
        } else {
            return null;
        }
    }


    @PostMapping("/gasto/nuevo")
    public List<GastosListarDTO> anyadirGastoGrupo(@RequestBody AnyadirGastoDTO anyadirGastoDTO) {
        return gastoService.anyadirGastoGrupo(anyadirGastoDTO);
    }


}