package com.cantina.services;

import com.cantina.database.ParcelaCondicaoPagamentoDAO;
import com.cantina.entities.ParcelaCondicaoPagamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelaCondicaoPagamentoServiceImpl implements ParcelaCondicaoPagamentoService {

    private final ParcelaCondicaoPagamentoDAO parcelaCondicaoPagamentoDAO = new ParcelaCondicaoPagamentoDAO();

    @Override
    public List<ParcelaCondicaoPagamento> listarTodos() {
        return parcelaCondicaoPagamentoDAO.listarTodos();
    }

    @Override
    public ParcelaCondicaoPagamento salvar(ParcelaCondicaoPagamento parcela) {
        parcelaCondicaoPagamentoDAO.salvar(parcela);
        return parcela;
    }

    @Override
    public ParcelaCondicaoPagamento buscarPorId(Long id) {
        return parcelaCondicaoPagamentoDAO.buscarPorId(id);
    }

    @Override
    public ParcelaCondicaoPagamento atualizar(Long id, ParcelaCondicaoPagamento parcela) {
        ParcelaCondicaoPagamento parcelaExistente = parcelaCondicaoPagamentoDAO.buscarPorId(id);
        if (parcelaExistente != null) {
            parcela.setId(id);
            parcelaCondicaoPagamentoDAO.atualizar(parcela);
            return parcela;
        }
        return null;
    }

    @Override
    public void excluir(Long id) {
        parcelaCondicaoPagamentoDAO.excluir(id);
    }
}