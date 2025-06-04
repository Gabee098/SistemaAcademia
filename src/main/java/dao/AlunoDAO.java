package dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.ConnectionFactory;

public class AlunoDAO {

    private final Connection con;

    public AlunoDAO() {
        
        con = ConnectionFactory.getConnection();
        
    }

    public void inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, cpf, telefone, email, data_nascimento) VALUES (?, ?, ?, ?, ?)";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getEmail());
             stmt.setDate(5, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao buscar aluno por ID: " + e.getMessage());
        }
    }
    
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, cpf, telefone, email, data_nascimento) VALUES (?, ?, ?, ?, ?)";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getEmail());
            stmt.setDate(5, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM Aluno WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, cpf = ?, telefone = ?, email = ?, data_nascimento = ? WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getEmail());
            stmt.setDate(5, java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(6, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Aluno WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento").toLocalDate()
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
        
    }
    public boolean verificarCpfExistente(String cpf, int idIgnorar) {
    for (Aluno aluno : listarTodos()) {
        if (aluno.getCpf().equals(cpf) && aluno.getId() != idIgnorar) {
            return true;
        }
    }
    return false;
}
}