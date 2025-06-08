package com.cantina.services;

import com.cantina.entities.Funcionario;
import java.util.List;

public interface FuncionarioService {
    List<Funcionario> listarTodos();
    Funcionario salvar(Funcionario funcionario);
    Funcionario buscarPorId(Long id);
    Funcionario atualizar(Long id, Funcionario funcionario);
    Funcionario atualizar(Funcionario funcionario);
    void excluir(Long id);
}