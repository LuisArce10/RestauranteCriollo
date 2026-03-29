package org.cibertec.restaurante.service.impl;

import org.cibertec.restaurante.entity.PlatoEntity;
import org.cibertec.restaurante.repository.PlatoRepository;
import org.cibertec.restaurante.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoServiceImpl implements PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Override
    public List<PlatoEntity> findAll() {
        return platoRepository.findAll();
    }

    @Override
    public Optional<PlatoEntity> findById(Long id) {
        return platoRepository.findById(id);
    }

    @Override
    public PlatoEntity save(PlatoEntity plato) {
        return platoRepository.save(plato);
    }

    @Override
    public void deleteById(Long id) {
        platoRepository.deleteById(id);
    }
}