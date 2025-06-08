package com.cantina.controllers;

import com.cantina.entities.Cliente;
import com.cantina.exceptions.DuplicateCnpjCpfException;
import com.cantina.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {

        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {

        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.salvar(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (DuplicateCnpjCpfException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro inesperado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        clienteService.excluir(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteExistente = clienteService.buscarPorId(id);
            if (clienteExistente != null) {
                cliente.setId(id);
                Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
                return ResponseEntity.ok(clienteAtualizado);
            } else {
                return ResponseEntity.status(404).body(Map.of("erro", "Cliente não encontrado."));
            }
        } catch (DuplicateCnpjCpfException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro inesperado: " + e.getMessage()));
        }
    }
}