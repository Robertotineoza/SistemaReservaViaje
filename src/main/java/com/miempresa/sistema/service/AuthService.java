
package com.miempresa.sistema.service;

import com.miempresa.sistema.model.InicioSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.InicioSesionRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private InicioSesionRepository inicioSesionRepo;
    @Autowired private UsuarioRepository usuarioRepo;

    public Usuario registrarUsuario(String nombre, String telefono, String email, String contraseña) {
        InicioSesion sesion = new InicioSesion();
        sesion.setEmail(email);
        sesion.setPassword(contraseña);
        sesion.setRol("usuario");
        inicioSesionRepo.save(sesion);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setTelefono(telefono);
        usuario.setInicioSesion(sesion);
        return usuarioRepo.save(usuario);
    }

    public Optional<InicioSesion> login(String email, String Password) {
        return inicioSesionRepo.findByEmail(email)
                .filter(i -> i.getPassword().equals(Password));
    }
}