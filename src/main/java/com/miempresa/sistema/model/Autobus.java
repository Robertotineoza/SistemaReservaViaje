
package com.miempresa.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Autobus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[A-Z]{2}-[0-9]{4}$", message = "La placa debe tener el formato AA-1234")
    @Column(unique = true, nullable = false)
    private String placa;

    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
