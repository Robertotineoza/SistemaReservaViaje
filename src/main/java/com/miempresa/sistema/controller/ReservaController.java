package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.service.ReservaService;
import com.miempresa.sistema.service.ViajeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ViajeService viajeService;

    @PostMapping("/reserva")
    public String registrarReserva(@RequestParam("viajeId") int viajeId,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para reservar.");
            return "redirect:/";
        }

        boolean reservada = reservaService.registrarReserva(usuario.getId(), viajeId);

        if (reservada) {
            redirectAttributes.addFlashAttribute("mensaje", "Reserva realizada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No hay asientos disponibles o hubo un problema.");
        }

        return "redirect:/misreservas";
    }

@GetMapping("/misreservas")
public String verMisReservas(HttpSession session, Model model) {
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null) {
        return "redirect:/login";
    }

    List<Reserva> reservasActivas = reservaService.obtenerReservasPorEstado(usuario, Reserva.Estado.ACTIVA);
    List<Reserva> reservasCanceladas = reservaService.obtenerReservasPorEstado(usuario, Reserva.Estado.CANCELADA);

    model.addAttribute("reservasActivas", reservasActivas);
    model.addAttribute("reservasCanceladas", reservasCanceladas);

    return "mis-reservas";
}

    @PostMapping("/reservas/cancelar/{id}")
    public String cancelarReserva(@PathVariable Integer id,
                                  HttpSession session,
                                  RedirectAttributes attr) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        boolean cancelada = reservaService.cancelarReserva(id, usuario);

        if (cancelada) {
            attr.addFlashAttribute("mensaje", "Reserva cancelada con éxito.");
        } else {
            attr.addFlashAttribute("error", "No se pudo cancelar la reserva.");
        }

        return "redirect:/misreservas";
    }

    @GetMapping("/reserva")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("viajes", viajeService.obtenerTodos());
        return "reserva";
    }
    
    
    

}

