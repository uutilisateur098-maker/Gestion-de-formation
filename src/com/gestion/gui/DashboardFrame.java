package com.gestion.gui;

import com.gestion.model.Utilisateur;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(Utilisateur user) {
        setTitle("Tableau de Bord - Gestion Formation");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu de navigation
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGestion = new JMenu("Gestion");
        JMenuItem itemApprenants = new JMenuItem("Apprenants");
        JMenuItem itemFormations = new JMenuItem("Formations");
        JMenuItem itemInscriptions = new JMenuItem("Inscriptions");

        menuGestion.add(itemApprenants);
        menuGestion.add(itemFormations);
        menuGestion.add(itemInscriptions);
        menuBar.add(menuGestion);

        JMenu menuUser = new JMenu("Utilisateur (" + user.getUsername() + ")");
        JMenuItem itemLogout = new JMenuItem("Déconnexion");
        menuUser.add(itemLogout);
        menuBar.add(menuUser);

        setJMenuBar(menuBar);

        // Contenu par défaut
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel("Bienvenue dans le système de gestion de formation pro."));
        add(welcomePanel, BorderLayout.CENTER);

        // Actions
        itemApprenants.addActionListener(e -> {
            updateView(new ApprenantPanel());
        });

        itemFormations.addActionListener(e -> {
            updateView(new FormationPanel());
        });

        itemInscriptions.addActionListener(e -> {
            updateView(new InscriptionPanel());
        });

        itemLogout.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void updateView(JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
