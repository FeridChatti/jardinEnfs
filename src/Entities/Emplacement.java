package Entities;

public class Emplacement {

    private double latitude;
    private double longitude;
    private Trajet trajet;

    public Emplacement() {
    }

    public Emplacement(double latitude, double longitude, Trajet trajet) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.trajet = trajet;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}
