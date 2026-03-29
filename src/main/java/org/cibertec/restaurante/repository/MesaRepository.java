package org.cibertec.restaurante.repository;

import org.cibertec.restaurante.entity.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {
}