package com.cantina.repositories;

import com.cantina.entities.CondicaoPagamento;
import java.util.List;

public interface CondicaoPagamentoRepository {
    void save(CondicaoPagamento condicaoPagamento);
    CondicaoPagamento findById(Long id);
    List<CondicaoPagamento> findAll();
    void update(CondicaoPagamento condicaoPagamento);
    void delete(Long id);
}