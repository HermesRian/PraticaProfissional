package com.cantina.services;

import com.cantina.database.VeiculoDAO;
import com.cantina.entities.Veiculo;
import com.cantina.services.VeiculoService;

import java.util.List;

public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoServiceImpl(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    @Override
    public void salvar(Veiculo veiculo) {
        veiculoDAO.salvar(veiculo);
    }

    @Override
    public List<Veiculo> listarTodos() {
        return veiculoDAO.listarTodos();
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return veiculoDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        veiculoDAO.atualizar(veiculo);
    }

    @Override
    public void excluir(Long id) {
        veiculoDAO.excluir(id);
    }
}