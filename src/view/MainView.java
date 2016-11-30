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


    public MainView(){
        creerFenetreMain();
        initAttribut();
        pack();
        setResizable(false);
        setTitle("Map");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Initie les attributs dans les panels
    public void initAttribut(){
        //TODO : Rechercher via le CSV au lieu des pictogrammes
        File dir = new File("pictogrammes");
        File[] directoryListing = dir.listFiles();
        int i = 0;
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

    public String[] getNameCheckBoxes()
    {
        return nameCheckBoxes;
    }

    public void setNameCheckBoxes(String[] nameCheckBoxes)
    {
        this.nameCheckBoxes = nameCheckBoxes;
    }

    public JPanel getPanPrincipal()
    {
        return panPrincipal;
    }

    public void setPanPrincipal(JPanel panPrincipal)
    {
        this.panPrincipal = panPrincipal;
    }

    public JPanel getCarte()
    {
        return carte;
    }

    public void setCarte(JPanel carte)
    {
        this.carte = carte;
    }

    public JPanel getPanCheckBox()
    {
        return panCheckBox;
    }

    public void setPanCheckBox(JPanel panCheckBox)
    {
        this.panCheckBox = panCheckBox;
    }

    public JPanel getPanFond()
    {
        return panFond;
    }

    public void setPanFond(JPanel panFond)
    {
        this.panFond = panFond;
    }

    public JPanel getPanInformations()
    {
        return panInformations;
    }

    public void setPanInformations(JPanel panInformations)
    {
        this.panInformations = panInformations;
    }

    public JCheckBox getCheckBox()
    {
        return checkBox;
    }

    public void setCheckBox(JCheckBox checkBox)
    {
        this.checkBox = checkBox;
    }

    public JCheckBox getCheckBox1()
    {
        return checkBox1;
    }

    public void setCheckBox1(JCheckBox checkBox1)
    {
        this.checkBox1 = checkBox1;
    }

    public JCheckBox[] getCheckBoxes()
    {
        return checkBoxes;
    }

    public void setCheckBoxes(JCheckBox[] checkBoxes)
    {
        this.checkBoxes = checkBoxes;
    }
}
