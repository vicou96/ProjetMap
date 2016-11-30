package controller;

import model.MainModel;
import view.MainView;

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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

    }
}
