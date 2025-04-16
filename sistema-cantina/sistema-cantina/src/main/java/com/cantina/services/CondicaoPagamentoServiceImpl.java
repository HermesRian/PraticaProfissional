package com.cantina.services;

import com.cantina.database.CondicaoPagamentoDAO;
import com.cantina.database.ParcelaCondicaoPagamentoDAO;
import com.cantina.entities.CondicaoPagamento;
import org.springframework.stereotype.Service;
import com.cantina.entities.ParcelaCondicaoPagamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondicaoPagamentoServiceImpl implements CondicaoPagamentoService {

    private final CondicaoPagamentoDAO condicaoPagamentoDAO = new CondicaoPagamentoDAO();
    private final ParcelaCondicaoPagamentoDAO parcelaCondicaoPagamentoDAO = new ParcelaCondicaoPagamentoDAO();

    @Override
    public List<CondicaoPagamento> listarTodos() {

        return condicaoPagamentoDAO.listarTodos();
    }
    @Transactional
    @Override
    public CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento) {
        double somaPercentuais = condicaoPagamento.getParcelasCondicao().stream()
                .mapToDouble(ParcelaCondicaoPagamento::getPercentual)
                .sum();

        if (somaPercentuais != 100.0) {
            throw new IllegalArgumentException("A soma dos percentuais das parcelas deve ser 100%.");
        }

        condicaoPagamentoDAO.salvar(condicaoPagamento);
        condicaoPagamento.getParcelasCondicao().forEach(parcela -> {
            parcela.setCondicaoPagamento(condicaoPagamento);
            parcelaCondicaoPagamentoDAO.salvar(parcela);
        });

        return condicaoPagamento;
    }

    @Override
    public CondicaoPagamento buscarPorId(Long id) {

        return condicaoPagamentoDAO.buscarPorId(id);
    }

    @Override
    public CondicaoPagamento atualizar(Long id, CondicaoPagamento condicaoPagamento) {
        CondicaoPagamento condicaoPagamentoExistente = condicaoPagamentoDAO.buscarPorId(id);
        if (condicaoPagamentoExistente != null) {
            condicaoPagamento.setId(id);
            condicaoPagamentoDAO.atualizar(condicaoPagamento);
            return condicaoPagamento;
        }
        return null;
    }

    @Override
    public void excluir(Long id) {

        condicaoPagamentoDAO.excluir(id);
    }
}