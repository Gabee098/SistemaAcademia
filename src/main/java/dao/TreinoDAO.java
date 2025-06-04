package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Treino;
import util.ConnectionFactory;

public class TreinoDAO {

    private Connection con = null;

    public TreinoDAO() {
        con = ConnectionFactory.getConnection();

    }

    public boolean salvar(Treino Treino) {
        String sql = "INSERT INTO Treino (aluno_id, id, descricao, tipo_treino, duracao_minutos, data) VALUES (?, ?, ?, ?, ?, ?)";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Treino.getIdAluno());
            stmt.setInt(2, Treino.getId());
            stmt.setString(3, Treino.getDescricao());
            stmt.setString(4, Treino.getTipoTreino());
            stmt.setInt(5, Treino.getDuracao_minutos());
            stmt.setDate(6, java.sql.Date.valueOf(Treino.getDataInicio()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Treino buscarPorId(int id) {
        String sql = "SELECT * FROM Treino WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Treino(rs.getInt("id"), rs.getInt("aluno_id"),
                        rs.getInt("id"), rs.getString("descricao"), rs.getString("tipo_treino"),
                        rs.getDate("data").toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void atualizar(Treino treino) {
        String sql = "UPDATE Treino SET aluno_id = ?, id = ?, descricao = ?, tipo_treino = ?, duracao_minutos = ?, data = ? WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, treino.getIdAluno());
            stmt.setInt(2, treino.getId());
            stmt.setString(3, treino.getDescricao());
            stmt.setString(4, treino.getTipoTreino());
            stmt.setInt(5, treino.getDuracao_minutos());
            stmt.setDate(6, java.sql.Date.valueOf(treino.getDataInicio()));
            stmt.setInt(7, treino.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Treino WHERE id = ?";
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Treino> listarTodos() {
        String sql = "SELECT * FROM Treino";
        List<Treino> treinos = new ArrayList<>();
        try (java.sql.PreparedStatement stmt = con.prepareStatement(sql)) {
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Treino treino = new Treino(
                        rs.getInt("id"),
                        rs.getInt("aluno_id"),
                        rs.getInt("duracao_minutos"),
                        rs.getString("descricao"),
                        rs.getString("tipo_treino"),
                        rs.getDate("data").toLocalDate());
                treinos.add(treino);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TreinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return treinos;
    }
}
