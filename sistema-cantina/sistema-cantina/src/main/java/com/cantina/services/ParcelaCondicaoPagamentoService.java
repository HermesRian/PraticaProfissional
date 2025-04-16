package com.cantina.services;

import com.cantina.entities.ParcelaCondicaoPagamento;

import java.util.List;

public interface ParcelaCondicaoPagamentoService {
    List<ParcelaCondicaoPagamento> listarTodos();
    ParcelaCondicaoPagamento salvar(ParcelaCondicaoPagamento parcela);
    ParcelaCondicaoPagamento buscarPorId(Long id);
    ParcelaCondicaoPagamento atualizar(Long id, ParcelaCondicaoPagamento parcela);
    void excluir(Long id);
}