package org.example.gasticountback.DTOs;

import lombok.Data;

@Data
public class GastosListarDTO {
    private Integer id;
    private String concepto;
    private String categoria;
    private Double precio;
    private String usuario;

}