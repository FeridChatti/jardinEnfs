/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Collection;

/**
 * @author karim
 */
public class Enfant {


    private int id;


    private String nom;


    private String prenom;


    private Date datenaiss;


    private String sexe;


    private Collection<Abonnement> $abonnements;


    private Parents parent;


    private Collection<Participer> participation;


    private Collection<PartActivite> participerActivite;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public String getSexe() {
        return sexe;
    }

    public Collection<Abonnement> get$abonnements() {
        return $abonnements;
    }

    public Parents getParent() {
        return parent;
    }

    public Collection<Participer> getParticipation() {
        return participation;
    }

    public Collection<PartActivite> getParticiperActivite() {
        return participerActivite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void set$abonnements(Collection<Abonnement> $abonnements) {
        this.$abonnements = $abonnements;
    }

    public void setParent(Parents parent) {
        this.parent = parent;
    }

    public void setParticipation(Collection<Participer> participation) {
        this.participation = participation;
    }

    public void setParticiperActivite(Collection<PartActivite> participerActivite) {
        this.participerActivite = participerActivite;
    }


    public Enfant(int id, String nom, String prenom, Date datenaiss, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaiss = datenaiss;
        this.sexe = sexe;
    }

    public Enfant() {

    }

    @Override
    public String toString() {
        return prenom;
    }
}
