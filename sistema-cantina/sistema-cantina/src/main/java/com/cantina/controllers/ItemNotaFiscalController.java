package com.cantina.controllers;

import com.cantina.entities.ItemNotaFiscal;
import com.cantina.entities.Produto;
import com.cantina.services.ItemNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public ResponseEntity<ItemNotaFiscal> atualizar(@PathVariable Long id, @RequestBody ItemNotaFiscal itemNotaFiscal) {
        ItemNotaFiscal itemNotaFiscalAtualizado = itemNotaFiscalService.atualizar(id, itemNotaFiscal);
        return ResponseEntity.ok(itemNotaFiscalAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        itemNotaFiscalService.excluir(id);
    }
}