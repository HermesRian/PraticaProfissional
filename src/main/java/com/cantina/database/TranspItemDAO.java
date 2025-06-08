package com.cantina.database;

import com.cantina.entities.TranspItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TranspItemDAO {

    public void salvar(TranspItem transpItem) {
        String sql = "INSERT INTO transp_item (codigo, descricao, transportadora_id, codigo_transp, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transpItem.getCodigo());
            statement.setString(2, transpItem.getDescricao());
            statement.setObject(3, transpItem.getTransportadoraId());
            statement.setString(4, transpItem.getCodigoTransp());
            statement.setBoolean(5, transpItem.isAtivo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TranspItem> listarTodos() {
        List<TranspItem> itens = new ArrayList<>();
        String sql = "SELECT * FROM transp_item";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TranspItem item = new TranspItem();
                item.setId(resultSet.getLong("id"));
                item.setCodigo(resultSet.getString("codigo"));
                item.setDescricao(resultSet.getString("descricao"));
                item.setTransportadoraId(resultSet.getObject("transportadora_id", Long.class));
                item.setCodigoTransp(resultSet.getString("codigo_transp"));
                item.setAtivo(resultSet.getBoolean("ativo"));
                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    public TranspItem buscarPorId(Long id) {
        String sql = "SELECT * FROM transp_item WHERE id = ?";
        TranspItem item = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new TranspItem();
                item.setId(resultSet.getLong("id"));
                item.setCodigo(resultSet.getString("codigo"));
                item.setDescricao(resultSet.getString("descricao"));
                item.setTransportadoraId(resultSet.getObject("transportadora_id", Long.class));
                item.setCodigoTransp(resultSet.getString("codigo_transp"));
                item.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void atualizar(TranspItem transpItem) {
        String sql = "UPDATE transp_item SET codigo = ?, descricao = ?, transportadora_id = ?, codigo_transp = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transpItem.getCodigo());
            statement.setString(2, transpItem.getDescricao());
            statement.setObject(3, transpItem.getTransportadoraId());
            statement.setString(4, transpItem.getCodigoTransp());
            statement.setBoolean(5, transpItem.isAtivo());
            statement.setLong(6, transpItem.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM transp_item WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}