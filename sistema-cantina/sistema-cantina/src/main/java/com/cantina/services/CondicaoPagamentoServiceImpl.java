package com.cantina.services;

import com.cantina.entities.CondicaoPagamento;
import com.cantina.repositories.CondicaoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondicaoPagamentoServiceImpl implements CondicaoPagamentoService {

    @Autowired
    private CondicaoPagamentoRepository condicaoPagamentoRepository;

    @Override
    public List<CondicaoPagamento> listarTodos() {
        return condicaoPagamentoRepository.findAll();
    }

    @Override
    public CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento) {
        return condicaoPagamentoRepository.save(condicaoPagamento);
    }

    @Override
    public CondicaoPagamento buscarPorId(Long id) {
        Optional<CondicaoPagamento> condicaoPagamento = condicaoPagamentoRepository.findById(id);
        return condicaoPagamento.orElseThrow(() -> new RuntimeException("Condição de Pagamento não encontrada"));
    }

    @Override
    public void excluir(Long id) {
        condicaoPagamentoRepository.deleteById(id);
    }
}