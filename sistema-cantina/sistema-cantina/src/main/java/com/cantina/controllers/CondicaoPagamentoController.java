package com.cantina.controllers;

import com.cantina.entities.CondicaoPagamento;
import com.cantina.services.CondicaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condicoes-pagamento")
public class CondicaoPagamentoController {

    @Autowired
    private CondicaoPagamentoService condicaoPagamentoService;

    @GetMapping
    public ResponseEntity<List<CondicaoPagamento>> listarTodos() {
        List<CondicaoPagamento> condicoesPagamento = condicaoPagamentoService.listarTodos();
        return ResponseEntity.ok(condicoesPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicaoPagamento> buscarPorId(@PathVariable Long id) {
        CondicaoPagamento condicaoPagamento = condicaoPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(condicaoPagamento);
    }

    @PostMapping
    public ResponseEntity<CondicaoPagamento> salvar(@RequestBody CondicaoPagamento condicaoPagamento) {
        CondicaoPagamento novaCondicaoPagamento = condicaoPagamentoService.salvar(condicaoPagamento);
        return ResponseEntity.ok(novaCondicaoPagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        condicaoPagamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}