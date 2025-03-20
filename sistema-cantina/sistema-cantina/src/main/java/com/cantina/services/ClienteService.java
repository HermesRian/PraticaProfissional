package com.cantina.services;

import com.cantina.entities.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodos();
    Cliente salvar(Cliente cliente);
    Cliente buscarPorId(Long id);
    void excluir(Long id);
}