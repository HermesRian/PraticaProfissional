package com.cantina.database;

import com.cantina.entities.ProdutoFornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFornecedorDAO {

    public void salvar(ProdutoFornecedor produtoFornecedor) {
        String sql = "INSERT INTO produto_fornecedor (produto_id, fornecedor_id, codigo_prod, custo, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, produtoFornecedor.getProdutoId());
            statement.setLong(2, produtoFornecedor.getFornecedorId());
            statement.setString(3, produtoFornecedor.getCodigoProd());
            statement.setBigDecimal(4, produtoFornecedor.getCusto());
            statement.setBoolean(5, produtoFornecedor.isAtivo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProdutoFornecedor> listarTodos() {
        List<ProdutoFornecedor> produtosFornecedores = new ArrayList<>();
        String sql = "SELECT * FROM produto_fornecedor";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
                produtoFornecedor.setId(resultSet.getLong("id"));
                produtoFornecedor.setProdutoId(resultSet.getLong("produto_id"));
                produtoFornecedor.setFornecedorId(resultSet.getLong("fornecedor_id"));
                produtoFornecedor.setCodigoProd(resultSet.getString("codigo_prod"));
                produtoFornecedor.setCusto(resultSet.getBigDecimal("custo"));
                produtoFornecedor.setAtivo(resultSet.getBoolean("ativo"));
                produtosFornecedores.add(produtoFornecedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtosFornecedores;
    }

    public ProdutoFornecedor buscarPorId(Long id) {
        String sql = "SELECT * FROM produto_fornecedor WHERE id = ?";
        ProdutoFornecedor produtoFornecedor = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produtoFornecedor = new ProdutoFornecedor();
                produtoFornecedor.setId(resultSet.getLong("id"));
                produtoFornecedor.setProdutoId(resultSet.getLong("produto_id"));
                produtoFornecedor.setFornecedorId(resultSet.getLong("fornecedor_id"));
                produtoFornecedor.setCodigoProd(resultSet.getString("codigo_prod"));
                produtoFornecedor.setCusto(resultSet.getBigDecimal("custo"));
                produtoFornecedor.setAtivo(resultSet.getBoolean("ativo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtoFornecedor;
    }

    public void atualizar(ProdutoFornecedor produtoFornecedor) {
        String sql = "UPDATE produto_fornecedor SET produto_id = ?, fornecedor_id = ?, codigo_prod = ?, custo = ?, ativo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, produtoFornecedor.getProdutoId());
            statement.setLong(2, produtoFornecedor.getFornecedorId());
            statement.setString(3, produtoFornecedor.getCodigoProd());
            statement.setBigDecimal(4, produtoFornecedor.getCusto());
            statement.setBoolean(5, produtoFornecedor.isAtivo());
            statement.setLong(6, produtoFornecedor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM produto_fornecedor WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}