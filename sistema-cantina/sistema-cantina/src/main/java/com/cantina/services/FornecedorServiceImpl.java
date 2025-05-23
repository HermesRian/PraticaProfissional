package com.cantina.services;

import com.cantina.database.FornecedorDAO;
import com.cantina.entities.Fornecedor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:5173")
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @Override
    public List<Fornecedor> listarTodos() {

        return fornecedorDAO.listarTodos();
    }

    @Override
    public Fornecedor salvar(Fornecedor fornecedor) {
        fornecedorDAO.salvar(fornecedor);
        return fornecedor;
    }

    @Override
    public Fornecedor buscarPorId(Long id) {

        return fornecedorDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {

        fornecedorDAO.excluir(id);
    }

    @Override
    public void update(Fornecedor fornecedor) {

        fornecedorDAO.update(fornecedor);
    }
}