package com.cantina.services;

import com.cantina.database.EstadoDAO;
import com.cantina.entities.Estado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoDAO estadoDAO;

    public EstadoServiceImpl(EstadoDAO estadoDAO) {
        this.estadoDAO = estadoDAO;
    }

    @Override
    public Estado salvar(Estado estado) {
        return estadoDAO.salvar(estado);
    }

    @Override
    public List<Estado> listarTodos() {
        return estadoDAO.listarTodos();
    }

    @Override
    public Estado buscarPorId(Long id) {
        return estadoDAO.buscarPorId(id);
    }

    @Override
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoExistente = estadoDAO.buscarPorId(id);
        if (estadoExistente != null) {
            estadoExistente.setNome(estado.getNome());
            estadoExistente.setUf(estado.getUf());
            estadoExistente.setPaisId(estado.getPaisId());
            estadoDAO.atualizar(estadoExistente);
            return estadoExistente;
        }
        return null;
    }

    @Override
    public void excluir(Long id) {
        estadoDAO.excluir(id);
    }
}