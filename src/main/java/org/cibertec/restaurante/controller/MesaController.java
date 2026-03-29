package org.cibertec.restaurante.controller;

import org.cibertec.restaurante.entity.MesaEntity;
import org.cibertec.restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        List<MesaEntity> mesas = mesaService.findAll();
        model.addAttribute("mesas", mesas);
        return "mesas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaMesa(Model model) {
        model.addAttribute("mesa", new MesaEntity());
        return "mesas/formulario";
    }

    @PostMapping("/guardar")
    public String guardarMesa(@ModelAttribute("mesa") MesaEntity mesa) {
        mesaService.save(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        Optional<MesaEntity> mesa = mesaService.findById(id);
        mesa.ifPresent(value -> model.addAttribute("mesa", value));
        return "mesas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaService.deleteById(id);
        return "redirect:/mesas";
    }
}