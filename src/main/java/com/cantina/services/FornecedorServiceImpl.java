package com.cantina.services;

import com.cantina.database.CidadeDAO;
import com.cantina.database.EstadoDAO;
import com.cantina.database.FornecedorDAO;
import com.cantina.database.PaisDAO;
import com.cantina.entities.Cidade;
import com.cantina.entities.Estado;
import com.cantina.entities.Fornecedor;
import com.cantina.entities.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:5173")
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorDAO fornecedorDAO;

    @Autowired
    private CidadeDAO cidadeDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    private PaisDAO paisDAO;

    @Override
    public List<Fornecedor> listarTodos() {
        return fornecedorDAO.listarTodos();
    }

    @Override
    public Fornecedor salvar(Fornecedor fornecedor) {
        if (fornecedor.getCidadeId() != null) {
            Cidade cidade = cidadeDAO.buscarPorId(fornecedor.getCidadeId());
            if (cidade != null) {
                Estado estado = estadoDAO.buscarPorId(cidade.getEstadoId());
                if (estado != null) {
                    Pais pais = paisDAO.buscarPorId(Long.valueOf(estado.getPaisId()));
                    if (pais != null && "BRASIL".equalsIgnoreCase(pais.getNome())) {
                        if (fornecedor.getCpfCnpj() == null || fornecedor.getCpfCnpj().isEmpty()) {
                            throw new IllegalArgumentException("CNPJ é obrigatório para fornecedores brasileiros.");
                        }
                    }
                }
            }
        }

        if (fornecedor.getCpfCnpj() != null && fornecedor.getCpfCnpj().isEmpty()) {
            fornecedor.setCpfCnpj(null);
        }

        fornecedorDAO.salvar(fornecedor);
        return fornecedor;
    }

    @Override
    public Fornecedor buscarPorId(Long id) {
        return fornecedorDAO.buscarPorId(id);
    }

    @Override
    public void excluir(Long id) {
        fornecedorDAO.excluir(id);
    }


    @Override
    public Fornecedor atualizar(Fornecedor fornecedor) {
        if (fornecedor.getCidadeId() != null) {
            Cidade cidade = cidadeDAO.buscarPorId(fornecedor.getCidadeId());
            if (cidade != null) {
                Estado estado = estadoDAO.buscarPorId(cidade.getEstadoId());
                if (estado != null) {
                    Pais pais = paisDAO.buscarPorId(Long.valueOf(estado.getPaisId()));
                    if (pais != null && "BRASIL".equalsIgnoreCase(pais.getNome())) {
                        if (fornecedor.getCpfCnpj() == null || fornecedor.getCpfCnpj().isEmpty()) {
                            throw new IllegalArgumentException("CNPJ é obrigatório para fornecedores brasileiros.");
                        }
                    }
                }
            }
        }

        if (fornecedor.getCpfCnpj() != null && fornecedor.getCpfCnpj().isEmpty()) {
            fornecedor.setCpfCnpj(null);
        }

        fornecedorDAO.update(fornecedor);
        return fornecedor;
    }
}