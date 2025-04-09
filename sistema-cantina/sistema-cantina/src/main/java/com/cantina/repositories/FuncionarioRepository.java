package com.cantina.repositories;

import com.cantina.entities.Funcionario;
import java.util.List;

public interface FuncionarioRepository {
    void save(Funcionario funcionario);
    Funcionario findById(Long id);
    List<Funcionario> findAll();
    void update(Funcionario funcionario);
    void delete(Long id);
}