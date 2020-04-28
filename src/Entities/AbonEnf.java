/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 * @author FERID
 */
public class AbonEnf {

    private String nom;
    private String prenom;
    private String etat;
    private Date datenaiss;
    private String type;
    private int montant;
    private Date date;
    private int id;
    private String jardin;
    private String adresse;
    private String sexe;
    private String numtel;

    public AbonEnf(String nom, String prenom, String etat, Date datenaiss, String type, int montant, Date date, int id, String jardin, String adresse, String sexe, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.datenaiss = datenaiss;
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.id = id;
        this.jardin = jardin;
        this.adresse = adresse;
        this.sexe = sexe;
        this.numtel = numtel;
    }


    public AbonEnf() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJardin() {
        return jardin;
    }

    public String getParent() {
        return adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

    public void setParent(String adresse) {
        this.adresse = adresse;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return prenom;
    }
}
