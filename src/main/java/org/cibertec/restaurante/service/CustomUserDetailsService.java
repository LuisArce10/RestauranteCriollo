package org.cibertec.restaurante.service;

import org.cibertec.restaurante.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioService.findByEmail(email) // <-- debe existir este método
                .map(usuario -> User.builder()
                        .username(usuario.getEmail()) // <-- login con email
                        .password(usuario.getPassword())
                        .roles(usuario.getRol().getNombre()) // Ej: ADMINISTRADOR
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }
}
