package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAmigoRepository extends JpaRepository<Amigo, Integer> {
    List<Amigo> findByUsuarioId(Integer idUsuario);
}