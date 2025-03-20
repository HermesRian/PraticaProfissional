package com.cantina.services;

import com.cantina.entities.Funcionario;
import java.util.List;

public interface FuncionarioService {
    List<Funcionario> listarTodos();
    Funcionario salvar(Funcionario funcionario);
    Funcionario buscarPorId(Long id);
    void excluir(Long id);
}