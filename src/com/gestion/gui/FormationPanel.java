package com.gestion.gui;

import com.gestion.dao.FormationDAO;
import com.gestion.model.Formation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FormationPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTitre, txtDescription;
    private FormationDAO dao = new FormationDAO();

    public FormationPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[] { "ID", "Titre", "Description", "Debut", "Fin" }, 0);
        table = new JTable(tableModel);
        loadData();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form - wrapped in a container to control size
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestion Formation"));
        formPanel.setPreferredSize(new Dimension(300, 180));
        formPanel.setMaximumSize(new Dimension(300, 180));

        formPanel.add(new JLabel("Titre:"));
        txtTitre = new JTextField();
        formPanel.add(txtTitre);

        formPanel.add(new JLabel("Description:"));
        txtDescription = new JTextField();
        formPanel.add(txtDescription);

        JButton btnAjouter = new JButton("Ajouter");
        formPanel.add(btnAjouter);

        rightPanel.add(formPanel);
        rightPanel.add(Box.createVerticalGlue()); // Push form to top

        add(rightPanel, BorderLayout.EAST);

        btnAjouter.addActionListener(e -> {
            Formation f = new Formation();
            f.setTitre(txtTitre.getText());
            f.setDescription(txtDescription.getText());
            f.setDateDebut(new Date());
            f.setDateFin(new Date()); // Simple dates for demo
            dao.addFormation(f);
            loadData();
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Formation> list = dao.getAllFormations();
        for (Formation f : list) {
            tableModel.addRow(
                    new Object[] { f.getId(), f.getTitre(), f.getDescription(), f.getDateDebut(), f.getDateFin() });
        }
    }
}
