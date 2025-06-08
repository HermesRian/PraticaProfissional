package com.cantina.services;

import com.cantina.database.PaisDAO;
import com.cantina.entities.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisDAO paisDAO;

    @Override
    public List<Pais> listarTodos() {
        return paisDAO.listarTodos();
    }

    @Override
    public Pais buscarPorId(Long id) { // Alterado para Long
        return paisDAO.buscarPorId(id);
    }

    @Override
    public Pais salvar(Pais pais) {
        return paisDAO.salvar(pais);
    }

    @Override
    public Pais atualizar(Long id, Pais pais) {
        return paisDAO.atualizar(id, pais);
    }

    @Override
    public void excluir(Long id) {
        paisDAO.excluir(id);
    }
}