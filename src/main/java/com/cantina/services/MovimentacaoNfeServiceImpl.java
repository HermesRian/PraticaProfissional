package com.cantina.services;

import com.cantina.database.MovimentacaoNfeDAO;
import com.cantina.entities.MovimentacaoNfe;
import com.cantina.services.MovimentacaoNfeService;

import java.util.List;

public class MovimentacaoNfeServiceImpl implements MovimentacaoNfeService {

    private final MovimentacaoNfeDAO movimentacaoNfeDAO;

    public MovimentacaoNfeServiceImpl(MovimentacaoNfeDAO movimentacaoNfeDAO) {
        this.movimentacaoNfeDAO = movimentacaoNfeDAO;
    }

    @Override
    public void salvar(MovimentacaoNfe movimentacaoNfe) {
        movimentacaoNfeDAO.salvar(movimentacaoNfe);
    }

    @Override
    public List<MovimentacaoNfe> listarTodas() {
        return movimentacaoNfeDAO.listarTodas();
    }

    @Override
    public MovimentacaoNfe buscarPorId(Long id) {
        return movimentacaoNfeDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(MovimentacaoNfe movimentacaoNfe) {
        movimentacaoNfeDAO.atualizar(movimentacaoNfe);
    }

    @Override
    public void excluir(Long id) {
        movimentacaoNfeDAO.excluir(id);
    }
}