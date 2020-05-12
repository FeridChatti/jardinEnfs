/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 * @author karim
 */
public class Club {

    private int id;


    private String name;


    private String description;


    private String photo;


    private int rank;
    public Club() {
    }

    public Club(int id, String name, String description, String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public Club(int id, String name, String description, String photo, int rank) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", name=" + name + ", description=" + description + ", photo=" + photo + '}';
    }


}
