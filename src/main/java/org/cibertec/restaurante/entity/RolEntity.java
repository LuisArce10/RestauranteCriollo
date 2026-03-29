package org.cibertec.restaurante.entity;

import jakarta.persistence.*;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre; // debe contener valores como "ADMINISTRADOR", "MOZO", "COCINERO"
}
