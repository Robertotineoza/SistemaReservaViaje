package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutobusRepository extends JpaRepository<Autobus, Integer> {
    boolean existsByPlaca(String placa);
}