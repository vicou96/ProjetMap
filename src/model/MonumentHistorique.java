package model;
/**
 * Created by Roland on 30-Nov-16.
 */
public class MonumentHistorique extends PointInteret
{
    private String reference;
    private String date;
    private String protectionAuteur;
    private String siecle;
    private String categorie;

    public MonumentHistorique(String[] data){

        this.Lattitude= Double.parseDouble(data[0]);
        this.Longitude= Double.parseDouble(data[1]);
        this.reference= data[2];
        this.departement= Integer.parseInt(data[3]);
        this.commune=data[4];
        this.INSEE=data[5];
        this.categorie=data[6];
        this.designation=data[7];
        this.proprietaire=data[8];
        this.description=data[9];
        this.date=data[10];
        this.protectionAuteur=data[11];
        this.siecle=data[12];
    }
    public String getProprietaire() {
        return proprietaire;
    }

    public String getReference() {
        return reference;
    }

    public String getDate() {
        return date;
    }

    public String getProtectionAuteur() {
        return protectionAuteur;
    }

    public String getSiecle() {
        return siecle;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDesignation() {
        return designation;
    }

    private String designation;
    private String proprietaire;

}
