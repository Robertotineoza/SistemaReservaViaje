package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Chofer;
import com.miempresa.sistema.service.ChoferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChoferController {

    private final ChoferService choferService;

    public ChoferController(ChoferService choferService) {
        this.choferService = choferService;
    }

    @GetMapping("/choferes")
    public String mostrarFormulario(Model model) {
        model.addAttribute("chofer", new Chofer());
        model.addAttribute("choferes", choferService.obtenerTodos());
        return "choferes";
    }

    @PostMapping("/choferes")
    public String registrarChofer(@ModelAttribute Chofer chofer, Model model) {
        if (choferService.existeCodigo(chofer.getCodigo())) {
            model.addAttribute("error", "El código ya está registrado.");
        } else {
            choferService.guardar(chofer);
        }
        return "redirect:/choferes";
    }
}
