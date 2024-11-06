package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBalanceRepository extends JpaRepository<Balance, Integer> {
    List<Balance> findByGrupoId(Integer grupoId);
}