package com.cantina.database;

import com.cantina.entities.Fornecedor;
import com.cantina.exceptions.DuplicateCnpjException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FornecedorDAO {

    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (razao_social, nome_fantasia, cnpj, email, telefone, endereco, numero, complemento, bairro, cep, cidade_id, inscricao_estadual, ativo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fornecedor.getRazaoSocial());
            statement.setString(2, fornecedor.getNomeFantasia());
            statement.setString(3, fornecedor.getCnpj());
            statement.setString(4, fornecedor.getEmail());
            statement.setString(5, fornecedor.getTelefone());
            statement.setString(6, fornecedor.getEndereco());
            statement.setString(7, fornecedor.getNumero());
            statement.setString(8, fornecedor.getComplemento());
            statement.setString(9, fornecedor.getBairro());
            statement.setString(10, fornecedor.getCep());
            statement.setLong(11, fornecedor.getCidadeId());
            statement.setString(12, fornecedor.getInscricaoEstadual());
            statement.setBoolean(13, fornecedor.getAtivo());
            statement.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("fornecedor.cnpj")) {
                throw new DuplicateCnpjException("Já existe um fornecedor cadastrado com este CNPJ.");
            }
            throw new RuntimeException("Erro inesperado ao salvar o fornecedor.", e);
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
                fornecedor.setRazaoSocial(resultSet.getString("razao_social"));
                fornecedor.setNomeFantasia(resultSet.getString("nome_fantasia"));
                fornecedor.setCnpj(resultSet.getString("cnpj"));
                fornecedor.setEmail(resultSet.getString("email"));
                fornecedor.setTelefone(resultSet.getString("telefone"));
                fornecedor.setEndereco(resultSet.getString("endereco"));
                fornecedor.setNumero(resultSet.getString("numero"));
                fornecedor.setComplemento(resultSet.getString("complemento"));
                fornecedor.setBairro(resultSet.getString("bairro"));
                fornecedor.setCep(resultSet.getString("cep"));
                fornecedor.setCidadeId(resultSet.getLong("cidade_id"));
                fornecedor.setInscricaoEstadual(resultSet.getString("inscricao_estadual"));
                fornecedor.setAtivo(resultSet.getBoolean("ativo"));
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
                fornecedor.setRazaoSocial(resultSet.getString("razao_social"));
                fornecedor.setNomeFantasia(resultSet.getString("nome_fantasia"));
                fornecedor.setCnpj(resultSet.getString("cnpj"));
                fornecedor.setEmail(resultSet.getString("email"));
                fornecedor.setTelefone(resultSet.getString("telefone"));
                fornecedor.setEndereco(resultSet.getString("endereco"));
                fornecedor.setNumero(resultSet.getString("numero"));
                fornecedor.setComplemento(resultSet.getString("complemento"));
                fornecedor.setBairro(resultSet.getString("bairro"));
                fornecedor.setCep(resultSet.getString("cep"));
                fornecedor.setCidadeId(resultSet.getLong("cidade_id"));
                fornecedor.setInscricaoEstadual(resultSet.getString("inscricao_estadual"));
                fornecedor.setAtivo(resultSet.getBoolean("ativo"));
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
    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET razao_social = ?, nome_fantasia = ?, cnpj = ?, email = ?, telefone = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade_id = ?, inscricao_estadual = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fornecedor.getRazaoSocial());
            statement.setString(2, fornecedor.getNomeFantasia());
            statement.setString(3, fornecedor.getCnpj());
            statement.setString(4, fornecedor.getEmail());
            statement.setString(5, fornecedor.getTelefone());
            statement.setString(6, fornecedor.getEndereco());
            statement.setString(7, fornecedor.getNumero());
            statement.setString(8, fornecedor.getComplemento());
            statement.setString(9, fornecedor.getBairro());
            statement.setString(10, fornecedor.getCep());
            statement.setLong(11, fornecedor.getCidadeId());
            statement.setString(12, fornecedor.getInscricaoEstadual());
            statement.setBoolean(13, fornecedor.getAtivo());
            statement.setLong(14, fornecedor.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().contains("fornecedor.cnpj")) {
                throw new DuplicateCnpjException("Já existe um fornecedor cadastrado com este CNPJ.");
            }
            throw new RuntimeException("Erro ao atualizar o fornecedor.", e);
        }
    }
}