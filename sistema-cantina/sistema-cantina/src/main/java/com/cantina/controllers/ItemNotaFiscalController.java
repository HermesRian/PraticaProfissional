package com.cantina.controllers;

import com.cantina.entities.ItemNotaFiscal;
import com.cantina.services.ItemNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-nota-fiscal")
public class ItemNotaFiscalController {

    @Autowired
    private ItemNotaFiscalService itemNotaFiscalService;

    @GetMapping
    public List<ItemNotaFiscal> listarTodos() {
        return itemNotaFiscalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemNotaFiscal buscarPorId(@PathVariable Long id) {
        return itemNotaFiscalService.buscarPorId(id);
    }

    @PostMapping
    public ItemNotaFiscal salvar(@RequestBody ItemNotaFiscal itemNotaFiscal) {
        return itemNotaFiscalService.salvar(itemNotaFiscal);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        itemNotaFiscalService.excluir(id);
    }
}