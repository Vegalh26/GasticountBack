package org.example.gasticountback.DTOs;

import lombok.Data;
import org.example.gasticountback.enumerar.Categoria;

@Data
public class AnyadirGastoDTO {
    private Categoria categoria;
    private String fecha;
    private String concepto;
    private Double precio;
    private Integer grupoId;
    private Integer usuarioId;
}