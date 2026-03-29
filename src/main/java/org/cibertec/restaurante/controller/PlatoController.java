package org.cibertec.restaurante.controller;

import org.cibertec.restaurante.entity.PlatoEntity;
import org.cibertec.restaurante.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @GetMapping
    public String listarPlatos(Model model) {
        List<PlatoEntity> platos = platoService.findAll();
        model.addAttribute("platos", platos);
        return "platos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoPlato(Model model) {
        model.addAttribute("plato", new PlatoEntity());
        return "platos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarPlato(@ModelAttribute("plato") PlatoEntity plato) {
        platoService.save(plato);
        return "redirect:/platos";
    }

    @GetMapping("/editar/{id}")
    public String editarPlato(@PathVariable Long id, Model model) {
        Optional<PlatoEntity> plato = platoService.findById(id);
        plato.ifPresent(value -> model.addAttribute("plato", value));
        return "platos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable Long id) {
        platoService.deleteById(id);
        return "redirect:/platos";
    }
}