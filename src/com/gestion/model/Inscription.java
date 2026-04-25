package com.gestion.model;

import java.util.Date;

public class Inscription {
    private int id;
    private int apprenantId;
    private int formationId;
    private Date dateInscription;
    private String statut;

    public Inscription() {
    }

    public Inscription(int id, int apprenantId, int formationId, Date dateInscription, String statut) {
        this.id = id;
        this.apprenantId = apprenantId;
        this.formationId = formationId;
        this.dateInscription = dateInscription;
        this.statut = statut;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(int apprenantId) {
        this.apprenantId = apprenantId;
    }

    public int getFormationId() {
        return formationId;
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
