package com.cantina.services;

import com.cantina.entities.FormaPagamento;
import com.cantina.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public List<FormaPagamento> listarTodos() {
        return formaPagamentoRepository.findAll();
    }

    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Override
    public FormaPagamento buscarPorId(Long id) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
        return formaPagamento.orElseThrow(() -> new RuntimeException("Forma de Pagamento não encontrada"));
    }

    @Override
    public void excluir(Long id) {
        formaPagamentoRepository.deleteById(id);
    }
}