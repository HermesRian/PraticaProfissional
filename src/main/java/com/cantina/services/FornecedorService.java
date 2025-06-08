package com.cantina.services;

import com.cantina.entities.Fornecedor;
import java.util.List;

public interface FornecedorService {
    List<Fornecedor> listarTodos();
    Fornecedor salvar(Fornecedor fornecedor);
    Fornecedor buscarPorId(Long id);
    void excluir(Long id);
    Fornecedor atualizar(Fornecedor fornecedor);
}