package com.cantina.repositories;

import com.cantina.entities.Cliente;
import java.util.List;

public interface ClienteRepository {
    void save(Cliente cliente);
    Cliente findById(Long id);
    List<Cliente> findAll();
    void update(Cliente cliente);
    void delete(Long id);
}