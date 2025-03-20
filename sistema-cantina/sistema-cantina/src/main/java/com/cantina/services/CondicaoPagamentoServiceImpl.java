package com.cantina.services;

import com.cantina.database.CondicaoPagamentoDAO;
import com.cantina.entities.CondicaoPagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondicaoPagamentoServiceImpl implements CondicaoPagamentoService {

    private final CondicaoPagamentoDAO condicaoPagamentoDAO = new CondicaoPagamentoDAO();

    @Override
    public List<CondicaoPagamento> listarTodos() {
        return condicaoPagamentoDAO.listarTodos();
    }

    @Override
    public CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento) {
        condicaoPagamentoDAO.salvar(condicaoPagamento);
        return condicaoPagamento;
    }

    @Override
    public CondicaoPagamento buscarPorId(Long id) {
        return condicaoPagamentoDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        condicaoPagamentoDAO.excluir(id);
    }
}