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
public class Tuteur extends User {
    private int id;


    private String nom;

    private String prenom;

    private String sexe;


    private Jardin jardin;


    private Collection<Remarque> remarques;

    public Tuteur() {
    }

    public Tuteur(int id, String nom, String prenom, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public Tuteur(int id, String nom, String prenom, String sexe, String username, String email, String type, String role) {
        super(id, username, email, type, role);
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

    public Collection<Remarque> getRemarques() {
        return remarques;
    }

    public void setRemarques(Collection<Remarque> remarques) {
        this.remarques = remarques;
    }


}
