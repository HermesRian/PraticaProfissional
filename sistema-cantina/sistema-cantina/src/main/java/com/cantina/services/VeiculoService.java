package com.cantina.services;

import com.cantina.entities.Veiculo;

import java.util.List;

public interface VeiculoService {
    void salvar(Veiculo veiculo);
    List<Veiculo> listarTodos();
    Veiculo buscarPorId(Long id);
    void atualizar(Veiculo veiculo);
    void excluir(Long id);
}