package com.cantina.database;

import com.cantina.entities.ItemNotaFiscal;
import com.cantina.entities.NotaFiscal;
import com.cantina.entities.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemNotaFiscalDAO {

    public void salvar(ItemNotaFiscal item) {
        String sql = "INSERT INTO item_nota_fiscal (quantidade, preco_unitario, produto_id, nota_fiscal_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, item.getQuantidade());
            statement.setDouble(2, item.getPrecoUnitario());
            statement.setLong(3, item.getProduto().getId());
            statement.setLong(4, item.getNotaFiscal().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemNotaFiscal> listarTodos() {
        List<ItemNotaFiscal> itens = new ArrayList<>();
        String sql = "SELECT * FROM item_nota_fiscal";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ItemNotaFiscal item = new ItemNotaFiscal();
                item.setId(resultSet.getLong("id"));
                item.setQuantidade(resultSet.getInt("quantidade"));
                item.setPrecoUnitario(BigDecimal.valueOf(resultSet.getDouble("preco_unitario")));

                // Carregar Produto
                ProdutoDAO produtoDAO = new ProdutoDAO();
                item.setProduto(produtoDAO.buscarPorId(resultSet.getLong("produto_id")));

                // Carregar NotaFiscal
                NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
                item.setNotaFiscal(notaFiscalDAO.buscarPorId(resultSet.getLong("nota_fiscal_id")));

                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    public ItemNotaFiscal buscarPorId(Long id) {
        String sql = "SELECT * FROM item_nota_fiscal WHERE id = ?";
        ItemNotaFiscal item = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new ItemNotaFiscal();
                item.setId(resultSet.getLong("id"));
                item.setQuantidade(resultSet.getInt("quantidade"));
                item.setPrecoUnitario(BigDecimal.valueOf(resultSet.getDouble("preco_unitario")));

                // Carregar Produto
                ProdutoDAO produtoDAO = new ProdutoDAO();
                item.setProduto(produtoDAO.buscarPorId(resultSet.getLong("produto_id")));

                // Carregar NotaFiscal
                NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
                item.setNotaFiscal(notaFiscalDAO.buscarPorId(resultSet.getLong("nota_fiscal_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM item_nota_fiscal WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}