package com.example.erp.repository;

import com.example.erp.Entity.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollaborateurRepository extends JpaRepository<Collaborateur,Long> {
    Optional<Collaborateur> findByUsername(String username);
}
