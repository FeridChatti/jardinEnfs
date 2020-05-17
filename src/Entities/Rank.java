package Entities;

public class Rank {

    private int id;


    private int id_parent;


    private int id_club;


    private int rank;

    private String comment;


    public Rank() {
    }

    public Rank(int id, int id_parent, int id_club, int rank, String comment) {
        this.id = id;
        this.id_parent = id_parent;
        this.id_club = id_club;
        this.rank = rank;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
