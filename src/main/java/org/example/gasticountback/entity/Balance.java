package org.example.gasticountback.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="balances")
public class Balance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="total", nullable=false)
    private Double total;

    @ManyToOne
    @JoinColumn(name="grupo_id", nullable=false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "deudor_id", nullable = false)
    private Usuario deudor;

    @ManyToOne
    @JoinColumn(name = "pertenecedor_id", nullable = false)
    private Usuario pertenecedor;
}
