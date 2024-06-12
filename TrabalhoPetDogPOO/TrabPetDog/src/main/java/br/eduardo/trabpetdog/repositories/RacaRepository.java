
package br.eduardo.trabpetdog.repositories;

import br.eduardo.trabpetdog.domain.Raca;
import br.eduardo.trabpetdog.infraestructure.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class RacaRepository {
    private static final String INSERT = "INSERT INTO raca (ds_raca)"
            + " VALUES (?)";
    private static final String UPDATE = "UPDATE raca SET ds_raca=? WHERE id=?";
    private static final String DELETE = "DELETE FROM raca WHERE id=?";
    private static final String FIND_BY_ID = "SELECT id, ds_raca FROM raca "
            + "WHERE id=?";
    private static final String FIND_ALL = "SELECT id, ds_raca FROM raca";

    public Raca insert(Raca raca) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, raca.getDescricao());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    raca.setId(rs.getInt(1));
                }
            }
        }
        return raca;
    }

    public Raca update(Raca raca) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
            pstmt.setString(1, raca.getDescricao());
            pstmt.setInt(2, raca.getId());
            pstmt.executeUpdate();
        }
        return raca;
    }

    public Raca findById(int id) throws SQLException {
        Raca raca = null;
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    raca = new Raca();
                    raca.setId(rs.getInt("id"));
                    raca.setDescricao(rs.getString("ds_raca"));
                }
            }
        }
        return raca;
    }

    public ArrayList<Raca> findAll() throws SQLException {
        ArrayList<Raca> racas = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL); 
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Raca raca = new Raca();
                raca.setId(rs.getInt("id"));
                raca.setDescricao(rs.getString("ds_raca"));
                racas.add(raca);
            }
        }
        return racas;
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(DELETE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}