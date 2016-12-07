package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainView extends JFrame{

    private JPanel panPrincipal,carte,panCheckBox,panFond,panInformations,panVilles
            ,panelListes,panelSearch;

    private JCheckBox[] checkBoxes;

    private BufferedImage imageFR;

    private JTabbedPane panTab;

    private ArrayList<JButton> buttons;

    private JTextPane info;

    //Info liés au deuxième onglet avec les villes
    private JList<String> myList,myList1;

    private String[] data = {"one", "two", "three", "four"};

    private JTextField textField;

    private JLabel labTextField;

    private JButton btnValiderSearch;

    public MainView(){
        creerFenetreMain();
        pack();
        setResizable(false);
        setTitle("Map");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Initie les attributs dans les panels
    public void initAttribut(Set<String> listNames){

        buttons=new ArrayList<>();

        int i=0;
        checkBoxes = new JCheckBox[listNames.size()];
        for (String str:listNames) {
            checkBoxes[i] = new JCheckBox(str);
            checkBoxes[i].setPreferredSize(new Dimension(20, 50));
            panCheckBox.add(checkBoxes[i]);
            i++;
        }

        info=new JTextPane();
        info.setContentType("text/html");
        info.setBackground(null);
        info.setEditable(false);

        panInformations.setLayout(new BorderLayout());
        panInformations.add(info);
    }
    //Creer les panels dans la fenetre
    public void creerFenetreMain(){
        //Open the file
        try {
            imageFR= ImageIO.read(new File("Franche-Comte.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Put the file in the panel
        carte = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageFR, 0, 0, 381, 516, this);
            }
        };
        carte.setPreferredSize(new Dimension(381, 516));

        //Panel des check box
        panCheckBox = new JPanel();
        GridLayout gg=new GridLayout(4,6);
        panCheckBox.setLayout(gg);

        //Pan Informations pour le texte
        panInformations = new JPanel();

        //Panel villes pour les trier par viles
        panVilles = new JPanel();
        panVilles.setLayout(new BoxLayout(panVilles,BoxLayout.Y_AXIS));

        panelListes = new JPanel();
        panelListes.setLayout(new BoxLayout(panelListes,BoxLayout.X_AXIS));
        panelSearch = new JPanel();
        panelSearch.setLayout(new BoxLayout(panelSearch,BoxLayout.X_AXIS));


        myList = new JList<String>(data);
        myList1 = new JList<String>(data);

        labTextField = new JLabel("Rechercher par nom");
        textField = new JTextField();
        btnValiderSearch = new JButton("Chercher");

        panelSearch.add(labTextField);
        panelSearch.add(textField);
        panelSearch.add(btnValiderSearch);

        panelListes.add(myList);
        panelListes.add(myList1);
        myList.setPreferredSize(new Dimension(500,200));
        myList1.setPreferredSize(new Dimension(500,200));
        panelListes.setPreferredSize(new Dimension(1000,200));

        panVilles.add(panelListes);
        panVilles.add(panelSearch);

        //Panel des onglets
        panTab = new JTabbedPane();
        panTab.addTab("Catégories",panCheckBox);
        panTab.addTab("Trier par villes",panVilles);

        //Panel principal : informations holder (panel des onglets + panel texte)
        panPrincipal = new JPanel();
        panPrincipal.setLayout(new BoxLayout(panPrincipal, BoxLayout.Y_AXIS));
        panPrincipal.add(panTab);
        panPrincipal.add(panInformations);

        //Panel fond : general holder
        panFond = new JPanel(new BorderLayout());
        panFond.add(carte, BorderLayout.WEST);
        panFond.add(panPrincipal, BorderLayout.CENTER);

        setContentPane(panFond);
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


    public JCheckBox[] getCheckBoxes()
    {
        return checkBoxes;
    }

    public void setCheckBoxes(JCheckBox[] checkBoxes)
    {
        this.checkBoxes = checkBoxes;
    }

    public BufferedImage getImageFR() {
        return imageFR;
    }

    public void setActionListener(ActionListener listener, JCheckBox check){
        check.addActionListener(listener);
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public JTextPane getInfo() {
        return info;
    }

    public JPanel getPanVilles()
    {
        return panVilles;
    }

    public void setPanVilles(JPanel panVilles)
    {
        this.panVilles = panVilles;
    }

    public JPanel getPanelListes()
    {
        return panelListes;
    }

    public void setPanelListes(JPanel panelListes)
    {
        this.panelListes = panelListes;
    }

    public JPanel getPanelSearch()
    {
        return panelSearch;
    }

    public void setPanelSearch(JPanel panelSearch)
    {
        this.panelSearch = panelSearch;
    }

    public void setImageFR(BufferedImage imageFR)
    {
        this.imageFR = imageFR;
    }

    public JTabbedPane getPanTab()
    {
        return panTab;
    }

    public void setPanTab(JTabbedPane panTab)
    {
        this.panTab = panTab;
    }

    public void setButtons(ArrayList<JButton> buttons)
    {
        this.buttons = buttons;
    }

    public void setInfo(JTextPane info)
    {
        this.info = info;
    }

    public JList<String> getMyList()
    {
        return myList;
    }

    public void setMyList(JList<String> myList)
    {
        this.myList = myList;
    }

    public JList<String> getMyList1()
    {
        return myList1;
    }

    public void setMyList1(JList<String> myList1)
    {
        this.myList1 = myList1;
    }

    public String[] getData()
    {
        return data;
    }

    public void setData(String[] data)
    {
        this.data = data;
    }

    public JTextField getTextField()
    {
        return textField;
    }

    public void setTextField(JTextField textField)
    {
        this.textField = textField;
    }

    public JLabel getLabTextField()
    {
        return labTextField;
    }

    public void setLabTextField(JLabel labTextField)
    {
        this.labTextField = labTextField;
    }

    public JButton getBtnValiderSearch()
    {
        return btnValiderSearch;
    }

    public void setBtnValiderSearch(JButton btnValiderSearch)
    {
        this.btnValiderSearch = btnValiderSearch;
    }
}
