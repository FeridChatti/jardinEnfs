package Entities;

public class StatModel {


    private String nom;
    private int nbPart;

    public StatModel(String nom, int nbPart) {
        this.nom = nom;
        this.nbPart = nbPart;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbPart() {
        return nbPart;
    }

    public void setNbPart(int nbPart) {
        this.nbPart = nbPart;
    }


}
