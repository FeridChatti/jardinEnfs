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
public class AdmEnf {

    private int id;
    private String nom;
    private String prenom;
    private Date date;
    private String Sexe;
    private String parent;
    private String jardin;

    public AdmEnf() {
    }

    public AdmEnf(int id, String nom, String prenom, Date date, String Sexe, String parent, String jardin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.Sexe = Sexe;
        this.parent = parent;
        this.jardin = jardin;
    }


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate() {
        return date;
    }

    public String getSexe() {
        return Sexe;
    }

    public String getParent() {
        return parent;
    }

    public String getJardin() {
        return jardin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
