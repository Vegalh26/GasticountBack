package org.example.gasticountback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.example.gasticountback.enumerar.Categoria;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="concepto", nullable=false)
    private String concepto;

    @Enumerated(EnumType.STRING)
    @Column(name="categoria", nullable=false)
    private Categoria categoria;

    @Column(name="precio", nullable=false)
    private Double precio;

    @Column(name="fecha", nullable=false)
    private String fecha;

    // Relación de muchos a uno con la tabla participantes
    // (un gasto solo puede tener un participante / un participante puede tener muchos gastos)
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Participante.class)
    @JoinColumn(name="id_participante", referencedColumnName = "id")
    private Participante participante;

    //Relación de muchos a uno con la tabla grupos
    // (un gasto solo puede tener un grupo / un grupo puede tener muchos gastos)
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Grupo.class)
    @JoinColumn(name="id_grupo", referencedColumnName = "id")
    private Grupo grupo;
}
