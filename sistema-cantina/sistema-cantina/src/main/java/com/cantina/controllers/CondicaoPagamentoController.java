package com.cantina.controllers;

import com.cantina.entities.CondicaoPagamento;
import com.cantina.services.CondicaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condicoes-pagamento")
public class CondicaoPagamentoController {

    @Autowired
    private CondicaoPagamentoService condicaoPagamentoService;

    @GetMapping
    public List<CondicaoPagamento> listarTodos() {

        return condicaoPagamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public CondicaoPagamento buscarPorId(@PathVariable Long id) {

        return condicaoPagamentoService.buscarPorId(id);
    }

    @PostMapping
    public CondicaoPagamento salvar(@RequestBody CondicaoPagamento condicaoPagamento) {
        return condicaoPagamentoService.salvar(condicaoPagamento);
    }

    @PutMapping("/{id}")
    public CondicaoPagamento atualizar(@PathVariable Long id, @RequestBody CondicaoPagamento condicaoPagamento) {
        return condicaoPagamentoService.atualizar(id, condicaoPagamento);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        condicaoPagamentoService.excluir(id);
    }
}