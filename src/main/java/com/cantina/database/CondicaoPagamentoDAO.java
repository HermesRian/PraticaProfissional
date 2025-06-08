package com.cantina.database;

import com.cantina.entities.CondicaoPagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cantina.entities.ParcelaCondicaoPagamento;
import com.cantina.database.ParcelaCondicaoPagamentoDAO;


public class CondicaoPagamentoDAO {

    public void salvar(CondicaoPagamento condicaoPagamento) {
        String sql = "INSERT INTO condicao_pagamento (descricao, dias, parcelas, ativo, juros_percentual, multa_percentual, desconto_percentual) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, condicaoPagamento.getDescricao());
            statement.setInt(2, condicaoPagamento.getDias());
            statement.setInt(3, condicaoPagamento.getParcelas());
            statement.setBoolean(4, condicaoPagamento.getAtivo());
            statement.setDouble(5, condicaoPagamento.getJurosPercentual());
            statement.setDouble(6, condicaoPagamento.getMultaPercentual());
            statement.setDouble(7, condicaoPagamento.getDescontoPercentual());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                condicaoPagamento.setId(generatedKeys.getLong(1));
            }

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

            ParcelaCondicaoPagamentoDAO parcelaDAO = new ParcelaCondicaoPagamentoDAO();

            while (resultSet.next()) {
                CondicaoPagamento condicao = new CondicaoPagamento();
                condicao.setId(resultSet.getLong("id"));
                condicao.setDescricao(resultSet.getString("descricao"));
                condicao.setDias(resultSet.getInt("dias"));
                condicao.setParcelas(resultSet.getInt("parcelas"));
                condicao.setAtivo(resultSet.getBoolean("ativo"));
                condicao.setJurosPercentual(resultSet.getDouble("juros_percentual"));
                condicao.setMultaPercentual(resultSet.getDouble("multa_percentual"));
                condicao.setDescontoPercentual(resultSet.getDouble("desconto_percentual"));

                condicao.setParcelasCondicao(parcelaDAO.buscarPorCondicaoPagamentoId(condicao.getId()));

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
                condicao.setJurosPercentual(resultSet.getDouble("juros_percentual"));
                condicao.setMultaPercentual(resultSet.getDouble("multa_percentual"));
                condicao.setDescontoPercentual(resultSet.getDouble("desconto_percentual"));

                // Carregar as parcelas associadas
                ParcelaCondicaoPagamentoDAO parcelaDAO = new ParcelaCondicaoPagamentoDAO();
                condicao.setParcelasCondicao(parcelaDAO.buscarPorCondicaoPagamentoId(condicao.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condicao;
    }

    public void atualizar(CondicaoPagamento condicaoPagamento) {
        String sqlCondicao = "UPDATE condicao_pagamento SET descricao = ?, dias = ?, parcelas = ?, ativo = ?, juros_percentual = ?, multa_percentual = ?, desconto_percentual = ? WHERE id = ?";
        String sqlExcluirParcelas = "DELETE FROM parcela_condicao_pagamento WHERE condicao_pagamento_id = ?";
        String sqlInserirParcela = "INSERT INTO parcela_condicao_pagamento (numero_parcela, dias, percentual, condicao_pagamento_id, forma_pagamento_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Inicia uma transação

            try (PreparedStatement statementCondicao = connection.prepareStatement(sqlCondicao);
                 PreparedStatement statementExcluirParcelas = connection.prepareStatement(sqlExcluirParcelas);
                 PreparedStatement statementInserirParcela = connection.prepareStatement(sqlInserirParcela)) {

                // Atualiza os campos da condição de pagamento
                statementCondicao.setString(1, condicaoPagamento.getDescricao());
                statementCondicao.setInt(2, condicaoPagamento.getDias());
                statementCondicao.setInt(3, condicaoPagamento.getParcelas());
                statementCondicao.setBoolean(4, condicaoPagamento.getAtivo());
                statementCondicao.setDouble(5, condicaoPagamento.getJurosPercentual());
                statementCondicao.setDouble(6, condicaoPagamento.getMultaPercentual());
                statementCondicao.setDouble(7, condicaoPagamento.getDescontoPercentual());
                statementCondicao.setLong(8, condicaoPagamento.getId());
                statementCondicao.executeUpdate();

                // Exclui as parcelas antigas
                statementExcluirParcelas.setLong(1, condicaoPagamento.getId());
                statementExcluirParcelas.executeUpdate();

                // Insere as parcelas atualizadas
                for (ParcelaCondicaoPagamento parcela : condicaoPagamento.getParcelasCondicao()) {
                    statementInserirParcela.setInt(1, parcela.getNumeroParcela());
                    statementInserirParcela.setInt(2, parcela.getDias());
                    statementInserirParcela.setDouble(3, parcela.getPercentual());
                    statementInserirParcela.setLong(4, condicaoPagamento.getId());
                    statementInserirParcela.setLong(5, parcela.getFormaPagamento().getId());
                    statementInserirParcela.executeUpdate();
                }

                connection.commit(); // Confirma a transação
            } catch (SQLException e) {
                connection.rollback(); // Reverte a transação em caso de erro
                throw e;
            }
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