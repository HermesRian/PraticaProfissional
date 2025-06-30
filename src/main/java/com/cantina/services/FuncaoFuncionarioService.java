package com.cantina.services;

import com.cantina.entities.FuncaoFuncionario;
import java.util.List;

public interface FuncaoFuncionarioService {
    List<FuncaoFuncionario> listarTodos();
    List<FuncaoFuncionario> listarAtivos();
    FuncaoFuncionario salvar(FuncaoFuncionario funcaoFuncionario);
    FuncaoFuncionario buscarPorId(Long id);
    FuncaoFuncionario atualizar(Long id, FuncaoFuncionario funcaoFuncionario);
    FuncaoFuncionario atualizar(FuncaoFuncionario funcaoFuncionario);
    void excluir(Long id);
}
