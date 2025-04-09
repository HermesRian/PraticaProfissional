package com.cantina.database;

import com.cantina.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cnpjCpf, endereco, numero, complemento, bairro, cep, cidade_id, telefone, email, ativo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCnpjCpf());
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getNumero());
            statement.setString(6, cliente.getComplemento());
            statement.setString(7, cliente.getBairro());
            statement.setString(8, cliente.getCep());
            statement.setLong(9, cliente.getCidadeId());
            statement.setString(10, cliente.getTelefone());
            statement.setString(11, cliente.getEmail());
            statement.setBoolean(12, cliente.getAtivo());
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
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setComplemento(resultSet.getString("complemento"));
                cliente.setBairro(resultSet.getString("bairro"));
                cliente.setCep(resultSet.getString("cep"));
                cliente.setCidadeId(resultSet.getLong("cidade_id"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setAtivo(resultSet.getBoolean("ativo"));
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
                cliente.setNumero(resultSet.getString("numero"));
                cliente.setComplemento(resultSet.getString("complemento"));
                cliente.setBairro(resultSet.getString("bairro"));
                cliente.setCep(resultSet.getString("cep"));
                cliente.setCidadeId(resultSet.getLong("cidade_id"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cnpjCpf, endereco = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade_id = ?, telefone = ?, email = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCnpjCpf());
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getNumero());
            statement.setString(6, cliente.getComplemento());
            statement.setString(7, cliente.getBairro());
            statement.setString(8, cliente.getCep());
            statement.setLong(9, cliente.getCidadeId());
            statement.setString(10, cliente.getTelefone());
            statement.setString(11, cliente.getEmail());
            statement.setBoolean(12, cliente.getAtivo());
            statement.setLong(13, cliente.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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