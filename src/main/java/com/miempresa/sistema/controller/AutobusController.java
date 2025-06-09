
package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.service.AutobusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AutobusController {

    private final AutobusService autobusService;

    public AutobusController(AutobusService autobusService) {
        this.autobusService = autobusService;
    }

    @GetMapping("/autobuses")
    public String mostrarFormulario(Model model) {
        model.addAttribute("autobus", new Autobus());
        model.addAttribute("autobuses", autobusService.obtenerTodos());
        return "autobuses";
    }

    @PostMapping("/registro-autobus")
    public String registrarAutobus(@ModelAttribute Autobus autobus, Model model) {
        if (autobusService.existePlaca(autobus.getPlaca())) {
            model.addAttribute("error", "La placa ya está registrada.");
        } else {
            autobusService.guardar(autobus);
        }
        return "redirect:/autobuses";
    }
}
