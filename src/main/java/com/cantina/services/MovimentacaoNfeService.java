package com.cantina.services;

import com.cantina.entities.MovimentacaoNfe;

import java.util.List;

public interface MovimentacaoNfeService {
    void salvar(MovimentacaoNfe movimentacaoNfe);
    List<MovimentacaoNfe> listarTodas();
    MovimentacaoNfe buscarPorId(Long id);
    void atualizar(MovimentacaoNfe movimentacaoNfe);
    void excluir(Long id);
}