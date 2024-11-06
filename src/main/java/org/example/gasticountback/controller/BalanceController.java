package org.example.gasticountback.controller;

import lombok.AllArgsConstructor;
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

    @GetMapping("/{grupoId}")
    public List<VerBalanceDTO> verBalances(@PathVariable Integer grupoId) {
        return balanceService.verBalances(grupoId);
    }
}