package view;

import javax.swing.*;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainView extends JFrame{
    private JPanel panPrincipal;
    private JCheckBox checkBox;


    public MainView(){
        initAttribut();
        creerFenetreMain();
        pack();
        setResizable(false);
        setTitle("Map");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkBox.setVisible(true);
        panPrincipal.add(checkBox);
    }

    public void initAttribut(){
        checkBox = new JCheckBox();
    }

    public void creerFenetreMain(){
        panPrincipal = new JPanel();
        setContentPane(panPrincipal);
    }

}
