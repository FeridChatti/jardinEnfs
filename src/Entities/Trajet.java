/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * @author karim
 */
public class Trajet {

    private int id;


    private String adresse;

    private String heure;

    private Chauffeur chauffeur;

    public Trajet(int id, String adresse, String heure) {
        this.id = id;
        this.adresse = adresse;
        this.heure = heure;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", adresse=" + adresse + ", heure=" + heure + ", chauffeur=" + chauffeur + '}';
    }


}
