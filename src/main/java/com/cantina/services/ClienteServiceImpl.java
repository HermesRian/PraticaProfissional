package com.cantina.services;

import com.cantina.database.CidadeDAO;
import com.cantina.database.ClienteDAO;
import com.cantina.database.EstadoDAO;
import com.cantina.database.PaisDAO;
import com.cantina.entities.Cliente;
import com.cantina.utils.DocumentoUtils;
import org.springframework.stereotype.Service;
import com.cantina.entities.Cidade;
import com.cantina.entities.Estado;
import com.cantina.entities.Pais;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final CidadeDAO cidadeDAO = new CidadeDAO();
    private final EstadoDAO estadoDAO = new EstadoDAO();
    private final PaisDAO paisDAO = new PaisDAO();
    @Override
    public List<Cliente> listarTodos() {

        return clienteDAO.listarTodos();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        if (cliente.getCidadeId() != null) {
            Cidade cidade = cidadeDAO.buscarPorId(cliente.getCidadeId());
            if (cidade != null) {
                Estado estado = estadoDAO.buscarPorId(cidade.getEstadoId());
                if (estado != null) {
                    Pais pais = paisDAO.buscarPorId(Long.valueOf(estado.getPaisId()));
                    if (pais != null && "BRASIL".equalsIgnoreCase(pais.getNome())) {
                        if (cliente.getCnpjCpf() == null || cliente.getCnpjCpf().trim().isEmpty()) {
                            if (cliente.getTipo() != null && cliente.getTipo() == 0) {
                                throw new IllegalArgumentException("CPF é obrigatório para clientes brasileiros.");
                            } else if (cliente.getTipo() != null && cliente.getTipo() == 1) {
                                throw new IllegalArgumentException("CNPJ é obrigatório para clientes brasileiros.");
                            } else {
                                throw new IllegalArgumentException("CNPJ/CPF é obrigatório para clientes brasileiros.");
                            }
                        }
                        if (cliente.getTipo() != null && cliente.getCnpjCpf() != null) {
                            if (cliente.getTipo() == 0 && !DocumentoUtils.isCpfValido(cliente.getCnpjCpf())) {
                                throw new IllegalArgumentException("CPF inválido.");
                            } else if (cliente.getTipo() == 1 && !DocumentoUtils.isCnpjValido(cliente.getCnpjCpf())) {
                                throw new IllegalArgumentException("CNPJ inválido.");
                            }
                        }
                    }
                }
            }
        }
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

            if (cliente.getCidadeId() != null) {
                Cidade cidade = cidadeDAO.buscarPorId(cliente.getCidadeId());
                if (cidade != null) {
                    Estado estado = estadoDAO.buscarPorId(cidade.getEstadoId());
                    if (estado != null) {
                        Pais pais = paisDAO.buscarPorId(Long.valueOf(estado.getPaisId()));
                        if (pais != null && "BRASIL".equalsIgnoreCase(pais.getNome())) {
                            if (cliente.getCnpjCpf() == null || cliente.getCnpjCpf().trim().isEmpty()) {
                                if (cliente.getTipo() != null && cliente.getTipo() == 0) {
                                    throw new IllegalArgumentException("CPF é obrigatório para clientes brasileiros.");
                                } else if (cliente.getTipo() != null && cliente.getTipo() == 1) {
                                    throw new IllegalArgumentException("CNPJ é obrigatório para clientes brasileiros.");
                                } else {
                                    throw new IllegalArgumentException("CNPJ/CPF é obrigatório para clientes brasileiros.");
                                }
                            }
                            if (cliente.getTipo() != null && cliente.getCnpjCpf() != null) {
                                if (cliente.getTipo() == 0 && !DocumentoUtils.isCpfValido(cliente.getCnpjCpf())) {
                                    throw new IllegalArgumentException("CPF inválido.");
                                } else if (cliente.getTipo() == 1 && !DocumentoUtils.isCnpjValido(cliente.getCnpjCpf())) {
                                    throw new IllegalArgumentException("CNPJ inválido.");
                                }
                            }
                        }
                    }
                }
            }

            clienteDAO.atualizar(cliente);
            return cliente;
        }
        return null;
    }
}