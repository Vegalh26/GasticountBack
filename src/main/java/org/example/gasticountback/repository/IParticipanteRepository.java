package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findByGrupoId(Integer grupoId);
}