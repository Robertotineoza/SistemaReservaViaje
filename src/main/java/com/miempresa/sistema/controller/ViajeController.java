package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.service.AutobusService;
import com.miempresa.sistema.service.ChoferService;
import com.miempresa.sistema.service.ViajeService;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViajeController {

    private final ViajeService viajeService;
    private final AutobusService autobusService;
    private final ChoferService choferService;

    public ViajeController(ViajeService viajeService, AutobusService autobusService, ChoferService choferService) {
        this.viajeService = viajeService;
        this.autobusService = autobusService;
        this.choferService = choferService;
    }

    @GetMapping("/formviajes")
    public String mostrarFormulario(Model model) {
        model.addAttribute("viaje", new Viaje());
        model.addAttribute("viajes", viajeService.obtenerTodos());
        model.addAttribute("autobuses", autobusService.obtenerTodos());
        model.addAttribute("choferes", choferService.obtenerTodos());
        return "viajes";
    }

    @PostMapping("/registrarviajes")
public String registrarViaje(@ModelAttribute Viaje viaje) {
    viajeService.guardar(viaje);
    return "redirect:/formviajes"; 
}

@GetMapping("/viajesdisponibles")
public String filtrar(
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
    @RequestParam(required = false) String origen,
    @RequestParam(required = false) String destino,
    Model model
) {
    model.addAttribute("viajes", viajeService.filtrarViajes(fecha, origen, destino));
    model.addAttribute("fecha", fecha);
    model.addAttribute("origen", origen);
    model.addAttribute("destino", destino);
    return "reserva"; 
}


}
