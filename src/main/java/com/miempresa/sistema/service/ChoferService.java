package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Chofer;
import com.miempresa.sistema.repository.ChoferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoferService {

    private final ChoferRepository choferRepository;

    public ChoferService(ChoferRepository choferRepository) {
        this.choferRepository = choferRepository;
    }

    public List<Chofer> obtenerTodos() {
        return choferRepository.findAll();
    }

    public void guardar(Chofer chofer) {
        choferRepository.save(chofer);
    }

    public boolean existeCodigo(String codigo) {
        return choferRepository.existsByCodigo(codigo);
    }
}
