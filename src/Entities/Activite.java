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
public class Activite {

    private int id;


    private String typeact;


    private String detailles;


    private String photo;


    private String Date;


    private Date dateDebut;


    private Date dateFin;

    private Date dateCreation;

    private Club club;


    private Collection<PartActivite> participation;

    public Activite() {
    }

    public Activite(String typeact, String detailles, String Date) {
        this.typeact = typeact;
        this.detailles = detailles;
        this.Date = Date;
    }

    public Activite(int id, String typeact, String detailles, String photo, String Date, Date dateDebut, Date dateFin, Date dateCreation, Club club, Collection<PartActivite> participation) {
        this.id = id;
        this.typeact = typeact;
        this.detailles = detailles;
        this.photo = photo;
        this.Date = Date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateCreation = dateCreation;
        this.club = club;
        this.participation = participation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeact() {
        return typeact;
    }

    public void setTypeact(String typeact) {
        this.typeact = typeact;
    }

    public String getDetailles() {
        return detailles;
    }

    public void setDetailles(String detailles) {
        this.detailles = detailles;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Collection<PartActivite> getParticipation() {
        return participation;
    }

    public void setParticipation(Collection<PartActivite> participation) {
        this.participation = participation;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", typeact=" + typeact + ", detailles=" + detailles + ", photo=" + photo + ", Date=" + Date + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", dateCreation=" + dateCreation + ", club=" + club + ", participation=" + participation + '}';
    }


}
