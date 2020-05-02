/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 * @author karim
 */
public class Abonnement {


    private int id;


    private String date;


    private String type;


    private String etat;


    private String montant;


    private Jardin jardin;


    private Remarque remarques;


    private Enfant enfant;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getEtat() {
        return etat;
    }

    public String getMontant() {
        return montant;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public Remarque getRemarques() {
        return remarques;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

    public void setRemarques(Remarque remarques) {
        this.remarques = remarques;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public Abonnement() {
    }

    public Abonnement(int id, String date, String type, String etat, String montant, Jardin jardin, Remarque remarques, Enfant enfant) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.etat = etat;
        this.montant = montant;
        this.jardin = jardin;
        this.remarques = remarques;
        this.enfant = enfant;
    }

    public Abonnement(String date, String type, String etat, String montant, Enfant enfant) {
        this.date = date;
        this.type = type;
        this.etat = etat;
        this.montant = montant;
        this.enfant = enfant;
    }
}
