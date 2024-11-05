package org.example.gasticountback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="amigos")
public class Amigo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    // Relación muchos a uno con la tabla usuarios
    // (una relación de amigo solo puede tener un usuario / un usuario puede tener muchos amigos)
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Usuario.class)
    @JoinColumn(name="id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    // Relación muchos a uno con la tabla usuarios
    // (una relación de amigo solo puede tener un usuario / un usuario puede tener muchos amigos)
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Usuario.class)
    @JoinColumn(name="id_amigo", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario amigo;
}
