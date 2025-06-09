package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ViajeRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeService {

    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public List<Viaje> obtenerTodos() {
        return viajeRepository.findAll();
    }

    public void guardar(Viaje viaje) {
        viajeRepository.save(viaje);
    }
    
    public List<Viaje> filtrarViajes(LocalDate fecha, String origen, String destino) {
    return viajeRepository.findByFiltros(fecha, origen, destino);
}
        public Viaje obtenerPorId(Integer id) {
        return viajeRepository.findById(id).orElse(null); 
    }

}
