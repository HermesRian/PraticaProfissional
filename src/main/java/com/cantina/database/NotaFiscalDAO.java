package com.cantina.database;

import com.cantina.entities.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDAO {

    public void salvar(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO nota_fiscal (data_emissao, valor_total, condicao_pagamento_id, forma_pagamento_id, fornecedor_id, numero, serie, chave_acesso, cliente_id, transportadora_id, veiculo_id, modalidade_id, cancelada) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, notaFiscal.getDataEmissao() != null ? new Timestamp(notaFiscal.getDataEmissao().getTime()) : null);
            statement.setBigDecimal(2, notaFiscal.getValorTotal());
            statement.setLong(3, notaFiscal.getCondicaoPagamento() != null ? notaFiscal.getCondicaoPagamento().getId() : null);
            statement.setLong(4, notaFiscal.getFormaPagamento() != null ? notaFiscal.getFormaPagamento().getId() : null);
            statement.setLong(5, notaFiscal.getFornecedor() != null ? notaFiscal.getFornecedor().getId() : null);
            statement.setString(6, notaFiscal.getNumero());
            statement.setString(7, notaFiscal.getSerie());
            statement.setString(8, notaFiscal.getChaveAcesso());
            statement.setLong(9, notaFiscal.getCliente() != null ? notaFiscal.getCliente().getId() : null);
            statement.setLong(10, notaFiscal.getTransportadora() != null ? notaFiscal.getTransportadora().getId() : null);
            statement.setLong(11, notaFiscal.getVeiculo() != null ? notaFiscal.getVeiculo().getId() : null);
            statement.setLong(12, notaFiscal.getModalidade() != null ? notaFiscal.getModalidade().getId() : null);
            statement.setBoolean(13, notaFiscal.getCancelada() != null ? notaFiscal.getCancelada() : false);

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
                notaFiscal.setDataEmissao(resultSet.getTimestamp("data_emissao"));
                notaFiscal.setValorTotal(resultSet.getBigDecimal("valor_total"));
                // Relacionamentos devem ser carregados conforme necessário
                notaFiscal.setNumero(resultSet.getString("numero"));
                notaFiscal.setSerie(resultSet.getString("serie"));
                notaFiscal.setChaveAcesso(resultSet.getString("chave_acesso"));
                notaFiscal.setCancelada(resultSet.getBoolean("cancelada"));
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
                notaFiscal.setDataEmissao(resultSet.getTimestamp("data_emissao"));
                notaFiscal.setValorTotal(resultSet.getBigDecimal("valor_total"));
                // Relacionamentos devem ser carregados conforme necessário
                notaFiscal.setNumero(resultSet.getString("numero"));
                notaFiscal.setSerie(resultSet.getString("serie"));
                notaFiscal.setChaveAcesso(resultSet.getString("chave_acesso"));
                notaFiscal.setCancelada(resultSet.getBoolean("cancelada"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notaFiscal;
    }

    public void atualizar(NotaFiscal notaFiscal) {
        String sql = "UPDATE nota_fiscal SET data_emissao = ?, valor_total = ?, condicao_pagamento_id = ?, forma_pagamento_id = ?, fornecedor_id = ?, numero = ?, serie = ?, chave_acesso = ?, cliente_id = ?, transportadora_id = ?, veiculo_id = ?, modalidade_id = ?, cancelada = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, notaFiscal.getDataEmissao() != null ? new Timestamp(notaFiscal.getDataEmissao().getTime()) : null);
            statement.setBigDecimal(2, notaFiscal.getValorTotal());
            statement.setLong(3, notaFiscal.getCondicaoPagamento() != null ? notaFiscal.getCondicaoPagamento().getId() : null);
            statement.setLong(4, notaFiscal.getFormaPagamento() != null ? notaFiscal.getFormaPagamento().getId() : null);
            statement.setLong(5, notaFiscal.getFornecedor() != null ? notaFiscal.getFornecedor().getId() : null);
            statement.setString(6, notaFiscal.getNumero());
            statement.setString(7, notaFiscal.getSerie());
            statement.setString(8, notaFiscal.getChaveAcesso());
            statement.setLong(9, notaFiscal.getCliente() != null ? notaFiscal.getCliente().getId() : null);
            statement.setLong(10, notaFiscal.getTransportadora() != null ? notaFiscal.getTransportadora().getId() : null);
            statement.setLong(11, notaFiscal.getVeiculo() != null ? notaFiscal.getVeiculo().getId() : null);
            statement.setLong(12, notaFiscal.getModalidade() != null ? notaFiscal.getModalidade().getId() : null);
            statement.setBoolean(13, notaFiscal.getCancelada() != null ? notaFiscal.getCancelada() : false);
            statement.setLong(14, notaFiscal.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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