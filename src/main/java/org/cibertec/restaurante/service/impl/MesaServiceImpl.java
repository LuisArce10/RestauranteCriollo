package org.cibertec.restaurante.service.impl;

import org.cibertec.restaurante.entity.MesaEntity;
import org.cibertec.restaurante.repository.MesaRepository;
import org.cibertec.restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public List<MesaEntity> findAll() {
        return mesaRepository.findAll();
    }

    @Override
    public Optional<MesaEntity> findById(Long id) {
        return mesaRepository.findById(id);
    }

    @Override
    public MesaEntity save(MesaEntity mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public void deleteById(Long id) {
        mesaRepository.deleteById(id);
    }
}