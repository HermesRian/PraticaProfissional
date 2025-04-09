package com.cantina.database;

import com.cantina.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, modelo, marca, ano, capacidade, transportadora_id, ativo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getModelo());
            statement.setString(3, veiculo.getMarca());
            statement.setObject(4, veiculo.getAno());
            statement.setBigDecimal(5, veiculo.getCapacidade());
            statement.setObject(6, veiculo.getTransportadoraId());
            statement.setBoolean(7, veiculo.isAtivo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(resultSet.getLong("id"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setModelo(resultSet.getString("modelo"));
                veiculo.setMarca(resultSet.getString("marca"));
                veiculo.setAno(resultSet.getObject("ano", Integer.class));
                veiculo.setCapacidade(resultSet.getBigDecimal("capacidade"));
                veiculo.setTransportadoraId(resultSet.getObject("transportadora_id", Long.class));
                veiculo.setAtivo(resultSet.getBoolean("ativo"));
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    public Veiculo buscarPorId(Long id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        Veiculo veiculo = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                veiculo = new Veiculo();
                veiculo.setId(resultSet.getLong("id"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setModelo(resultSet.getString("modelo"));
                veiculo.setMarca(resultSet.getString("marca"));
                veiculo.setAno(resultSet.getObject("ano", Integer.class));
                veiculo.setCapacidade(resultSet.getBigDecimal("capacidade"));
                veiculo.setTransportadoraId(resultSet.getObject("transportadora_id", Long.class));
                veiculo.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculo;
    }

    public void atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, marca = ?, ano = ?, capacidade = ?, transportadora_id = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getModelo());
            statement.setString(3, veiculo.getMarca());
            statement.setObject(4, veiculo.getAno());
            statement.setBigDecimal(5, veiculo.getCapacidade());
            statement.setObject(6, veiculo.getTransportadoraId());
            statement.setBoolean(7, veiculo.isAtivo());
            statement.setLong(8, veiculo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}