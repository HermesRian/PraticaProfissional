package com.cantina.database;

import com.cantina.entities.NotaFiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDAO {

    public void salvar(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO nota_fiscal (data_emissao, valor_total, fornecedor_id, forma_pagamento_id, condicao_pagamento_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(notaFiscal.getDataEmissao().getTime()));
            statement.setDouble(2, notaFiscal.getValorTotal());
            statement.setLong(3, notaFiscal.getFornecedor().getId());
            statement.setLong(4, notaFiscal.getFormaPagamento().getId());
            statement.setLong(5, notaFiscal.getCondicaoPagamento().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NotaFiscal> listarTodos() {
        List<NotaFiscal> notasFiscais = new ArrayList<>();
        String sql = "SELECT * FROM nota_fiscal";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.setId(resultSet.getLong("id"));
                notaFiscal.setDataEmissao(resultSet.getDate("data_emissao"));
                notaFiscal.setValorTotal(resultSet.getDouble("valor_total"));

                // Carregar Fornecedor, FormaPagamento e CondicaoPagamento
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                notaFiscal.setFornecedor(fornecedorDAO.buscarPorId(resultSet.getLong("fornecedor_id")));

                FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
                notaFiscal.setFormaPagamento(formaPagamentoDAO.buscarPorId(resultSet.getLong("forma_pagamento_id")));

                CondicaoPagamentoDAO condicaoPagamentoDAO = new CondicaoPagamentoDAO();
                notaFiscal.setCondicaoPagamento(condicaoPagamentoDAO.buscarPorId(resultSet.getLong("condicao_pagamento_id")));

                notasFiscais.add(notaFiscal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notasFiscais;
    }

    public NotaFiscal buscarPorId(Long id) {
        String sql = "SELECT * FROM nota_fiscal WHERE id = ?";
        NotaFiscal notaFiscal = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                notaFiscal = new NotaFiscal();
                notaFiscal.setId(resultSet.getLong("id"));
                notaFiscal.setDataEmissao(resultSet.getDate("data_emissao"));
                notaFiscal.setValorTotal(resultSet.getDouble("valor_total"));

                // Carregar Fornecedor, FormaPagamento e CondicaoPagamento
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                notaFiscal.setFornecedor(fornecedorDAO.buscarPorId(resultSet.getLong("fornecedor_id")));

                FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
                notaFiscal.setFormaPagamento(formaPagamentoDAO.buscarPorId(resultSet.getLong("forma_pagamento_id")));

                CondicaoPagamentoDAO condicaoPagamentoDAO = new CondicaoPagamentoDAO();
                notaFiscal.setCondicaoPagamento(condicaoPagamentoDAO.buscarPorId(resultSet.getLong("condicao_pagamento_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notaFiscal;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM nota_fiscal WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}