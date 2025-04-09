package com.cantina.services;

import com.cantina.database.ClienteDAO;
import com.cantina.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        clienteDAO.salvar(cliente);
        return cliente;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        clienteDAO.excluir(id);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteDAO.buscarPorId(id);
        if (clienteExistente != null) {
            cliente.setId(id);
            clienteDAO.atualizar(cliente);
            return cliente;
        }
        return null;
    }
}