package com.cantina.services;

import com.cantina.database.CidadeDAO;
import com.cantina.entities.Cidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeDAO cidadeDAO;

    public CidadeServiceImpl(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        //Debug: Verifica se a cidade já existe
        return cidadeDAO.salvar(cidade);
    }

    @Override
    public List<Cidade> listarTodas() {
        return cidadeDAO.listarTodas();
    }

    @Override
    public Cidade buscarPorId(Long id) {
        return cidadeDAO.buscarPorId(id);
    }

    @Override
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeExistente = cidadeDAO.buscarPorId(id);
        if (cidadeExistente == null) {
            throw new RuntimeException("Cidade não encontrada com o ID: " + id);
        }
        cidadeExistente.setNome(cidade.getNome());
        cidadeExistente.setCodigoIbge(cidade.getCodigoIbge());
        cidadeExistente.setEstadoId(cidade.getEstadoId());
        cidadeDAO.atualizar(cidadeExistente);
        return cidadeExistente; // Retorna a entidade atualizada
    }

    @Override
    public void excluir(Long id) {
        cidadeDAO.excluir(id);
    }
}