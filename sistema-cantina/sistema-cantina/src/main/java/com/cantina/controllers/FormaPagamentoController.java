package com.cantina.controllers;

import com.cantina.entities.FormaPagamento;
import com.cantina.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarTodos() {
        List<FormaPagamento> formasPagamento = formaPagamentoService.listarTodos();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(formaPagamento);
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> salvar(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento novaFormaPagamento = formaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}