package org.example.gasticountback.entity;

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
@Table(name="participantes")
public class Participante {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombre", nullable=false)
    private String nombre;

    @Column(name="email")
    private String email;

    // Relación de uno a uno con la tabla usuarios
    // (un participante solo puede tener un usuario / un usuario solo puede tener un participante)
    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Usuario.class)
    @JoinColumn(name="email", referencedColumnName = "email", insertable = false, updatable = false)
    private Usuario usuario;

    // Relación de muchos a uno con la tabla grupos
    // (un participante solo puede pertenecer a un grupo / un grupo puede tener muchos participantes)
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Grupo.class)
    @JoinColumn(name="id_grupo", referencedColumnName = "id", insertable = false, updatable = false)
    private Grupo grupo;

    // Relación de muchos a muchos con la tabla balances
    // (un participante que debe puede tener muchos balances / un balance puede tener muchos participantes que deben)
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_debe",
            joinColumns = @JoinColumn(name = "participante_id"),
            inverseJoinColumns = @JoinColumn(name = "balance_id")
    )
    private List<Balance> balancesDebe;

    // Relación de muchos a muchos con la tabla balances
    // (un participante que recibe puede tener muchos balances / un balance puede tener muchos participantes que reciben)
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_recibe",
            joinColumns = @JoinColumn(name = "participante_id"),
            inverseJoinColumns = @JoinColumn(name = "balance_id")
    )
    private List<Balance> balancesRecibe;
}
