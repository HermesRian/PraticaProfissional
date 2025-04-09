package com.cantina.services;

import com.cantina.entities.Estado;

import java.util.List;

public interface EstadoService {
    Estado salvar(Estado estado);
    List<Estado> listarTodos();
    Estado buscarPorId(Long id);
    Estado atualizar(Long id, Estado estado);
    void excluir(Long id);
}