package com.cantina.database;

import com.cantina.entities.Cidade;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CidadeDAO {

    public Cidade salvar(Cidade cidade) {
        String sql = "INSERT INTO cidade (nome, codigo_ibge, estado_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cidade.getNome());
            statement.setString(2, cidade.getCodigoIbge());
            statement.setLong(3, cidade.getEstadoId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidade;
    }

    public List<Cidade> listarTodas() {
        List<Cidade> cidades = new ArrayList<>();
        String sql = "SELECT * FROM cidade";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(resultSet.getLong("id"));
                cidade.setNome(resultSet.getString("nome"));
                cidade.setCodigoIbge(resultSet.getString("codigo_ibge"));
                cidade.setEstadoId(resultSet.getLong("estado_id"));
                cidades.add(cidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidades;
    }

    public Cidade buscarPorId(Long id) {
        String sql = "SELECT * FROM cidade WHERE id = ?";
        Cidade cidade = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cidade = new Cidade();
                cidade.setId(resultSet.getLong("id"));
                cidade.setNome(resultSet.getString("nome"));
                cidade.setCodigoIbge(resultSet.getString("codigo_ibge"));
                cidade.setEstadoId(resultSet.getLong("estado_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidade;
    }

    public void atualizar(Cidade cidade) {
        String sql = "UPDATE cidade SET nome = ?, codigo_ibge = ?, estado_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cidade.getNome());
            statement.setString(2, cidade.getCodigoIbge());
            statement.setLong(3, cidade.getEstadoId());
            statement.setLong(4, cidade.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM cidade WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}