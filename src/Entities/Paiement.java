/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author karim
 */
public class Paiement extends ArrayList<Paiement> {

    private int id;

    private String date;


    private float montant;


    private int jardin;

    private String jard;


    public String getJard() {
        return jard;
    }

    public void setJard(String jard) {
        this.jard = jard;
    }

    public Paiement() {
    }

    public Paiement(String date, float montant, int jardin) {

        this.date = date;
        this.montant = montant;
        this.jardin = jardin;
    }
    public Paiement(int id, float montant, String date) {

        this.date = date;
        this.montant = montant;
        this.id = id;
    }


    public Paiement(String date, float montant, String jard) {

        this.date = date;
        this.montant = montant;
        this.jard = jard;
    }

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

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getJardin() {
        return jardin;
    }

    public void setJardin(int jardin) {
        this.jardin = jardin;
    }
}
