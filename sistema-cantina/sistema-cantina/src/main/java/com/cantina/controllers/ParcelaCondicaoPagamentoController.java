package com.cantina.controllers;

import com.cantina.entities.ParcelaCondicaoPagamento;
import com.cantina.services.ParcelaCondicaoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelas-condicao-pagamento")
public class ParcelaCondicaoPagamentoController {

    @Autowired
    private ParcelaCondicaoPagamentoService parcelaCondicaoPagamentoService;

    @GetMapping
    public List<ParcelaCondicaoPagamento> listarTodos() {
        return parcelaCondicaoPagamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ParcelaCondicaoPagamento buscarPorId(@PathVariable Long id) {
        return parcelaCondicaoPagamentoService.buscarPorId(id);
    }

    @PostMapping
    public ParcelaCondicaoPagamento salvar(@RequestBody ParcelaCondicaoPagamento parcela) {
        return parcelaCondicaoPagamentoService.salvar(parcela);
    }

    @PutMapping("/{id}")
    public ParcelaCondicaoPagamento atualizar(@PathVariable Long id, @RequestBody ParcelaCondicaoPagamento parcela) {
        return parcelaCondicaoPagamentoService.atualizar(id, parcela);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        parcelaCondicaoPagamentoService.excluir(id);
    }
}