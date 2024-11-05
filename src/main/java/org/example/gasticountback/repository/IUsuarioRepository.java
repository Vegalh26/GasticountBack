package org.example.gasticountback.repository;

import org.example.gasticountback.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findById(Integer idUsuario);
}