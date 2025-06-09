package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Reserva.Estado;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByUsuario(Usuario usuario);
    List<Reserva> findByViaje(Viaje viaje);
    long countByViaje(Viaje viaje);
    long countByViajeId(Integer viajeId);
    List<Reserva> findByEstado(Estado estado);

    List<Reserva> findByUsuarioAndEstado(Usuario usuario, Estado estado);
    int countByViajeIdAndEstado(int viajeId, Reserva.Estado estado);
    

}