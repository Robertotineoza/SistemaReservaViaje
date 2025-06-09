
package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ReservaRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import com.miempresa.sistema.repository.ViajeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean registrarReserva(int idUsuario, int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElse(null);
        if (viaje == null || viaje.getAutobus() == null) {
            return false;
        }

        int capacidad = viaje.getAutobus().getCapacidad();
        int reservasActuales = reservaRepository.countByViajeIdAndEstado(idViaje, Reserva.Estado.ACTIVA);

        if (reservasActuales >= capacidad) {
            return false;
        }

        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario == null) {
            return false;
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        reserva.setEstado(Reserva.Estado.ACTIVA);

        reservaRepository.save(reserva);
        return true;
    }

    public List<Reserva> obtenerReservasPorEstado(Usuario usuario, Reserva.Estado estado) {
        return reservaRepository.findByUsuarioAndEstado(usuario, estado);
    }


public boolean cancelarReserva(Integer reservaId, Usuario usuario) {
    if (reservaId == null || usuario == null || usuario.getId() == null) {
        return false;
    }

    Optional<Reserva> optional = reservaRepository.findById(reservaId);
    
    if (optional.isEmpty()) {
        return false;
    }

    Reserva reserva = optional.get();
    Usuario reservaUsuario = reserva.getUsuario();

    if (reservaUsuario == null || reservaUsuario.getId() == null) {
        return false;
    }

    if (!Objects.equals(reservaUsuario.getId(), usuario.getId())) {
        return false;
    }

    reserva.setEstado(Reserva.Estado.CANCELADA);
    reservaRepository.save(reserva);
    return true;
}


}
