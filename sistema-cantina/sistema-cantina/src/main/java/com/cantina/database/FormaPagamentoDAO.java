package com.cantina.database;

import com.cantina.entities.FormaPagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {

    public void salvar(FormaPagamento formaPagamento) {
        String sql = "INSERT INTO forma_pagamento (descricao, codigo, tipo, ativo) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, formaPagamento.getDescricao());
            statement.setString(2, formaPagamento.getCodigo());
            statement.setString(3, formaPagamento.getTipo());
            statement.setBoolean(4, formaPagamento.getAtivo());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FormaPagamento> listarTodos() {
        List<FormaPagamento> formas = new ArrayList<>();
        String sql = "SELECT * FROM forma_pagamento";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FormaPagamento forma = new FormaPagamento();
                forma.setId(resultSet.getLong("id"));
                forma.setDescricao(resultSet.getString("descricao"));
                forma.setCodigo(resultSet.getString("codigo"));
                forma.setTipo(resultSet.getString("tipo"));
                forma.setAtivo(resultSet.getBoolean("ativo"));

                formas.add(forma);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formas;
    }

    public FormaPagamento buscarPorId(Long id) {
        String sql = "SELECT * FROM forma_pagamento WHERE id = ?";
        FormaPagamento forma = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                forma = new FormaPagamento();
                forma.setId(resultSet.getLong("id"));
                forma.setDescricao(resultSet.getString("descricao"));
                forma.setCodigo(resultSet.getString("codigo"));
                forma.setTipo(resultSet.getString("tipo"));
                forma.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return forma;
    }

    public void atualizar(FormaPagamento formaPagamento) {
        String sql = "UPDATE forma_pagamento SET descricao = ?, codigo = ?, tipo = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, formaPagamento.getDescricao());
            statement.setString(2, formaPagamento.getCodigo());
            statement.setString(3, formaPagamento.getTipo());
            statement.setBoolean(4, formaPagamento.getAtivo());
            statement.setLong(5, formaPagamento.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM forma_pagamento WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}