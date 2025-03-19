package com.cantina.controllers;

import com.cantina.entities.ItemNotaFiscal;
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
    public ResponseEntity<List<ItemNotaFiscal>> listarTodos() {
        List<ItemNotaFiscal> itensNotaFiscal = itemNotaFiscalService.listarTodos();
        return ResponseEntity.ok(itensNotaFiscal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemNotaFiscal> buscarPorId(@PathVariable Long id) {
        ItemNotaFiscal itemNotaFiscal = itemNotaFiscalService.buscarPorId(id);
        return ResponseEntity.ok(itemNotaFiscal);
    }

    @PostMapping
    public ResponseEntity<ItemNotaFiscal> salvar(@RequestBody ItemNotaFiscal itemNotaFiscal) {
        ItemNotaFiscal novoItemNotaFiscal = itemNotaFiscalService.salvar(itemNotaFiscal);
        return ResponseEntity.ok(novoItemNotaFiscal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        itemNotaFiscalService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}