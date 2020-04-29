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
public class Chauffeur extends User {


    private int id;


    private String cin;


    private String nom;


    private String tel;


    private String sexe;


    private Collection<Pointage> $pointage;


    private Collection<Trajet> trajet;

    private int jardin_id;

    private Jardin jardin;

    public Chauffeur() {
    }

    public Chauffeur(int id, String cin, String nom, String tel, String sexe, int jardin) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.tel = tel;
        this.sexe = sexe;
        this.jardin_id = jardin;
    }

    public int getJardin_id() {
        return jardin_id;
    }

    public void setJardin_id(int jardin_id) {
        this.jardin_id = jardin_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Collection<Pointage> get$pointage() {
        return $pointage;
    }

    public void set$pointage(Collection<Pointage> $pointage) {
        this.$pointage = $pointage;
    }

    public Collection<Trajet> getTrajet() {
        return trajet;
    }

    public void setTrajet(Collection<Trajet> trajet) {
        this.trajet = trajet;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

}
