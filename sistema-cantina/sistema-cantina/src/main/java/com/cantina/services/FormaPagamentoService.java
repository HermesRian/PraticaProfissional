package com.cantina.services;

import com.cantina.entities.FormaPagamento;
import java.util.List;

public interface FormaPagamentoService {
    List<FormaPagamento> listarTodos();
    FormaPagamento salvar(FormaPagamento formaPagamento);
    FormaPagamento buscarPorId(Long id);
    void excluir(Long id);
}