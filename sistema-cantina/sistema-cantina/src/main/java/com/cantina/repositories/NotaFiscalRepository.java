package com.cantina.repositories;

import com.cantina.entities.NotaFiscal;
import java.util.List;

public interface NotaFiscalRepository {
    void save(NotaFiscal notaFiscal);
    NotaFiscal findById(Long id);
    List<NotaFiscal> findAll();
    void update(NotaFiscal notaFiscal);
    void delete(Long id);
}