package com.cantina.services;

import com.cantina.entities.NotaFiscal;
import java.util.List;

public interface NotaFiscalService {
    List<NotaFiscal> listarTodos();
    NotaFiscal salvar(NotaFiscal notaFiscal);
    NotaFiscal buscarPorId(Long id);
    void excluir(Long id);
}