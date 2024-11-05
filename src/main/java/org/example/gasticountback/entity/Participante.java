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

    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Usuario.class)
    @JoinColumn(name="email", referencedColumnName = "email", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Grupo.class)
    @JoinColumn(name="id_grupo", referencedColumnName = "id")
    private Grupo grupo;

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_debe",
            joinColumns = @JoinColumn(name = "participante_id"),
            inverseJoinColumns = @JoinColumn(name = "balance_id")
    )
    private List<Balance> balancesDebe;

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_recibe",
            joinColumns = @JoinColumn(name = "participante_id"),
            inverseJoinColumns = @JoinColumn(name = "balance_id")
    )
    private List<Balance> balancesRecibe;
}