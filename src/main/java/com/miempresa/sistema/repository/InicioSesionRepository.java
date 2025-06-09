
package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.InicioSesion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InicioSesionRepository extends JpaRepository<InicioSesion, Integer> {
  Optional<InicioSesion> findByEmail(String email);
   boolean existsByEmail(String email);
}