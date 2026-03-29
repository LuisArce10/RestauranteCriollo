package org.cibertec.restaurante.service;

import org.cibertec.restaurante.entity.PlatoEntity;

import java.util.List;
import java.util.Optional;

public interface PlatoService {
    List<PlatoEntity> findAll();
    Optional<PlatoEntity> findById(Long id);
    PlatoEntity save(PlatoEntity plato);
    void deleteById(Long id);
}