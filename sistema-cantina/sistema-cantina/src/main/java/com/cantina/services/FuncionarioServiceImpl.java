package com.cantina.services;

import com.cantina.database.FuncionarioDAO;
import com.cantina.entities.Funcionario;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        funcionarioDAO.salvar(funcionario);
        return funcionario;
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return funcionarioDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        funcionarioDAO.excluir(id);
    }

    @Override
    public Funcionario atualizar(Long id, Funcionario funcionario) {
        funcionario.setId(id);
        funcionarioDAO.atualizar(funcionario);
        return funcionario;
    }
}