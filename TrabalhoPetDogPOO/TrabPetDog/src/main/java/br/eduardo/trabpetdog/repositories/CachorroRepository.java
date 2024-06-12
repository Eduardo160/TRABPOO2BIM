
package br.eduardo.trabpetdog.repositories;

import br.eduardo.trabpetdog.domain.Cachorro;
import br.eduardo.trabpetdog.infraestructure.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;

public class CachorroRepository {
    private static final String INSERT = "INSERT INTO cachorro (nome, id_raca,"
            + " id_pelagem, id_cor, vl_tamanho, st_perfume, dt_nascimento) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cachorro SET nome=?, "
            + "id_raca=?," + " id_pelagem=?, id_cor=?, vl_tamanho=?,"
            + " st_perfume=?,"+ " dt_nascimento=? WHERE id=?";
    private static final String DELETE = "DELETE FROM cachorro WHERE id=?";
    private static final String FIND_BY_ID = "SELECT id, nome, id_raca,"
            + "id_pelagem, id_cor, vl_tamanho, st_perfume,"
            + " dt_nascimento FROM cachorro WHERE id=?";
    private static final String FIND_ALL = "SELECT id, nome, id_raca,"
            + " id_pelagem, id_cor, vl_tamanho, st_perfume,"
            + " dt_nascimento FROM cachorro";

    public Cachorro insert(Cachorro cachorro) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cachorro.getNome());
            pstmt.setInt(2, cachorro.getRaca().getId());
            pstmt.setInt(3, cachorro.getPelagem().getId());
            pstmt.setInt(4, cachorro.getCor().getId());
            pstmt.setDouble(5, cachorro.getTamanho());
            pstmt.setBoolean(6, cachorro.isStPerfume());
            pstmt.setDate(7, new java.sql.Date
        (cachorro.getDtNascimento().getTime()));
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cachorro.setId(rs.getInt(1));
                }
            }
        }
        return cachorro;
    }

    public Cachorro update(Cachorro cachorro) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {
            pstmt.setString(1, cachorro.getNome());
            pstmt.setInt(2, cachorro.getRaca().getId());
            pstmt.setInt(3, cachorro.getPelagem().getId());
            pstmt.setInt(4, cachorro.getCor().getId());
            pstmt.setDouble(5, cachorro.getTamanho());
            pstmt.setBoolean(6, cachorro.isStPerfume());
            pstmt.setDate(7, new java.sql.Date(
                    cachorro.getDtNascimento().getTime()));
            pstmt.setInt(8, cachorro.getId());
            pstmt.executeUpdate();
        }
        return cachorro;
    }
    
    public Cachorro findById(int id) throws SQLException {
        Cachorro cachorro = null;
        try (Connection conn = new ConnectionFactory().getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cachorro = new Cachorro();
                    cachorro.setId(rs.getInt("id"));
                    cachorro.setNome(rs.getString("nome"));
                    cachorro.setRaca(new RacaRepository().findById(
                            rs.getInt("id_raca")));
                    cachorro.setPelagem(new PelagemRepository().findById(
                            rs.getInt("id_pelagem")));
                    cachorro.setCor(new CorRepository().findById(
                            rs.getInt("id_cor")));
                    cachorro.setTamanho(rs.getDouble(
                            "vl_tamanho"));
                    cachorro.setStPerfume(rs.getBoolean(
                            "st_perfume"));
                    cachorro.setDtNascimento(rs.getDate(
                            "dt_nascimento"));
                }
            }
        }
        return cachorro;
    }

    public ArrayList<Cachorro> findAll() throws SQLException {
        ArrayList<Cachorro> cachorros = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setRaca(new RacaRepository().findById(rs.getInt(
                        "id_raca")));
                cachorro.setPelagem(new PelagemRepository().findById(
                        rs.getInt("id_pelagem")));
                cachorro.setCor(new CorRepository().findById(rs.getInt(
                        "id_cor")));
                cachorro.setTamanho(rs.getDouble("vl_tamanho"));
                cachorro.setStPerfume(rs.getBoolean(
                        "st_perfume"));
                cachorro.setDtNascimento(rs.getDate(
                        "dt_nascimento"));
                cachorros.add(cachorro);
            }
        }
        return cachorros;
    }

    public void delete(int id) throws SQLException {
        try (Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(DELETE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
