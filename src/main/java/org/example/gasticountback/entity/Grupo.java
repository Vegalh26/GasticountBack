package org.example.gasticountback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.example.gasticountback.enumerar.Moneda;

import java.util.List;
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

    // TODO: Tabla balances
    @OneToMany(mappedBy = "grupo", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private List<Balance> balances;

    // TODO: Tabla usuarios
    // Relación de muchos a muchos con la tabla USUARIOS
    // (un grupo puede tener muchos usuarios / un usuario puede pertenecer a muchos grupos)
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;
}
