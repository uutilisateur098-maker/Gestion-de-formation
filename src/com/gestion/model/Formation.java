package com.gestion.model;

import java.util.Date;

public class Formation {
    private int id;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private int formateurId;

    public Formation() {
    }

    public Formation(int id, String titre, String description, Date dateDebut, Date dateFin, int formateurId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.formateurId = formateurId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getFormateurId() {
        return formateurId;
    }

    public void setFormateurId(int formateurId) {
        this.formateurId = formateurId;
    }
}
