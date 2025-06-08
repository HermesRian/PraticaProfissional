package com.cantina.controllers;

import com.cantina.entities.Fornecedor;
import com.cantina.exceptions.DuplicateCnpjException;
import com.cantina.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor novoFornecedor = fornecedorService.salvar(fornecedor);
            return ResponseEntity.ok(novoFornecedor);
        } catch (DuplicateCnpjException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro inesperado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        fornecedorService.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor fornecedorExistente = fornecedorService.buscarPorId(id);
            if (fornecedorExistente != null) {
                fornecedor.setId(id);
                Fornecedor fornecedorAtualizado = fornecedorService.atualizar(fornecedor);
                return ResponseEntity.ok(fornecedorAtualizado);
            } else {
                return ResponseEntity.status(404).body(Map.of("erro", "Fornecedor não encontrado."));
            }
        } catch (DuplicateCnpjException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}