/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * @author Sami
 */
public class MapModel {
    private double lan;
    private double lat;
    private String nom;
    private String heure;

    public MapModel(double lan, double lat, String nom, String heure) {
        this.lan = lan;
        this.lat = lat;
        this.nom = nom;
        this.heure = heure;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }


}
