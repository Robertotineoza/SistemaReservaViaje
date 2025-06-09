package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.InicioSesion;
import com.miempresa.sistema.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
Optional<Usuario> findByInicioSesionEmail(String email);
 Optional<Usuario> findByInicioSesion(InicioSesion inicioSesion); 

}