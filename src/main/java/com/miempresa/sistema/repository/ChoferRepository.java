package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoferRepository extends JpaRepository<Chofer, Integer> {
    boolean existsByCodigo(String codigo);
}