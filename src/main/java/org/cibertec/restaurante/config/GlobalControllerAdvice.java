package org.cibertec.restaurante.config;

import org.cibertec.restaurante.entity.UsuarioEntity;
import org.cibertec.restaurante.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UsuarioService usuarioService;

    public GlobalControllerAdvice(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute
    public void addUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            Optional<UsuarioEntity> usuarioOpt = usuarioService.findByEmail(auth.getName());
            usuarioOpt.ifPresent(usuario -> {
                model.addAttribute("usuarioNombre", usuario.getNombre());
                model.addAttribute("usuarioEmail", usuario.getEmail());
                model.addAttribute("usuarioRol", usuario.getRol().getNombre());
            });
        }
    }
}
