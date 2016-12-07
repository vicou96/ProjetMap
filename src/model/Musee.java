package model;

/**
 * Created by Roland on 30-Nov-16.
 */
public class Musee extends PointInteret {
    private String nom;
    private String adresse;
    private String acces;
    private String telephone;
    private String courriel;
    private String siteinternet;
    private String facebook;
    private int codepostal;

    public Musee(String[] data) {
        this.designation = data[0];
        this.adresse = data[1];
        this.codepostal= Integer.parseInt(data[2]);
        this.commune = data[3];
        this.INSEE = data[4];
        this.acces = data[5];
        this.Lattitude = Double.parseDouble(data[6]);
        this.Longitude = Double.parseDouble(data[7]);
        this.telephone = data[8];
        this.courriel = data[9];
        this.siteinternet = data[10];
        this.facebook = data[11];
        this.description = data[12];
    }
    public int getCodepostal() {
        return codepostal;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getAcces() {
        return acces;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getSiteinternet() {
        return siteinternet;
    }

    public String getFacebook() {
        return facebook;
    }
}
