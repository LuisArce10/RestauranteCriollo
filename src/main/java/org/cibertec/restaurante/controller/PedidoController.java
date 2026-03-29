package org.cibertec.restaurante.controller;

import org.cibertec.restaurante.entity.PedidoEntity;
import org.cibertec.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String listarPedidos(Model model) {
        List<PedidoEntity> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoPedido(Model model) {
        model.addAttribute("pedido", new PedidoEntity());
        return "pedidos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute("pedido") PedidoEntity pedido) {
        pedidoService.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable Long id, Model model) {
        Optional<PedidoEntity> pedido = pedidoService.findById(id);
        pedido.ifPresent(value -> model.addAttribute("pedido", value));
        return "pedidos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos";
    }
}