package org.cibertec.restaurante.service;

import org.cibertec.restaurante.entity.MesaEntity;

import java.util.List;
import java.util.Optional;

public interface MesaService {
    List<MesaEntity> findAll();
    Optional<MesaEntity> findById(Long id);
    MesaEntity save(MesaEntity mesa);
    void deleteById(Long id);
}