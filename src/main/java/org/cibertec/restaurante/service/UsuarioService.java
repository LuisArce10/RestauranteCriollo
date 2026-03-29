package org.cibertec.restaurante.service;

import org.cibertec.restaurante.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioEntity> findAll();
    Optional<UsuarioEntity> findById(Long id);
    UsuarioEntity save(UsuarioEntity usuario);
    void deleteById(Long id);

    Optional<UsuarioEntity> findByEmail(String email); // 👈 CAMBIO AQUÍ
}
