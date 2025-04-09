package com.cantina.services;

import com.cantina.database.ModalidadeNfeDAO;
import com.cantina.entities.ModalidadeNfe;
import com.cantina.services.ModalidadeNfeService;

import java.util.List;

public class ModalidadeNfeServiceImpl implements ModalidadeNfeService {

    private final ModalidadeNfeDAO modalidadeNfeDAO;

    public ModalidadeNfeServiceImpl(ModalidadeNfeDAO modalidadeNfeDAO) {
        this.modalidadeNfeDAO = modalidadeNfeDAO;
    }

    @Override
    public void salvar(ModalidadeNfe modalidadeNfe) {
        modalidadeNfeDAO.salvar(modalidadeNfe);
    }

    @Override
    public List<ModalidadeNfe> listarTodas() {
        return modalidadeNfeDAO.listarTodas();
    }

    @Override
    public ModalidadeNfe buscarPorId(Long id) {
        return modalidadeNfeDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(ModalidadeNfe modalidadeNfe) {
        modalidadeNfeDAO.atualizar(modalidadeNfe);
    }

    @Override
    public void excluir(Long id) {
        modalidadeNfeDAO.excluir(id);
    }
}