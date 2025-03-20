package com.cantina.database;

import com.cantina.entities.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo, telefone, email, nivelAcesso) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getCargo());
            statement.setString(4, funcionario.getTelefone());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getNivelAcesso());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getLong("id"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setTelefone(resultSet.getString("telefone"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setNivelAcesso(resultSet.getString("nivelAcesso"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public Funcionario buscarPorId(Long id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getLong("id"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setTelefone(resultSet.getString("telefone"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setNivelAcesso(resultSet.getString("nivelAcesso"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}