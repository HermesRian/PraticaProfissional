package com.cantina.services;

import com.cantina.entities.CondicaoPagamento;
import java.util.List;

public interface CondicaoPagamentoService {
    List<CondicaoPagamento> listarTodos();
    CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento);
    CondicaoPagamento buscarPorId(Long id);
    void excluir(Long id);
}