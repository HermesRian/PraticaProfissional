package com.cantina.services;

import com.cantina.database.TranspItemDAO;
import com.cantina.entities.TranspItem;
import com.cantina.services.TranspItemService;

import java.util.List;

public class TranspItemServiceImpl implements TranspItemService {

    private final TranspItemDAO transpItemDAO;

    public TranspItemServiceImpl(TranspItemDAO transpItemDAO) {
        this.transpItemDAO = transpItemDAO;
    }

    @Override
    public void salvar(TranspItem transpItem) {
        transpItemDAO.salvar(transpItem);
    }

    @Override
    public List<TranspItem> listarTodos() {
        return transpItemDAO.listarTodos();
    }

    @Override
    public TranspItem buscarPorId(Long id) {
        return transpItemDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(TranspItem transpItem) {
        transpItemDAO.atualizar(transpItem);
    }

    @Override
    public void excluir(Long id) {
        transpItemDAO.excluir(id);
    }
}