package com.javanauta.usuario.infrastructure.security.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<com.javanauta.usuario.infrastructure.entity.Usuario,Long> {

    boolean existsByEmail(String email);

    Optional<com.javanauta.usuario.infrastructure.entity.Usuario>findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}

