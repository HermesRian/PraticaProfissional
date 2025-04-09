package com.cantina.database;

import com.cantina.entities.ModalidadeNfe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModalidadeNfeDAO {

    public void salvar(ModalidadeNfe modalidadeNfe) {
        String sql = "INSERT INTO modalidade_nfe (codigo, descricao, ativo) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, modalidadeNfe.getCodigo());
            statement.setString(2, modalidadeNfe.getDescricao());
            statement.setBoolean(3, modalidadeNfe.isAtivo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ModalidadeNfe> listarTodas() {
        List<ModalidadeNfe> modalidades = new ArrayList<>();
        String sql = "SELECT * FROM modalidade_nfe";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ModalidadeNfe modalidade = new ModalidadeNfe();
                modalidade.setId(resultSet.getLong("id"));
                modalidade.setCodigo(resultSet.getString("codigo"));
                modalidade.setDescricao(resultSet.getString("descricao"));
                modalidade.setAtivo(resultSet.getBoolean("ativo"));
                modalidades.add(modalidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modalidades;
    }

    public ModalidadeNfe buscarPorId(Long id) {
        String sql = "SELECT * FROM modalidade_nfe WHERE id = ?";
        ModalidadeNfe modalidade = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                modalidade = new ModalidadeNfe();
                modalidade.setId(resultSet.getLong("id"));
                modalidade.setCodigo(resultSet.getString("codigo"));
                modalidade.setDescricao(resultSet.getString("descricao"));
                modalidade.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modalidade;
    }

    public void atualizar(ModalidadeNfe modalidadeNfe) {
        String sql = "UPDATE modalidade_nfe SET codigo = ?, descricao = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, modalidadeNfe.getCodigo());
            statement.setString(2, modalidadeNfe.getDescricao());
            statement.setBoolean(3, modalidadeNfe.isAtivo());
            statement.setLong(4, modalidadeNfe.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM modalidade_nfe WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}