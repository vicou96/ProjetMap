package controller;

import model.MainModel;
import model.MonumentHistorique;
import model.Musee;
import model.PointInteret;
import view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Victor on 30/11/2016.
 */
public class checkBoxController implements ActionListener
{

    private MainModel model;
    private MainView view;
    private String lastinfo;

    public checkBoxController(MainModel model, MainView view){
        this.lastinfo ="";
        this.model = model;
        this.view = view;
        view.initAttribut(model.getEdifices().keySet());
        initListenerCheckBoxes();
        view.getCarte().setLayout(null);
        initVilles();
        view.getInfo().setText("Informations");
    }
    public void initVilles(){

        view.getMyList().setListData(model.getVillesArray());
    }
    public void initListenerCheckBoxes(){
        for (JCheckBox check :view.getCheckBoxes()) {
            view.setActionListener(this,check);
        }
        view.getPanButton().add(view.getBtnDeselect());
        view.getPanButton().add(view.getBtnSelectAll());
        view.getBtnDeselect().addActionListener(this);
        view.getBtnSelectAll().addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().getClass()==JCheckBox.class){
            JCheckBox source = (JCheckBox) e.getSource();
            if (source.isSelected()){
                getPointToDisplay(source.getText());
            }
            else {
                supprimerPoints(source.getText());
            }
        }
        else {
            JButton jb=(JButton)e.getSource();
            if (jb.getText().equals("Tout déselectionner")){
                for (JCheckBox j : view.getCheckBoxes()) {
                    j.setSelected(false);
                }
                for(JButton pt:view.getButtons()){
                    view.getCarte().remove(pt);
                }
                view.getButtons().removeAll(view.getButtons());
                view.getInfo().setText("Informations");
                view.repaint();
            }
            else if (jb.getText().equals("Sélectionner tout")){
                for (JCheckBox j : view.getCheckBoxes()) {
                    if (!j.isSelected()){
                        j.setSelected(true);
                        getPointToDisplay(j.getText());
                    }
                }
            }
            else {
                displayInfo(jb);
            }
        }
    }

    private void supprimerPoints(String text) {
        if (lastinfo.equals(text)){
            view.getInfo().setText("Informations");
        }
        ArrayList<JButton> remove=new ArrayList<>();
        for(JButton pt:view.getButtons()){
            if (pt.getName().split(";")[1].equals(text)){
                view.getCarte().remove(pt);
                remove.add(pt);
            }
        }
        view.getButtons().removeAll(remove);
        view.repaint();

    }
    private void supprimerToutPoints(){
        for(JButton pt:view.getButtons()){
            view.getCarte().remove(pt);
        }
        view.getButtons().removeAll(view.getButtons());
        view.repaint();
    }
    /*private void supprimerAutoPoints(){
        ArrayList<String> villeSelected=new ArrayList<>();
        ArrayList<PointInteret> toadd=new ArrayList<>();
        for (int i = 0; i < view.getMyList1().getModel().getSize(); i++) {
            villeSelected.add(view.getMyList1().getModel().getElementAt(i));
        }
        if (villeSelected.size()!=0){

        }
    }*/

   /* private void afficherPoints(String text) {
        for(PointInteret pt:model.getEdifices().get(text)){
            Point p=CoordinatesTransform(pt.getLattitude(),pt.getLongitude());
            JButton tmp=new JButton(new ImageIcon(model.getPictoImages(text)));

            tmp.setVisible(true);
            tmp.setOpaque(true);
            tmp.setBorder(null);
            tmp.setContentAreaFilled(false);

            tmp.setBounds(p.x, p.y, tmp.getIcon().getIconWidth(), tmp.getIcon().getIconHeight());

            tmp.setPreferredSize(new Dimension(tmp.getIcon().getIconWidth(), tmp.getIcon().getIconHeight()));
            tmp.addActionListener(this);
            tmp.setName(pt.getINSEE() + ';' + text);
            view.getButtons().add(tmp);
            view.getCarte().add(tmp);
        }

        view.repaint();
    }*/
    private void getPointToDisplay(String text){
        ArrayList<String> villeSelected=new ArrayList<>();
        ArrayList<PointInteret> toadd=new ArrayList<>();
        for (int i = 0; i < view.getMyList1().getModel().getSize(); i++) {
            villeSelected.add(view.getMyList1().getModel().getElementAt(i));
        }
            for(PointInteret pt:model.getEdifices().get(text)){
                if (villeSelected.size()==0||villeSelected.contains(pt.getCommune())) {
                    Point p = CoordinatesTransform(pt.getLattitude(), pt.getLongitude());
                    JButton tmp = new JButton(new ImageIcon(model.getPictoImages(text)));

                    tmp.setVisible(true);
                    tmp.setOpaque(true);
                    tmp.setBorder(null);
                    tmp.setContentAreaFilled(false);

                    tmp.setBounds(p.x, p.y, tmp.getIcon().getIconWidth(), tmp.getIcon().getIconHeight());

                    tmp.setPreferredSize(new Dimension(tmp.getIcon().getIconWidth(), tmp.getIcon().getIconHeight()));
                    tmp.addActionListener(this);
                    tmp.setName(pt.getINSEE() + ';' + text);
                    view.getButtons().add(tmp);
                    view.getCarte().add(tmp);
                }
            }
            view.repaint();
    }

    public void displayInfo(JButton jb){
        String[] toSearch=jb.getName().split(";");
        lastinfo=toSearch[1];
        for (PointInteret pt : model.getEdifices().get(toSearch[1])) {
            if(pt.getINSEE().equals(toSearch[0])){
                //TODO Recuperer informations et les afficher dans le panel
                if (toSearch[1].equals("musee")){
                    Musee tmpmusee= (Musee) pt;
                    String info= "<html>Informations<br>"+tmpmusee.getDesignation()+", Code Insee: "+tmpmusee.getINSEE()+" <br>Latitude: " + tmpmusee.getLattitude()+" Longitude: "+tmpmusee.getLongitude()+" Adresse: "+tmpmusee.getAdresse()+" "+tmpmusee.getCommune()+" "+tmpmusee.getCodepostal()+"<br>Accès: "+tmpmusee.getAcces()+
                            "<br>Courriel: "+tmpmusee.getCourriel()+"<br>Facebook: "+tmpmusee.getFacebook()+"<br>Site internet: "+tmpmusee.getSiteinternet()+"<br>Téléphone: "+tmpmusee.getTelephone()+"<br><br>"+tmpmusee.getDescription()+"</html>";
                    view.getInfo().setText(info);
                }
                else {
                    MonumentHistorique tmpMonument=(MonumentHistorique) pt;
                    String info= "<html>Informations<br>"+tmpMonument.getDesignation()+", Code Insee: "+tmpMonument.getINSEE()+" <br>Latitude: " + tmpMonument.getLattitude()+" Longitude: "+tmpMonument.getLongitude()+" Adresse: "+tmpMonument.getCommune()+" "+
                            tmpMonument.getDepartement()+"<br>Époque: "+tmpMonument.getSiecle()+" (Protégée le "+tmpMonument.getDateProtection()+") "+(tmpMonument.getAuteur().equals("")?"": "<br>Auteur(s): "+tmpMonument.getAuteur())+"<br>Propriétaire: "+tmpMonument.getProprietaire()+"<br>Référence: "+tmpMonument.getReference()+"<br><br>"+tmpMonument.getDescription()+"</html>";
                    view.getInfo().setText(info);
                }
            }
        }
    }

    public Point CoordinatesTransform(double latitude,double longitude){
        double tt=((100*(longitude-5.25))/(7.23-5.25));
        int depYonScreen= (int) (view.getImageFR().getHeight()-(((latitude-46.23)*(view.getImageFR().getHeight()))/(48.183-46.23)));
        int depXonScreen= (int)(((longitude-5.25)*(view.getImageFR().getWidth()))/(7.23-5.25));

        /*
        double tt=((100*(longitude-5.25))/(7.23-5.25));
        int depXonScreen= (int) ((view.getImageFR().getWidth()*((100*(longitude-5.25))/(7.23-5.25)))/100);//((view.getImageFR().getWidth()*(7.23-longitude))/(7.23-5.25));
        int depYonScreen=(int) ((view.getImageFR().getHeight()*((100*(latitude-46.23))/(48.183-46.23)))/100);//((latitude-46.23)/((48.183-46.23)/view.getImageFR().getHeight()));//((view.getImageFR().getHeight()*(48.183-latitude))/(48.183-46.23));
       */return new Point(depXonScreen,depYonScreen);
    }

}
