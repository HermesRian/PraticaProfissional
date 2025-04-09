package com.cantina.services;

import com.cantina.entities.ProdutoFornecedor;

import java.util.List;

public interface ProdutoFornecedorService {
    void salvar(ProdutoFornecedor produtoFornecedor);
    List<ProdutoFornecedor> listarTodos();
    ProdutoFornecedor buscarPorId(Long id);
    void atualizar(ProdutoFornecedor produtoFornecedor);
    void excluir(Long id);
}