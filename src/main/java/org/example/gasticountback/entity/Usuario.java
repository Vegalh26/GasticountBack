package org.example.gasticountback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name="nombreUsuario")
    private String nombreUsuario;

    @Column(name="telefono")
    private String telefono;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="foto")
    private String foto;

    // TODO Tabla amigos
    // Relación de uno a muchos con la tabla amigos
    // (un usuario puede tener muchos amigos / una relación de amigo solo puede tener un usuario)
    @OneToMany(mappedBy = "usuario", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<Amigo> amigos;

    // Relación de uno a muchos con la tabla amigos
    // (un usuario puede tener muchos amigos / una relación de amigo solo puede tener un usuario)
    @OneToMany(mappedBy = "usuario", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private Set<Amigo> amigos2;

    // TODO: Tabla grupos
    // Relación de muchos a muchos con la tabla grupos
    // (un usuario puede pertenecer a muchos grupos / un grupo puede tener muchos usuarios)
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos;

    // TODO Tabla balances
    @OneToMany(mappedBy = "usuario", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    private List<Balance> balances;
}
