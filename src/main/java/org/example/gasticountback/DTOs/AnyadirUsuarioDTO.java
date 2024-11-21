package org.example.gasticountback.DTOs;

import lombok.Data;

@Data
public class AnyadirUsuarioDTO {
    private Integer id;
    private String nombre;
    private Integer grupoId;
    private String concepto;
}
