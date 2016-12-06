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
        if(e.getSource().getClass()==JCheckBox.class){
            JCheckBox source = (JCheckBox) e.getSource();
            if (source.isSelected()){
                afficherPoints(source.getText());
            }
            else {
                supprimerPoints(source.getText());
            }
        }
        else {
            JButton jb=(JButton)e.getSource();
            String[] toSearch=jb.getName().split(";");
            for (PointInteret pt : model.getEdifices().get(toSearch[1])) {
                if(pt.getINSEE().equals(toSearch[0])){
                    System.out.println(pt.getDescription());
                    //TODO Recuperer informations et les afficher dans le panel
                }
            }
        }

    }

    private void supprimerPoints(String text) {
        for(PointInteret pt:model.getEdifices().get(text)){
            Point p=CoordinatesTransform(pt.getLattitude(),pt.getLongitude());
            view.getCarte().remove(view.getCarte().getComponentAt(p));


        }
        view.repaint();

    }

    private void afficherPoints(String text) {
        for(PointInteret pt:model.getEdifices().get(text)){
            Point p=CoordinatesTransform(pt.getLattitude(),pt.getLongitude());
            JButton tmp=new JButton(new ImageIcon(model.getPictoImages(text)));
            tmp.setLayout(null);

            tmp.setVisible(true);
            tmp.setOpaque(true);
            tmp.setBorder(null);
            tmp.setContentAreaFilled(false);

            tmp.setBounds(p.x,p.y,tmp.getIcon().getIconWidth(),tmp.getIcon().getIconHeight());

            tmp.setPreferredSize(new Dimension(tmp.getIcon().getIconWidth(),tmp.getIcon().getIconHeight()));
            tmp.addActionListener(this);
            tmp.setName(pt.getINSEE()+';'+text);
            view.getCarte().add(tmp);
            view.repaint();
        }

    }

    public Point CoordinatesTransform(double latitude,double longitude){
        double tt=((100*(longitude-5.25))/(7.23-5.25));
        int depXonScreen= (int) ((view.getImageFR().getWidth()*((100*(longitude-5.25))/(7.23-5.25)))/100);//((view.getImageFR().getWidth()*(7.23-longitude))/(7.23-5.25));
        int depYonScreen=(int) ((view.getImageFR().getHeight()*((100*(latitude-46.23))/(48.183-46.23)))/100);//((latitude-46.23)/((48.183-46.23)/view.getImageFR().getHeight()));//((view.getImageFR().getHeight()*(48.183-latitude))/(48.183-46.23));
        return new Point(depXonScreen,depYonScreen);
    }

}
