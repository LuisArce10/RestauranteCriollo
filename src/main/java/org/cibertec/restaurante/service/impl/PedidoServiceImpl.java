package org.cibertec.restaurante.service.impl;

import org.cibertec.restaurante.entity.PedidoEntity;
import org.cibertec.restaurante.repository.PedidoRepository;
import org.cibertec.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<PedidoEntity> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<PedidoEntity> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public PedidoEntity save(PedidoEntity pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}