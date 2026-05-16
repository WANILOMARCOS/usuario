package com.javanauta.usuario.infrastructure.security.repository;

import com.Javanauta.apredendoSpring.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {}

