package com.javanauta.usuario.infrastructure.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<com.javanauta.usuario.infrastructure.entity.Telefone, Long> {}

