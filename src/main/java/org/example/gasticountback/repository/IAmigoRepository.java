package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Amigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAmigoRepository extends JpaRepository<Amigo, Integer> {
    List<Amigo> findAmigosByUsuarioId(Integer idUsuario);
}