package com.cantina.services;

import com.cantina.entities.Produto;
import java.util.List;

public interface ProdutoService {
    List<Produto> listarTodos();
    Produto salvar(Produto produto);
    Produto buscarPorId(Long id);
    Produto atualizar(Long id, Produto produto);
    void excluir(Long id);
}