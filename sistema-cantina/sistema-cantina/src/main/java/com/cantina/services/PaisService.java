package com.cantina.services;

import com.cantina.entities.Pais;

import java.util.List;

public interface PaisService {
    List<Pais> listarTodos();
    Pais buscarPorId(Long id);
    Pais salvar(Pais pais);
    Pais atualizar(Long id, Pais pais);
    void excluir(Long id);
}