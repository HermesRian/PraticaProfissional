package com.cantina.services;

import com.cantina.entities.Cidade;

import java.util.List;

public interface CidadeService {
    Cidade salvar(Cidade cidade);
    List<Cidade> listarTodas();
    Cidade buscarPorId(Long id);
    Cidade atualizar(Long id, Cidade cidade);
    void excluir(Long id);
}