package com.cantina.database;

import com.cantina.entities.Transportadora;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportadoraDAO {

    public void salvar(Transportadora transportadora) {
        String sql = "INSERT INTO transportadora (razao_social, nome_fantasia, cnpj, email, telefone, endereco, cidade_id, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transportadora.getRazaoSocial());
            statement.setString(2, transportadora.getNomeFantasia());
            statement.setString(3, transportadora.getCnpj());
            statement.setString(4, transportadora.getEmail());
            statement.setString(5, transportadora.getTelefone());
            statement.setString(6, transportadora.getEndereco());
            statement.setObject(7, transportadora.getCidadeId());
            statement.setBoolean(8, transportadora.isAtivo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transportadora> listarTodas() {
        List<Transportadora> transportadoras = new ArrayList<>();
        String sql = "SELECT * FROM transportadora";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Transportadora transportadora = new Transportadora();
                transportadora.setId(resultSet.getLong("id"));
                transportadora.setRazaoSocial(resultSet.getString("razao_social"));
                transportadora.setNomeFantasia(resultSet.getString("nome_fantasia"));
                transportadora.setCnpj(resultSet.getString("cnpj"));
                transportadora.setEmail(resultSet.getString("email"));
                transportadora.setTelefone(resultSet.getString("telefone"));
                transportadora.setEndereco(resultSet.getString("endereco"));
                transportadora.setCidadeId(resultSet.getObject("cidade_id", Long.class));
                transportadora.setAtivo(resultSet.getBoolean("ativo"));
                transportadoras.add(transportadora);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transportadoras;
    }

    public Transportadora buscarPorId(Long id) {
        String sql = "SELECT * FROM transportadora WHERE id = ?";
        Transportadora transportadora = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                transportadora = new Transportadora();
                transportadora.setId(resultSet.getLong("id"));
                transportadora.setRazaoSocial(resultSet.getString("razao_social"));
                transportadora.setNomeFantasia(resultSet.getString("nome_fantasia"));
                transportadora.setCnpj(resultSet.getString("cnpj"));
                transportadora.setEmail(resultSet.getString("email"));
                transportadora.setTelefone(resultSet.getString("telefone"));
                transportadora.setEndereco(resultSet.getString("endereco"));
                transportadora.setCidadeId(resultSet.getObject("cidade_id", Long.class));
                transportadora.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transportadora;
    }

    public void atualizar(Transportadora transportadora) {
        String sql = "UPDATE transportadora SET razao_social = ?, nome_fantasia = ?, cnpj = ?, email = ?, telefone = ?, endereco = ?, cidade_id = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, transportadora.getRazaoSocial());
            statement.setString(2, transportadora.getNomeFantasia());
            statement.setString(3, transportadora.getCnpj());
            statement.setString(4, transportadora.getEmail());
            statement.setString(5, transportadora.getTelefone());
            statement.setString(6, transportadora.getEndereco());
            statement.setObject(7, transportadora.getCidadeId());
            statement.setBoolean(8, transportadora.isAtivo());
            statement.setLong(9, transportadora.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM transportadora WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}