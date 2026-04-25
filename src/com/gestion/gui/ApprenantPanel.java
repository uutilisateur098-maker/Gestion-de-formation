package com.gestion.gui;

import com.gestion.dao.ApprenantDAO;
import com.gestion.model.Apprenant;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class ApprenantPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNom, txtPrenom, txtEmail;
    private JLabel lblPhoto;
    private byte[] photoBytes = null;
    private ApprenantDAO dao = new ApprenantDAO();

    public ApprenantPanel() {
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[] { "ID", "Nom", "Prenom", "Email" }, 0);
        table = new JTable(tableModel);
        loadData();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form - wrapped in a container to control size
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestion Apprenant"));
        formPanel.setPreferredSize(new Dimension(300, 280));
        formPanel.setMaximumSize(new Dimension(300, 280));

        formPanel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        formPanel.add(txtNom);

        formPanel.add(new JLabel("Prenom:"));
        txtPrenom = new JTextField();
        formPanel.add(txtPrenom);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Photo:"));
        lblPhoto = new JLabel("Aucune photo", JLabel.CENTER);
        lblPhoto.setPreferredSize(new Dimension(100, 100));
        lblPhoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        formPanel.add(lblPhoto);

        JButton btnParcourir = new JButton("Parcourir...");
        formPanel.add(btnParcourir);

        JButton btnAjouter = new JButton("Ajouter");
        formPanel.add(btnAjouter);

        rightPanel.add(formPanel);
        rightPanel.add(Box.createVerticalGlue()); // Push form to top

        add(rightPanel, BorderLayout.EAST);

        // Logic
        btnParcourir.addActionListener(e -> choosePhoto());
        btnAjouter.addActionListener(e -> addApprenant());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                // Potential detail selection logic
            }
        });
    }

    private void choosePhoto() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                photoBytes = Files.readAllBytes(file.toPath());
                ImageIcon icon = new ImageIcon(photoBytes);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                lblPhoto.setIcon(new ImageIcon(img));
                lblPhoto.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void addApprenant() {
        Apprenant a = new Apprenant();
        a.setNom(txtNom.getText());
        a.setPrenom(txtPrenom.getText());
        a.setEmail(txtEmail.getText());
        a.setDateNaissance(new Date()); // Simplified
        a.setPhoto(photoBytes);

        dao.addApprenant(a);
        loadData();
        clearForm();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Apprenant> list = dao.getAllApprenants();
        for (Apprenant a : list) {
            tableModel.addRow(new Object[] { a.getId(), a.getNom(), a.getPrenom(), a.getEmail() });
        }
    }

    private void clearForm() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        lblPhoto.setIcon(null);
        lblPhoto.setText("Aucune photo");
        photoBytes = null;
    }
}
