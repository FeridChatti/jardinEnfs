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
public class Remarque {
    private int id;


    private String description;


    private Date date;


    // private Abonnement abonnement;


    // private Tuteur tuteur;

    private String enfant;
    private String nomtut;


    public String getNomtut() {
        return nomtut;
    }

    public void setNomtut(String nomtut) {
        this.nomtut = nomtut;
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

  /*  public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
*/
   /* public Tuteur getTuteur() {
        return tuteur;
    }

    public void setTuteur(Tuteur tuteur) {
        this.tuteur = tuteur;
    }*/

    public String getEnfant() {
        return enfant;
    }

    public void setEnfant(String enfant) {
        this.enfant = enfant;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Remarque(int id, String description, Date date, String tuteur, String enfant) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.nomtut = tuteur;
        this.enfant = enfant;
    }

}
