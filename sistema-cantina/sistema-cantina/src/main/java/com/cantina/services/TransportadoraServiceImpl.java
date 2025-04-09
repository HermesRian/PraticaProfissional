package com.cantina.services;

import com.cantina.database.TransportadoraDAO;
import com.cantina.entities.Transportadora;
import com.cantina.services.TransportadoraService;

import java.util.List;

public class TransportadoraServiceImpl implements TransportadoraService {

    private final TransportadoraDAO transportadoraDAO;

    public TransportadoraServiceImpl(TransportadoraDAO transportadoraDAO) {
        this.transportadoraDAO = transportadoraDAO;
    }

    @Override
    public void salvar(Transportadora transportadora) {
        transportadoraDAO.salvar(transportadora);
    }

    @Override
    public List<Transportadora> listarTodas() {
        return transportadoraDAO.listarTodas();
    }

    @Override
    public Transportadora buscarPorId(Long id) {
        return transportadoraDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(Transportadora transportadora) {
        transportadoraDAO.atualizar(transportadora);
    }

    @Override
    public void excluir(Long id) {
        transportadoraDAO.excluir(id);
    }
}