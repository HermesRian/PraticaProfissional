package com.cantina.services;

import com.cantina.entities.Produto;
import com.cantina.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}