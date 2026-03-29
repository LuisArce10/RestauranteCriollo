package org.cibertec.restaurante.repository;

import org.cibertec.restaurante.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}