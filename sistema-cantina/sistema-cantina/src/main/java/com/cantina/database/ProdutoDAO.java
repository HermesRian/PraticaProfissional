package com.cantina.database;

import com.cantina.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.*;

public class ProdutoDAO {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, quantidade_estoque, descricao, codigo, ativo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produto.getNome());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidadeEstoque());
            statement.setString(4, produto.getDescricao());
            statement.setString(5, produto.getCodigo());
            statement.setBoolean(6, produto.getAtivo() != null ? produto.getAtivo() : true);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getLong("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidade_estoque"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setCodigo(resultSet.getString("codigo"));
                produto.setAtivo(resultSet.getBoolean("ativo"));

                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarPorId(Long id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produto produto = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getLong("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidade_estoque"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setCodigo(resultSet.getString("codigo"));
                produto.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade_estoque = ?, descricao = ?, codigo = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produto.getNome());
            statement.setBigDecimal(2, produto.getPreco());
            statement.setInt(3, produto.getQuantidadeEstoque());
            statement.setString(4, produto.getDescricao());
            statement.setString(5, produto.getCodigo());
            statement.setBoolean(6, produto.getAtivo() != null ? produto.getAtivo() : true);
            statement.setLong(7, produto.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}