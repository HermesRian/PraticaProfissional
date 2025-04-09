package com.cantina.services;

import com.cantina.entities.Transportadora;

import java.util.List;

public interface TransportadoraService {
    void salvar(Transportadora transportadora);
    List<Transportadora> listarTodas();
    Transportadora buscarPorId(Long id);
    void atualizar(Transportadora transportadora);
    void excluir(Long id);
}