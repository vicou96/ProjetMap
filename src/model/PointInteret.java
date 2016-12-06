package model;

/**
 * Created by Roland on 30-Nov-16.
 */
public class PointInteret
{
    protected double Lattitude;
    protected double Longitude;
    protected int departement;
    protected String commune;
    protected int codepostal;
    protected String description;
    protected String INSEE;
    public PointInteret(){}

    public double getLattitude() {
        return Lattitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public int getDepartement() {
        return departement;
    }

    public String getCommune() {
        return commune;
    }

    public int getCodepostal() {
        return codepostal;
    }

    public String getDescription() {
        return description;
    }

    public String getINSEE() {
        return INSEE;
    }

}
