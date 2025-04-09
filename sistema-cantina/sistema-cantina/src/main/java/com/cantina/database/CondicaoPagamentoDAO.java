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
        String sql = "INSERT INTO condicao_pagamento (descricao, dias, parcelas, ativo) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, condicaoPagamento.getDescricao());
            statement.setInt(2, condicaoPagamento.getDias());
            statement.setInt(3, condicaoPagamento.getParcelas());
            statement.setBoolean(4, condicaoPagamento.getAtivo());

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
                condicao.setDias(resultSet.getInt("dias"));
                condicao.setParcelas(resultSet.getInt("parcelas"));
                condicao.setAtivo(resultSet.getBoolean("ativo"));

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
                condicao.setDias(resultSet.getInt("dias"));
                condicao.setParcelas(resultSet.getInt("parcelas"));
                condicao.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condicao;
    }

    public void atualizar(CondicaoPagamento condicaoPagamento) {
        String sql = "UPDATE condicao_pagamento SET descricao = ?, dias = ?, parcelas = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, condicaoPagamento.getDescricao());
            statement.setInt(2, condicaoPagamento.getDias());
            statement.setInt(3, condicaoPagamento.getParcelas());
            statement.setBoolean(4, condicaoPagamento.getAtivo());
            statement.setLong(5, condicaoPagamento.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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