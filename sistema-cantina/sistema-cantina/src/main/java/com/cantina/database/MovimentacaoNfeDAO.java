package com.cantina.database;

import com.cantina.entities.MovimentacaoNfe;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoNfeDAO {

    public void salvar(MovimentacaoNfe movimentacaoNfe) {
        String sql = "INSERT INTO movimentacao_nfe (nota_fiscal_id, data_movimentacao, status, descricao) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, movimentacaoNfe.getNotaFiscalId());
            statement.setTimestamp(2, Timestamp.valueOf(movimentacaoNfe.getDataMovimentacao()));
            statement.setString(3, movimentacaoNfe.getStatus());
            statement.setString(4, movimentacaoNfe.getDescricao());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MovimentacaoNfe> listarTodas() {
        List<MovimentacaoNfe> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao_nfe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                MovimentacaoNfe movimentacao = new MovimentacaoNfe();
                movimentacao.setId(resultSet.getLong("id"));
                movimentacao.setNotaFiscalId(resultSet.getLong("nota_fiscal_id"));
                movimentacao.setDataMovimentacao(resultSet.getTimestamp("data_movimentacao").toLocalDateTime());
                movimentacao.setStatus(resultSet.getString("status"));
                movimentacao.setDescricao(resultSet.getString("descricao"));
                movimentacoes.add(movimentacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public MovimentacaoNfe buscarPorId(Long id) {
        String sql = "SELECT * FROM movimentacao_nfe WHERE id = ?";
        MovimentacaoNfe movimentacao = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                movimentacao = new MovimentacaoNfe();
                movimentacao.setId(resultSet.getLong("id"));
                movimentacao.setNotaFiscalId(resultSet.getLong("nota_fiscal_id"));
                movimentacao.setDataMovimentacao(resultSet.getTimestamp("data_movimentacao").toLocalDateTime());
                movimentacao.setStatus(resultSet.getString("status"));
                movimentacao.setDescricao(resultSet.getString("descricao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacao;
    }

    public void atualizar(MovimentacaoNfe movimentacaoNfe) {
        String sql = "UPDATE movimentacao_nfe SET nota_fiscal_id = ?, data_movimentacao = ?, status = ?, descricao = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, movimentacaoNfe.getNotaFiscalId());
            statement.setTimestamp(2, Timestamp.valueOf(movimentacaoNfe.getDataMovimentacao()));
            statement.setString(3, movimentacaoNfe.getStatus());
            statement.setString(4, movimentacaoNfe.getDescricao());
            statement.setLong(5, movimentacaoNfe.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM movimentacao_nfe WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}