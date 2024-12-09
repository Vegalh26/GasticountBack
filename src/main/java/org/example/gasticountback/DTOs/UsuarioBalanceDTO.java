package org.example.gasticountback.DTOs;

import lombok.Data;

@Data
public class UsuarioBalanceDTO {
    private String nombre;
    private String mensaje;
    private double balance;
    private String foto;

    public UsuarioBalanceDTO(String nombre, String mensaje, double balance, String foto) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.balance = balance;
        this.foto = foto;
    }
}