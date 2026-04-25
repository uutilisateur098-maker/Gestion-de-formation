package com.gestion.dao;

import com.gestion.model.Apprenant;
import com.gestion.util.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApprenantDAO {

    public void addApprenant(Apprenant apprenant) {
        String sql = "INSERT INTO apprenants (nom, prenom, date_naissance, email, photo) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, apprenant.getNom());
            pstmt.setString(2, apprenant.getPrenom());
            pstmt.setDate(3, new java.sql.Date(apprenant.getDateNaissance().getTime()));
            pstmt.setString(4, apprenant.getEmail());
            pstmt.setBytes(5, apprenant.getPhoto());

            pstmt.executeUpdate();
            System.out.println("Apprenant ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Apprenant> getAllApprenants() {
        List<Apprenant> apprenants = new ArrayList<>();
        String sql = "SELECT * FROM apprenants";
        Connection conn = ConnexionDB.getConnection();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Apprenant a = new Apprenant();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setDateNaissance(rs.getDate("date_naissance"));
                a.setEmail(rs.getString("email"));
                a.setPhoto(rs.getBytes("photo"));
                apprenants.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apprenants;
    }

    public void updateApprenant(Apprenant apprenant) {
        String sql = "UPDATE apprenants SET nom=?, prenom=?, date_naissance=?, email=?, photo=? WHERE id=?";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, apprenant.getNom());
            pstmt.setString(2, apprenant.getPrenom());
            pstmt.setDate(3, new java.sql.Date(apprenant.getDateNaissance().getTime()));
            pstmt.setString(4, apprenant.getEmail());
            pstmt.setBytes(5, apprenant.getPhoto());
            pstmt.setInt(6, apprenant.getId());

            pstmt.executeUpdate();
            System.out.println("Apprenant mis à jour !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteApprenant(int id) {
        String sql = "DELETE FROM apprenants WHERE id=?";
        Connection conn = ConnexionDB.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Apprenant supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
