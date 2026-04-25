package com.gestion.gui;

import com.gestion.dao.ApprenantDAO;
import com.gestion.dao.FormationDAO;
import com.gestion.dao.InscriptionDAO;
import com.gestion.model.Apprenant;
import com.gestion.model.Formation;
import com.gestion.model.Inscription;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InscriptionPanel extends JPanel {
    private JComboBox<Apprenant> comboApprenants;
    private JComboBox<Formation> comboFormations;
    private JComboBox<String> comboStatut;
    private InscriptionDAO insDAO = new InscriptionDAO();

    public InscriptionPanel() {
        setLayout(new BorderLayout());

        // Create a centered wrapper panel
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Inscription Apprenant"));
        formPanel.setPreferredSize(new Dimension(400, 220));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Apprenant:"), gbc);
        comboApprenants = new JComboBox<>();
        loadApprenants();
        gbc.gridx = 1;
        formPanel.add(comboApprenants, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Formation:"), gbc);
        comboFormations = new JComboBox<>();
        loadFormations();
        gbc.gridx = 1;
        formPanel.add(comboFormations, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Statut:"), gbc);
        comboStatut = new JComboBox<>(new String[] { "EN_COURS", "TERMINE", "ANNULE" });
        gbc.gridx = 1;
        formPanel.add(comboStatut, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnInscrire = new JButton("Inscrire l'apprenant");
        formPanel.add(btnInscrire, gbc);

        centerWrapper.add(formPanel);
        add(centerWrapper, BorderLayout.NORTH);

        btnInscrire.addActionListener(e -> {
            Apprenant a = (Apprenant) comboApprenants.getSelectedItem();
            Formation f = (Formation) comboFormations.getSelectedItem();
            if (a != null && f != null) {
                Inscription ins = new Inscription();
                ins.setApprenantId(a.getId());
                ins.setFormationId(f.getId());
                ins.setStatut((String) comboStatut.getSelectedItem());
                insDAO.addInscription(ins);
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
            }
        });
    }

    private void loadApprenants() {
        List<Apprenant> list = new ApprenantDAO().getAllApprenants();
        for (Apprenant a : list) {
            comboApprenants.addItem(a);
        }
        // Custom renderer to show name
        comboApprenants.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Apprenant) {
                    Apprenant a = (Apprenant) value;
                    setText(a.getPrenom() + " " + a.getNom());
                }
                return this;
            }
        });
    }

    private void loadFormations() {
        List<Formation> list = new FormationDAO().getAllFormations();
        for (Formation f : list) {
            comboFormations.addItem(f);
        }
        comboFormations.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Formation) {
                    Formation f = (Formation) value;
                    setText(f.getTitre());
                }
                return this;
            }
        });
    }
}
