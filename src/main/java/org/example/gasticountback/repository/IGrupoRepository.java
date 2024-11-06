package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Integer> {
}