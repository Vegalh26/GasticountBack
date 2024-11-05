package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGastoRepository extends JpaRepository<Gasto, Integer> {
}