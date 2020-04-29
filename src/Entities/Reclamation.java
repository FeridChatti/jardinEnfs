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
public class Reclamation {


    private int id;


    private String description;


    private Date date;


    private String titre;


    private String nom;

    private String numtel;

    private String mail;

    private String etat;


    private Parents parent;


    public Parents getParent() {
        return parent;
    }

    public void setParent(Parents parent) {
        this.parent = parent;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getTitre() {
        return titre;
    }


    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getNumtel() {
        return numtel;
    }


    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }


    public String getMail() {
        return mail;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getEtat() {
        return etat;
    }


    public void setEtat(String etat) {
        this.etat = etat;
    }


    public Reclamation(int id, String description, Date date,
                       String titre, String nom, String numtel, String mail, String etat) {
        super();
        this.id = id;
        this.description = description;
        this.date = date;
        this.titre = titre;
        this.nom = nom;
        this.numtel = numtel;
        this.mail = mail;
        this.etat = etat;


    }


    @Override
    public String toString() {
        return "Reclamation [id=" + id + ", description=" + description + ", date=" + date + ", titre=" + titre
                + ", nom=" + nom + ", numtel=" + numtel + ", mail=" + mail + ", etat=" + etat + "]";
    }


}
