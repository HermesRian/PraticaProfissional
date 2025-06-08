package com.cantina.services;

import com.cantina.entities.ModalidadeNfe;

import java.util.List;

public interface ModalidadeNfeService {
    void salvar(ModalidadeNfe modalidadeNfe);
    List<ModalidadeNfe> listarTodas();
    ModalidadeNfe buscarPorId(Long id);
    void atualizar(ModalidadeNfe modalidadeNfe);
    void excluir(Long id);
}