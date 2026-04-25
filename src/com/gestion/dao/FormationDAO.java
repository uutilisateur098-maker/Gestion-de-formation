package com.gestion.dao;

import com.gestion.model.Formation;
import com.gestion.util.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormationDAO {

    public void addFormation(Formation formation) {
        String sql = "INSERT INTO formations (titre, description, date_debut, date_fin, formateur_id) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, formation.getTitre());
            pstmt.setString(2, formation.getDescription());
            pstmt.setDate(3, new java.sql.Date(formation.getDateDebut().getTime()));
            pstmt.setDate(4, new java.sql.Date(formation.getDateFin().getTime()));
            if (formation.getFormateurId() > 0) {
                pstmt.setInt(5, formation.getFormateurId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Formation> getAllFormations() {
        List<Formation> formations = new ArrayList<>();
        String sql = "SELECT * FROM formations";
        Connection conn = ConnexionDB.getConnection();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Formation f = new Formation();
                f.setId(rs.getInt("id"));
                f.setTitre(rs.getString("titre"));
                f.setDescription(rs.getString("description"));
                f.setDateDebut(rs.getDate("date_debut"));
                f.setDateFin(rs.getDate("date_fin"));
                f.setFormateurId(rs.getInt("formateur_id"));
                formations.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formations;
    }

    // Additional CRUD methods (update, delete) could be added here
}
