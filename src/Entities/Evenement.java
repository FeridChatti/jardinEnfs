package Entities;

import java.util.Collection;

public class Evenement {


    private int id;


    private String titre;


    private String date;


    private String description;


    private String image;


    private Categorie categorie;


    private Jardin jardin;

    private Collection<Participer> participation;

    public Evenement(int id, String titre, String date, String description, String image, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }

    public Evenement(String titre, String date, String description, String image) {
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.image = image;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }

    public Collection<Participer> getParticipation() {
        return participation;
    }

    public void setParticipation(Collection<Participer> participation) {
        this.participation = participation;
    }

    @Override
    public String toString() {
        return titre;
    }


}
