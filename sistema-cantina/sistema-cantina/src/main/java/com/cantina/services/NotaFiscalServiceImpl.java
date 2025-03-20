package com.cantina.services;

import com.cantina.database.NotaFiscalDAO;
import com.cantina.entities.NotaFiscal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();

    @Override
    public List<NotaFiscal> listarTodos() {
        return notaFiscalDAO.listarTodos();
    }

    @Override
    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        notaFiscalDAO.salvar(notaFiscal);
        return notaFiscal;
    }

    @Override
    public NotaFiscal buscarPorId(Long id) {
        return notaFiscalDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        notaFiscalDAO.excluir(id);
    }
}