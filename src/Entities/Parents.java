/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Collection;


/**
 * @author karim
 */
public class Parents extends User {
    private int id;


    private String nom;


    private String prenom;


    private String numtel;

    private String adresse;


    private String sexe;

    private int nbenfant;


    private Collection<Enfant> enfants;


    private Collection<Messages> messages;


    private Collection<Reclamation> reclamations;

    public Parents(int id, String nom, String prenom, String numtel,
                   String adresse, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.adresse = adresse;
        this.sexe = sexe;
    }

    public int getNbenfant() {
        return nbenfant;
    }

    public void setNbenfant(int nbenfant) {
        this.nbenfant = nbenfant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Collection<Enfant> getEnfants() {
        return enfants;
    }

    public void setEnfants(Collection<Enfant> enfants) {
        this.enfants = enfants;
    }

    public Collection<Messages> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Messages> messages) {
        this.messages = messages;
    }

    public Collection<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(Collection<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
}
