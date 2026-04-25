package com.gestion.dao;

import com.gestion.model.Inscription;
import com.gestion.util.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDAO {

    public void addInscription(Inscription ins) {
        String sql = "INSERT INTO inscriptions (apprenant_id, formation_id, statut) VALUES (?, ?, ?)";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ins.getApprenantId());
            pstmt.setInt(2, ins.getFormationId());
            pstmt.setString(3, ins.getStatut());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inscription> getInscriptionsByFormation(int formationId) {
        List<Inscription> list = new ArrayList<>();
        String sql = "SELECT * FROM inscriptions WHERE formation_id = ?";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, formationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Inscription ins = new Inscription();
                    ins.setId(rs.getInt("id"));
                    ins.setApprenantId(rs.getInt("apprenant_id"));
                    ins.setFormationId(rs.getInt("formation_id"));
                    ins.setDateInscription(rs.getTimestamp("date_inscription"));
                    ins.setStatut(rs.getString("statut"));
                    list.add(ins);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
