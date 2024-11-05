package org.example.gasticountback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.example.gasticountback.enumerar.Moneda;

import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="concepto", nullable=false)
    private String concepto;

    @Enumerated(EnumType.STRING)
    @Column(name="moneda", nullable=false)
    private Moneda moneda;

    // Relación de uno a muchos con la tabla participantes
    // (un grupo puede tener muchos participantes / un participante solo puede pertenecer a un grupo)
    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(name="id_grupo")
    private Set<Participante> participantes;
}
