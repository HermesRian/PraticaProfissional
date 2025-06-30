package com.cantina.database;

import com.cantina.entities.FuncaoFuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncaoFuncionarioDAO {

    public void salvar(FuncaoFuncionario funcaoFuncionario) {
        String sql = "INSERT INTO funcao_funcionario (descricao, ativo, nome, requer_cnh, carga_horaria, observacao, user_criacao, user_atualizacao) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcaoFuncionario.getDescricao());
            statement.setBoolean(2, funcaoFuncionario.getAtivo());
            statement.setString(3, funcaoFuncionario.getNome());
            statement.setBoolean(4, funcaoFuncionario.getRequerCnh());
            statement.setBigDecimal(5, funcaoFuncionario.getCargaHoraria());
            statement.setString(6, funcaoFuncionario.getObservacao());
            statement.setString(7, funcaoFuncionario.getUserCriacao());
            statement.setString(8, funcaoFuncionario.getUserAtualizacao());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FuncaoFuncionario> listarTodos() {
        List<FuncaoFuncionario> funcoes = new ArrayList<>();
        String sql = "SELECT * FROM funcao_funcionario";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FuncaoFuncionario funcao = mapResultSetToFuncaoFuncionario(resultSet);
                funcoes.add(funcao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcoes;
    }

    public FuncaoFuncionario buscarPorId(Long id) {
        String sql = "SELECT * FROM funcao_funcionario WHERE id = ?";
        FuncaoFuncionario funcaoFuncionario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcaoFuncionario = mapResultSetToFuncaoFuncionario(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcaoFuncionario;
    }

    public void atualizar(FuncaoFuncionario funcaoFuncionario) {
        String sql = "UPDATE funcao_funcionario SET descricao = ?, ativo = ?, nome = ?, requer_cnh = ?, carga_horaria = ?, observacao = ?, user_atualizacao = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, funcaoFuncionario.getDescricao());
            statement.setBoolean(2, funcaoFuncionario.getAtivo());
            statement.setString(3, funcaoFuncionario.getNome());
            statement.setBoolean(4, funcaoFuncionario.getRequerCnh());
            statement.setBigDecimal(5, funcaoFuncionario.getCargaHoraria());
            statement.setString(6, funcaoFuncionario.getObservacao());
            statement.setString(7, funcaoFuncionario.getUserAtualizacao());
            statement.setLong(8, funcaoFuncionario.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM funcao_funcionario WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FuncaoFuncionario> listarAtivos() {
        List<FuncaoFuncionario> funcoes = new ArrayList<>();
        String sql = "SELECT * FROM funcao_funcionario WHERE ativo = true";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FuncaoFuncionario funcao = mapResultSetToFuncaoFuncionario(resultSet);
                funcoes.add(funcao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcoes;
    }

    private FuncaoFuncionario mapResultSetToFuncaoFuncionario(ResultSet resultSet) throws SQLException {
        FuncaoFuncionario funcaoFuncionario = new FuncaoFuncionario();
        funcaoFuncionario.setId(resultSet.getLong("id"));
        funcaoFuncionario.setDescricao(resultSet.getString("descricao"));
        funcaoFuncionario.setAtivo(resultSet.getBoolean("ativo"));
        funcaoFuncionario.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
        funcaoFuncionario.setUltimaModificacao(resultSet.getTimestamp("ultima_modificacao"));
        funcaoFuncionario.setNome(resultSet.getString("nome"));
        funcaoFuncionario.setRequerCnh(resultSet.getBoolean("requer_cnh"));
        funcaoFuncionario.setCargaHoraria(resultSet.getBigDecimal("carga_horaria"));
        funcaoFuncionario.setObservacao(resultSet.getString("observacao"));
        funcaoFuncionario.setUserCriacao(resultSet.getString("user_criacao"));
        funcaoFuncionario.setUserAtualizacao(resultSet.getString("user_atualizacao"));
        return funcaoFuncionario;
    }
}
