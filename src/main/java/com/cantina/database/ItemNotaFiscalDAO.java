package com.cantina.database;

import com.cantina.entities.ItemNotaFiscal;
import com.cantina.entities.NotaFiscal;
import com.cantina.entities.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemNotaFiscalDAO {

    public void salvar(ItemNotaFiscal item) {
        String sql = "INSERT INTO item_nota_fiscal (quantidade, nota_fiscal_id, produto_id, descricao, valor, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, item.getQuantidade());
            statement.setLong(2, item.getNotaFiscal().getId());
            statement.setLong(3, item.getProduto().getId());
            statement.setString(4, item.getDescricao());
            statement.setBigDecimal(5, item.getValor());
            statement.setBigDecimal(6, item.getValorUnitario());
            statement.setBigDecimal(7, item.getValorTotal());

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
                item.setDescricao(resultSet.getString("descricao"));
                item.setValor(resultSet.getBigDecimal("valor"));
                item.setValorUnitario(resultSet.getBigDecimal("valor_unitario"));
                item.setValorTotal(resultSet.getBigDecimal("valor_total"));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                item.setProduto(produtoDAO.buscarPorId(resultSet.getLong("produto_id")));

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
                item.setDescricao(resultSet.getString("descricao"));
                item.setValor(resultSet.getBigDecimal("valor"));
                item.setValorUnitario(resultSet.getBigDecimal("valor_unitario"));
                item.setValorTotal(resultSet.getBigDecimal("valor_total"));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                item.setProduto(produtoDAO.buscarPorId(resultSet.getLong("produto_id")));

                NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
                item.setNotaFiscal(notaFiscalDAO.buscarPorId(resultSet.getLong("nota_fiscal_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void atualizar(ItemNotaFiscal item) {
        String sql = "UPDATE item_nota_fiscal SET quantidade = ?, nota_fiscal_id = ?, produto_id = ?, descricao = ?, valor = ?, valor_unitario = ?, valor_total = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, item.getQuantidade());
            statement.setLong(2, item.getNotaFiscal().getId());
            statement.setLong(3, item.getProduto().getId());
            statement.setString(4, item.getDescricao());
            statement.setBigDecimal(5, item.getValor());
            statement.setBigDecimal(6, item.getValorUnitario());
            statement.setBigDecimal(7, item.getValorTotal());
            statement.setLong(8, item.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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