package com.cantina.repositories;

import com.cantina.entities.Produto;
import java.util.List;

public interface ProdutoRepository {
    void save(Produto produto);
    Produto findById(Long id);
    List<Produto> findAll();
    void update(Produto produto);
    void delete(Long id);
}