package com.cantina.database;

import com.cantina.entities.CondicaoPagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondicaoPagamentoDAO {

    public void salvar(CondicaoPagamento condicaoPagamento) {
        String sql = "INSERT INTO condicao_pagamento (descricao, numeroParcelas, intervaloDias) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, condicaoPagamento.getDescricao());
//            statement.setInt(2, condicaoPagamento.getNumeroParcelas());
//            statement.setInt(3, condicaoPagamento.getIntervaloDias());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CondicaoPagamento> listarTodos() {
        List<CondicaoPagamento> condicoes = new ArrayList<>();
        String sql = "SELECT * FROM condicao_pagamento";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CondicaoPagamento condicao = new CondicaoPagamento();
                condicao.setId(resultSet.getLong("id"));
                condicao.setDescricao(resultSet.getString("descricao"));
//                condicao.setNumeroParcelas(resultSet.getInt("numeroParcelas"));
//                condicao.setIntervaloDias(resultSet.getInt("intervaloDias"));

                condicoes.add(condicao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condicoes;
    }

    public CondicaoPagamento buscarPorId(Long id) {
        String sql = "SELECT * FROM condicao_pagamento WHERE id = ?";
        CondicaoPagamento condicao = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                condicao = new CondicaoPagamento();
                condicao.setId(resultSet.getLong("id"));
                condicao.setDescricao(resultSet.getString("descricao"));
//                condicao.setNumeroParcelas(resultSet.getInt("numeroParcelas"));
//                condicao.setIntervaloDias(resultSet.getInt("intervaloDias"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condicao;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM condicao_pagamento WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}