package com.cantina.database;

import com.cantina.entities.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, cnpj, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fornecedor.getNome());
            statement.setString(2, fornecedor.getCnpj());
            statement.setString(3, fornecedor.getEndereco());
            statement.setString(4, fornecedor.getTelefone());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(resultSet.getLong("id"));
                fornecedor.setNome(resultSet.getString("nome"));
                fornecedor.setCnpj(resultSet.getString("cnpj"));
                fornecedor.setEndereco(resultSet.getString("endereco"));
                fornecedor.setTelefone(resultSet.getString("telefone"));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedores;
    }

    public Fornecedor buscarPorId(Long id) {
        String sql = "SELECT * FROM fornecedor WHERE id = ?";
        Fornecedor fornecedor = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(resultSet.getLong("id"));
                fornecedor.setNome(resultSet.getString("nome"));
                fornecedor.setCnpj(resultSet.getString("cnpj"));
                fornecedor.setEndereco(resultSet.getString("endereco"));
                fornecedor.setTelefone(resultSet.getString("telefone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedor;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM fornecedor WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}