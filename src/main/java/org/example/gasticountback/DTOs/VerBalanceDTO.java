package org.example.gasticountback.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class VerBalanceDTO {
    private String nombre_participante_debe;
    private Double cantidad_debe;
    private String nombre_participante_recibe;
}
