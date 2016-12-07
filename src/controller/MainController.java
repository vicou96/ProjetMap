package controller;

import model.MainModel;
import view.MainView;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainController{
    private controllerCity city;
    private checkBoxController check;
    public MainController(MainModel model, MainView view){
        city=new controllerCity(model, view);
        check=new checkBoxController(model, view);
    }


}
