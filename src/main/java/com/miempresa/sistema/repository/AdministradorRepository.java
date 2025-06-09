
package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Administrador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByInicioSesionEmail(String email);
}