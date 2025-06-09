package com.miempresa.sistema.controller;

import com.miempresa.sistema.dto.RegistroDTO;
import com.miempresa.sistema.model.InicioSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.InicioSesionRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private InicioSesionRepository inicioSesionRepo;

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody RegistroDTO dto) {
        try {
            if (inicioSesionRepo.existsByEmail(dto.getEmail())) {
                return ResponseEntity.badRequest().body("El correo ya está registrado.");
            }

            InicioSesion credenciales = new InicioSesion();
            credenciales.setEmail(dto.getEmail());
            credenciales.setPassword(dto.getPassword()); 
            credenciales.setRol("USUARIO");
            inicioSesionRepo.save(credenciales);

            Usuario usuario = new Usuario();
            usuario.setNombre(dto.getNombre());
            usuario.setTelefono(dto.getTelefono());
            usuario.setInicioSesion(credenciales);
            usuarioRepo.save(usuario);

            return ResponseEntity.ok("Usuario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar: " + e.getMessage());
        }
    }
}
