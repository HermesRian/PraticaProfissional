package com.cantina.database;

import com.cantina.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidadeEstoque) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produto.getNome());
//            statement.setString(2, produto.getDescricao());
            statement.setBigDecimal(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidadeEstoque());

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
//                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidadeEstoque"));

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
              //  produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidadeEstoque"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
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