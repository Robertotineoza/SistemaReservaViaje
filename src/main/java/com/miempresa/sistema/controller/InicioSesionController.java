package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.InicioSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.InicioSesionRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InicioSesionController {

    @Autowired
    private InicioSesionRepository inicioSesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                                @RequestParam("password") String password,
                                HttpSession session,
                                Model model) {

        InicioSesion usuarioSesion = inicioSesionRepository.findByEmail(email).orElse(null);

        if (usuarioSesion != null && usuarioSesion.getPassword().equals(password)) {
            Usuario usuario = usuarioRepo.findByInicioSesion(usuarioSesion).orElse(null);
            if (usuario == null) {
                model.addAttribute("error", "Usuario no encontrado");
                return "login";
            }

            session.setAttribute("usuario", usuario);

            if ("administrador".equalsIgnoreCase(usuarioSesion.getRol())) {
                return "redirect:/paneladmin";
            } else if ("usuario".equalsIgnoreCase(usuarioSesion.getRol())) {
                return "redirect:/panelusuario";
            } else {
                model.addAttribute("error", "Rol no válido");
                return "login";
            }
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/paneladmin")
    public String mostrarPanelAdmin(HttpSession session, Model model) {
        Object usuario = session.getAttribute("usuario");

        if (usuario == null) {
            model.addAttribute("error", "Debes iniciar sesión");
            return "login";
        }

        InicioSesion sesion = ((Usuario) usuario).getInicioSesion();
        if (!"administrador".equalsIgnoreCase(sesion.getRol())) {
            model.addAttribute("error", "Acceso denegado");
            return "login";
        }

        return "paneladmin";
    }

    @GetMapping("/panelusuario")
    public String mostrarPanelUsuario(HttpSession session, Model model) {
        Object usuario = session.getAttribute("usuario");

        if (usuario == null) {
            model.addAttribute("error", "Debes iniciar sesión");
            return "login";
        }

        InicioSesion sesion = ((Usuario) usuario).getInicioSesion();
        if (!"usuario".equalsIgnoreCase(sesion.getRol())) {
            model.addAttribute("error", "Acceso denegado");
            return "login";
        }

        return "panelusuario";
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/")
    public String mostrarLoginDesdeRaiz() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @GetMapping("/login")
public String mostrarLogin() {
    return "login";          
}

