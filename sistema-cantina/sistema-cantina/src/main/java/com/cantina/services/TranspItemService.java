package com.cantina.services;

import com.cantina.entities.TranspItem;

import java.util.List;

public interface TranspItemService {
    void salvar(TranspItem transpItem);
    List<TranspItem> listarTodos();
    TranspItem buscarPorId(Long id);
    void atualizar(TranspItem transpItem);
    void excluir(Long id);
}