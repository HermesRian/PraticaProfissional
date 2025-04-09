package com.cantina.database;

import com.cantina.entities.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo, salario, email, telefone, endereco, numero, complemento, bairro, cep, cidade_id, ativo, data_admissao, data_demissao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getCargo());
            statement.setBigDecimal(4, funcionario.getSalario());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getTelefone());
            statement.setString(7, funcionario.getEndereco());
            statement.setString(8, funcionario.getNumero());
            statement.setString(9, funcionario.getComplemento());
            statement.setString(10, funcionario.getBairro());
            statement.setString(11, funcionario.getCep());
            statement.setLong(12, funcionario.getCidadeId());
            statement.setBoolean(13, funcionario.getAtivo());
            statement.setDate(14, funcionario.getDataAdmissao() != null ? new java.sql.Date(funcionario.getDataAdmissao().getTime()) : null);
            statement.setDate(15, funcionario.getDataDemissao() != null ? new java.sql.Date(funcionario.getDataDemissao().getTime()) : null);
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
                funcionario.setSalario(resultSet.getBigDecimal("salario"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setTelefone(resultSet.getString("telefone"));
                funcionario.setEndereco(resultSet.getString("endereco"));
                funcionario.setNumero(resultSet.getString("numero"));
                funcionario.setComplemento(resultSet.getString("complemento"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidadeId(resultSet.getLong("cidade_id"));
                funcionario.setAtivo(resultSet.getBoolean("ativo"));
                funcionario.setDataAdmissao(resultSet.getDate("data_admissao"));
                funcionario.setDataDemissao(resultSet.getDate("data_demissao"));
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
                funcionario.setSalario(resultSet.getBigDecimal("salario"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setTelefone(resultSet.getString("telefone"));
                funcionario.setEndereco(resultSet.getString("endereco"));
                funcionario.setNumero(resultSet.getString("numero"));
                funcionario.setComplemento(resultSet.getString("complemento"));
                funcionario.setBairro(resultSet.getString("bairro"));
                funcionario.setCep(resultSet.getString("cep"));
                funcionario.setCidadeId(resultSet.getLong("cidade_id"));
                funcionario.setAtivo(resultSet.getBoolean("ativo"));
                funcionario.setDataAdmissao(resultSet.getDate("data_admissao"));
                funcionario.setDataDemissao(resultSet.getDate("data_demissao"));
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

    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, cargo = ?, salario = ?, email = ?, telefone = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade_id = ?, ativo = ?, data_admissao = ?, data_demissao = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setString(3, funcionario.getCargo());
            statement.setBigDecimal(4, funcionario.getSalario());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getTelefone());
            statement.setString(7, funcionario.getEndereco());
            statement.setString(8, funcionario.getNumero());
            statement.setString(9, funcionario.getComplemento());
            statement.setString(10, funcionario.getBairro());
            statement.setString(11, funcionario.getCep());
            statement.setLong(12, funcionario.getCidadeId());
            statement.setBoolean(13, funcionario.getAtivo());
            statement.setDate(14, funcionario.getDataAdmissao() != null ? new java.sql.Date(funcionario.getDataAdmissao().getTime()) : null);
            statement.setDate(15, funcionario.getDataDemissao() != null ? new java.sql.Date(funcionario.getDataDemissao().getTime()) : null);
            statement.setLong(16, funcionario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}