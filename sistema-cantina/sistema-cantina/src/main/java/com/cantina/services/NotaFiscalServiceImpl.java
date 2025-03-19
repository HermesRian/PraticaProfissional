package com.cantina.services;

import com.cantina.entities.NotaFiscal;
import com.cantina.repositories.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Override
    public List<NotaFiscal> listarTodos() {
        return notaFiscalRepository.findAll();
    }

    @Override
    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        return notaFiscalRepository.save(notaFiscal);
    }

    @Override
    public NotaFiscal buscarPorId(Long id) {
        Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id);
        return notaFiscal.orElseThrow(() -> new RuntimeException("Nota Fiscal não encontrada"));
    }

    @Override
    public void excluir(Long id) {
        notaFiscalRepository.deleteById(id);
    }
}