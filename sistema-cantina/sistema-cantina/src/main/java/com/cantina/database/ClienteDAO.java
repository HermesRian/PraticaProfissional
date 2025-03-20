package com.cantina.database;

import com.cantina.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cnpjCpf, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCnpjCpf());
            statement.setString(3, cliente.getEndereco());
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getLong("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCnpjCpf(resultSet.getString("cnpjCpf"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente buscarPorId(Long id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente cliente = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getLong("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCnpjCpf(resultSet.getString("cnpjCpf"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}