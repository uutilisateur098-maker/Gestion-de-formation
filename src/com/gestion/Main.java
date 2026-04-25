package com.gestion;

import com.gestion.dao.ApprenantDAO;
import com.gestion.dao.UtilisateurDAO;
import com.gestion.gui.LoginFrame;
import com.gestion.model.Apprenant;
import com.gestion.model.Utilisateur;
import com.gestion.util.ConnexionDB;
import javax.swing.SwingUtilities;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Test de la connexion ---");
        if (ConnexionDB.getConnection() != null) {
            System.out.println("Test de connexion réussi !");
        } else {
            System.out.println("Test de connexion échoué ! (Vérifiez MySQL)");
            return;
        }

        System.out.println("\n--- Test Authentification ---");
        UtilisateurDAO userDAO = new UtilisateurDAO();
        Utilisateur admin = userDAO.authenticate("admin", "admin123");
        if (admin != null) {
            System.out.println("Authentification réussie pour : " + admin.getUsername());
        } else {
            System.out.println("Authentification échouée (Assurez-vous d'avoir exécuté database.sql)");
        }

        System.out.println("\n--- Test CRUD Apprenant ---");
        ApprenantDAO appDAO = new ApprenantDAO();
        Apprenant testApp = new Apprenant(0, "Doe", "John", new Date(), "john.doe@email.com", null);
        appDAO.addApprenant(testApp);

        List<Apprenant> list = appDAO.getAllApprenants();
        System.out.println("Nombre d'apprenants : " + list.size());
        for (Apprenant a : list) {
            System.out.println("- " + a.getPrenom() + " " + a.getNom());
        }

        System.out.println("\n--- Fin des tests ---");

        // Lancer l'interface graphique
        System.out.println("\n--- Lancement de l'interface graphique ---");
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
