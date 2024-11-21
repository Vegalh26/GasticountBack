package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
import org.example.gasticountback.DTOs.CalcularBalanceDTO;
import org.example.gasticountback.DTOs.VerBalanceDTO;
import org.example.gasticountback.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    /*
    @GetMapping("/balances/{grupoId}")
    public VerBalanceDTO getBalanceByGrupoId(@PathVariable Integer grupoId) {
        CalcularBalanceDTO calcularBalanceDTO = new CalcularBalanceDTO();
        calcularBalanceDTO.setGrupoId(grupoId);
        return balanceService.calcularBalance(calcularBalanceDTO);
    }
    */
}