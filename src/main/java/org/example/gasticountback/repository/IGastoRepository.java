package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGastoRepository extends JpaRepository<Gasto, Integer> {
    List<Gasto> findByGrupoId(Integer grupoId);

    List<Gasto> findByUsuarioIdAndGrupoId(Integer usuarioId, Integer grupoId);
}