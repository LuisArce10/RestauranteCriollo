package org.cibertec.restaurante.repository;

import org.cibertec.restaurante.entity.PlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<PlatoEntity, Long> {
}