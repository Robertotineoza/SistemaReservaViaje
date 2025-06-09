
package com.miempresa.sistema.model;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String telefono;
    @OneToOne
    @JoinColumn(name = "id_inicio_sesion", unique = true)
    private InicioSesion inicioSesion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public InicioSesion getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(InicioSesion inicioSesion) {
        this.inicioSesion = inicioSesion;
    }
    
    
}