package com.cantina.repositories;

import com.cantina.entities.FormaPagamento;
import java.util.List;

public interface FormaPagamentoRepository {
    void save(FormaPagamento formaPagamento);
    FormaPagamento findById(Long id);
    List<FormaPagamento> findAll();
    void update(FormaPagamento formaPagamento);
    void delete(Long id);
}