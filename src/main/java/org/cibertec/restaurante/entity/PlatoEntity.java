package org.cibertec.restaurante.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "plato")
public class PlatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPlato estado;

    @Column(name = "url_imagen")
    private String urlImagen;
    @ManyToOne
    @JoinColumn(name = "categoria_plato_id", nullable = false)
    private CategoriaPlatoEntity categoriaPlato;

    public enum EstadoPlato {
        ACTIVO, INACTIVO
    }
}
