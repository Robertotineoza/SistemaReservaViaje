package com.miempresa.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^CH-[0-9]{4}$", message = "El código debe tener el formato CH-0000")
    @Column(unique = true, nullable = false)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
