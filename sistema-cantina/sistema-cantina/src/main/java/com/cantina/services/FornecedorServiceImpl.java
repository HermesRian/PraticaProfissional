package com.cantina.services;

import com.cantina.entities.Fornecedor;
import com.cantina.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public Fornecedor buscarPorId(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        return fornecedor.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }

    @Override
    public void excluir(Long id) {
        fornecedorRepository.deleteById(id);
    }
}