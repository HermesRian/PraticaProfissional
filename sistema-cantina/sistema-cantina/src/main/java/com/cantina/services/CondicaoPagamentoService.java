package com.cantina.services;

import com.cantina.entities.CondicaoPagamento;
import java.util.List;

public interface CondicaoPagamentoService {
    List<CondicaoPagamento> listarTodos();
    CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento);
    CondicaoPagamento buscarPorId(Long id);
    CondicaoPagamento atualizar(Long id, CondicaoPagamento condicaoPagamento);
    void excluir(Long id);
}