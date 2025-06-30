package com.cantina.services;

import com.cantina.database.FuncaoFuncionarioDAO;
import com.cantina.entities.FuncaoFuncionario;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:5173")
public class FuncaoFuncionarioServiceImpl implements FuncaoFuncionarioService {

    private final FuncaoFuncionarioDAO funcaoFuncionarioDAO = new FuncaoFuncionarioDAO();

    @Override
    public List<FuncaoFuncionario> listarTodos() {
        return funcaoFuncionarioDAO.listarTodos();
    }

    @Override
    public List<FuncaoFuncionario> listarAtivos() {
        return funcaoFuncionarioDAO.listarAtivos();
    }

    @Override
    public FuncaoFuncionario salvar(FuncaoFuncionario funcaoFuncionario) {
        funcaoFuncionarioDAO.salvar(funcaoFuncionario);
        return funcaoFuncionario;
    }

    @Override
    public FuncaoFuncionario buscarPorId(Long id) {
        return funcaoFuncionarioDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        funcaoFuncionarioDAO.excluir(id);
    }

    @Override
    public FuncaoFuncionario atualizar(FuncaoFuncionario funcaoFuncionario) {
        funcaoFuncionarioDAO.atualizar(funcaoFuncionario);
        return funcaoFuncionario;
    }

    @Override
    public FuncaoFuncionario atualizar(Long id, FuncaoFuncionario funcaoFuncionario) {
        FuncaoFuncionario funcaoExistente = funcaoFuncionarioDAO.buscarPorId(id);
        if (funcaoExistente == null) {
            throw new IllegalArgumentException("Função de funcionário não encontrada para o ID fornecido.");
        }
        funcaoFuncionario.setId(id);
        funcaoFuncionarioDAO.atualizar(funcaoFuncionario);
        return funcaoFuncionario;
    }
}
