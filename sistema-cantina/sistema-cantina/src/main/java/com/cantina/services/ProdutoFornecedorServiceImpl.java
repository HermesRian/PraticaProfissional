package com.cantina.services;

import com.cantina.database.ProdutoFornecedorDAO;
import com.cantina.entities.ProdutoFornecedor;
import com.cantina.services.ProdutoFornecedorService;

import java.util.List;

public class ProdutoFornecedorServiceImpl implements ProdutoFornecedorService {

    private final ProdutoFornecedorDAO produtoFornecedorDAO;

    public ProdutoFornecedorServiceImpl(ProdutoFornecedorDAO produtoFornecedorDAO) {
        this.produtoFornecedorDAO = produtoFornecedorDAO;
    }

    @Override
    public void salvar(ProdutoFornecedor produtoFornecedor) {
        produtoFornecedorDAO.salvar(produtoFornecedor);
    }

    @Override
    public List<ProdutoFornecedor> listarTodos() {
        return produtoFornecedorDAO.listarTodos();
    }

    @Override
    public ProdutoFornecedor buscarPorId(Long id) {
        return produtoFornecedorDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(ProdutoFornecedor produtoFornecedor) {
        produtoFornecedorDAO.atualizar(produtoFornecedor);
    }

    @Override
    public void excluir(Long id) {
        produtoFornecedorDAO.excluir(id);
    }
}