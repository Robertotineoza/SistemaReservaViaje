package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.repository.AutobusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutobusService {

    private final AutobusRepository autobusRepository;

    public AutobusService(AutobusRepository autobusRepository) {
        this.autobusRepository = autobusRepository;
    }

    public List<Autobus> obtenerTodos() {
        return autobusRepository.findAll();
    }

    public void guardar(Autobus autobus) {
        autobusRepository.save(autobus);
    }

    public boolean existePlaca(String placa) {
        return autobusRepository.existsByPlaca(placa);
    }
}
