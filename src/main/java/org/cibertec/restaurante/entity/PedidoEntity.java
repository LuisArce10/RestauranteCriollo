package org.cibertec.restaurante.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private MesaEntity mesa;

    @ManyToOne
    @JoinColumn(name = "mozo_id", nullable = false)
    private UsuarioEntity mozo;

    @ManyToMany
    @JoinTable(
            name = "pedido_platos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "plato_id")
    )
    private List<PlatoEntity> platos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    @Column(name = "fecha", nullable = false)
    private java.time.LocalDateTime fecha; // 👈 Este es el que faltaba

    public enum EstadoPedido {
        PENDIENTE,
        EN_PROCESO,
        LISTO,
        PAGADO
    }
}
