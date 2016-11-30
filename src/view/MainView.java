package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainView extends JFrame{
    private JPanel panPrincipal,carte,panCheckBox,panFond,panInformations;
    private JCheckBox checkBox,checkBox1;
    private JCheckBox[] checkBoxes;
    private String[] nameCheckBoxes;
    private int i = 0;


    public MainView(){
        creerFenetreMain();
        initAttribut();
        pack();
        setResizable(false);
        setTitle("Map");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Initie les attributs dans les panels
    public void initAttribut(){
        //TODO : Rechercher via le CSV au lieu des pictogrammes
        File dir = new File("pictogrammes");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            nameCheckBoxes = new String[directoryListing.length];
            for (File child : directoryListing) {
                nameCheckBoxes[i] = child.getName().substring(0, child.getName().lastIndexOf('.'));
                i++;
            }
        } else {
            System.out.println("Erreur loading fichier");
        }
        checkBoxes = new JCheckBox[nameCheckBoxes.length];
        for (int j = 0; j < nameCheckBoxes.length; j++)
        {
            checkBoxes[j] = new JCheckBox(nameCheckBoxes[j]);
            panCheckBox.add(checkBoxes[j]);
        }
        panInformations.add(new JLabel("Informations"));
    }
    //Creer les panels dans la fenetre
    public void creerFenetreMain(){
        try {
            carte = new JPanel() {
                BufferedImage image = ImageIO.read(new File("Franche-Comte.jpg"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, 381, 516, this);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        panCheckBox = new JPanel();
        panCheckBox.setLayout(new GridLayout(10,3));
        panPrincipal = new JPanel();
        panPrincipal.setLayout(new BoxLayout(panPrincipal, BoxLayout.Y_AXIS));
        panInformations = new JPanel();
        panFond = new JPanel(new BorderLayout());

        carte.setPreferredSize( new Dimension( 381, 516 ) );
        panFond.add(carte,BorderLayout.WEST);
        panFond.add(panPrincipal,BorderLayout.CENTER);
        panPrincipal.add(panCheckBox);
        panPrincipal.add(panInformations);
        setContentPane(panFond);
    }

}
