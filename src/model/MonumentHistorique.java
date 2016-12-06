package model;
/**
 * Created by Roland on 30-Nov-16.
 */
public class MonumentHistorique extends PointInteret
{
    private String reference;
    private String dateProtection;
    private String Auteur;
    private String siecle;
    private String categorie;
    private String designation;
    private String proprietaire;
    private String departement;

    public MonumentHistorique(String[] data){

        this.Lattitude= Double.parseDouble(data[0]);
        this.Longitude= Double.parseDouble(data[1]);
        this.reference= data[2];
        this.departement= data[3];
        this.commune=data[4];
        this.INSEE=data[5];
        this.categorie=data[6];
        this.designation=data[7];
        this.proprietaire=data[8];
        this.description=data[9];
        this.dateProtection =data[10];
        this.Auteur =data[11];
        this.siecle=data[12];
    }
    public String getProprietaire() {
        return proprietaire;
    }

    public String getReference() {
        return reference;
    }

    public String getDateProtection() {
        return dateProtection;
    }

    public String getAuteur() {
        return Auteur;
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
    public String getDepartement() {
        return departement;
    }


}
