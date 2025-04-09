package com.cantina.controllers;

import com.cantina.entities.FormaPagamento;
import com.cantina.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public List<FormaPagamento> listarTodos() {
        return formaPagamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public FormaPagamento buscarPorId(@PathVariable Long id) {
        return formaPagamentoService.buscarPorId(id);
    }

    @PostMapping
    public FormaPagamento salvar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{id}")
    public FormaPagamento atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.atualizar(id, formaPagamento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        formaPagamentoService.excluir(id);
    }
}