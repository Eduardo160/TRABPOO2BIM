
package br.eduardo.trabpetdog.repositories;

import br.eduardo.trabpetdog.domain.Pelagem;
import br.eduardo.trabpetdog.infraestructure.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class PelagemRepository {
    private static final String INSERT = "INSERT INTO pelagem (ds_pelagem) "
            + "VALUES (?)";
    private static final String UPDATE = "UPDATE pelagem SET ds_pelagem=? "
            + "WHERE id=?";
    private static final String DELETE = "DELETE FROM pelagem WHERE id=?";
    private static final String FIND_BY_ID = "SELECT id, ds_pelagem FROM "
            + "pelagem WHERE id=?";
    private static final String FIND_ALL = "SELECT id, ds_pelagem FROM pelagem";

    public Pelagem insert(Pelagem pelagem) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, pelagem.getDescricao());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pelagem.setId(rs.getInt(1));
                }
            }
        }
        return pelagem;
    }

    public Pelagem update(Pelagem pelagem) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
            pstmt.setString(1, pelagem.getDescricao());
            pstmt.setInt(2, pelagem.getId());
            pstmt.executeUpdate();
        }
        return pelagem;
    }

    public Pelagem findById(int id) throws SQLException {
        Pelagem pelagem = null;
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pelagem = new Pelagem();
                    pelagem.setId(rs.getInt("id"));
                    pelagem.setDescricao(rs.getString("ds_pelagem"));
                }
            }
        }
        return pelagem;
    }

    public ArrayList<Pelagem> findAll() throws SQLException {
        ArrayList<Pelagem> pelagens = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL); 
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Pelagem pelagem = new Pelagem();
                pelagem.setId(rs.getInt("id"));
                pelagem.setDescricao(rs.getString("ds_pelagem"));
                pelagens.add(pelagem);
            }
        }
        return pelagens;
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(DELETE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
