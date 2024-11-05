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

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_debe",
            joinColumns = @JoinColumn(name = "balance_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantesDebe;

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "balance_participante_recibe",
            joinColumns = @JoinColumn(name = "balance_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id")
    )
    private List<Participante> participantesRecibe;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, targetEntity = Grupo.class)
    @JoinColumn(name="id_grupo", referencedColumnName = "id", insertable = false, updatable = false)
    private Grupo grupo;
}
