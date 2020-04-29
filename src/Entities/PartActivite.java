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
public class PartActivite {
    private int id;

    private String date;


    private Enfant enfant;
    private AbonEnf e;


    public AbonEnf getE() {
        return e;
    }

    public void setE(AbonEnf e) {
        this.e = e;
    }

    private Activite activite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public PartActivite(int id, String date, Enfant enfant, Activite activite) {
        this.id = id;
        this.date = date;
        this.enfant = enfant;
        this.activite = activite;
    }

    public PartActivite() {
    }
}
