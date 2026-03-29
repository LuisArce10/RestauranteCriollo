package org.cibertec.restaurante.service;

import org.cibertec.restaurante.entity.PedidoEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<PedidoEntity> findAll();
    Optional<PedidoEntity> findById(Long id);
    PedidoEntity save(PedidoEntity pedido);
    void deleteById(Long id);
}