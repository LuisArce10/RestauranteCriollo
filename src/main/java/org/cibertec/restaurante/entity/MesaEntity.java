package org.cibertec.restaurante.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "mesa")
public class MesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", nullable = false, unique = true)
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoMesa estado;

    @OneToMany(mappedBy = "mesa")
    private List<PedidoEntity> pedidos;

    public enum EstadoMesa {
        LIBRE, OCUPADA
    }
}