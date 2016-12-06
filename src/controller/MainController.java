package controller;

import model.MainModel;
import model.PointInteret;
import view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainController implements ActionListener
{

    private MainModel model;
    private MainView view;

    public MainController(MainModel model, MainView view){
        this.model = model;
        this.view = view;
        view.initAttribut(model.getEdifices().keySet());
        initListenerCheckBoxes();

    }
    public void initListenerCheckBoxes(){
        for (JCheckBox check :view.getCheckBoxes()) {
            view.setActionListener(this,check);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JCheckBox source = (JCheckBox) e.getSource();
        if (source.isSelected()){
            afficherPoints(source.getText());
        }
        else {
            supprimerPoints(source.getText());
        }

    }

    private void supprimerPoints(String text) {
        for(PointInteret pt:model.getEdifices().get(text)){
            Point p=CoordinatesTransform(pt.getLattitude(),pt.getLongitude());
            view.getCarte().remove(view.getCarte().getComponentAt(p));

        }
        view.getCarte().repaint();
       /*
        for(JCheckBox check:view.getCheckBoxes()){

        }*/
    }

    private void afficherPoints(String text) {
        for(PointInteret pt:model.getEdifices().get(text)){
            Point p=CoordinatesTransform(pt.getLattitude(),pt.getLongitude());
            //view.getGraphics().drawLine(p.x,p.y,p.x,p.y);
            view.getCarte().getGraphics().drawImage(model.getPictoImages(text),(int)p.getX(),(int)p.getY(),model.getPictoImages(text).getWidth(),model.getPictoImages(text).getHeight(),view.getCarte());

        }
    }

    public Point CoordinatesTransform(double latitude,double longitude){
        double tt=((100*(longitude-5.25))/(7.23-5.25));
        //((view.getImageFR().getWidth()*((100*(longitude-5.25))/(7.23-5.25)))/100);
        int depXonScreen= (int) ((view.getImageFR().getWidth()*((100*(longitude-5.25))/(7.23-5.25)))/100);//((view.getImageFR().getWidth()*(7.23-longitude))/(7.23-5.25));
        int depYonScreen=(int) ((view.getImageFR().getHeight()*((100*(latitude-46.23))/(48.183-46.23)))/100);//((latitude-46.23)/((48.183-46.23)/view.getImageFR().getHeight()));//((view.getImageFR().getHeight()*(48.183-latitude))/(48.183-46.23));
        return new Point(depXonScreen,depYonScreen);
    }
}
