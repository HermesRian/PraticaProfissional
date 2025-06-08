package com.cantina.services;

import com.cantina.database.ProdutoDAO;
import com.cantina.entities.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    @Override
    public Produto salvar(Produto produto) {
        produtoDAO.salvar(produto);
        return produto;
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        produtoDAO.excluir(id);
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {
        Produto produtoExistente = produtoDAO.buscarPorId(id);
        if (produtoExistente != null) {
            produto.setId(id);
            produtoDAO.atualizar(produto);
            return produto;
        }
        return null;
    }
}