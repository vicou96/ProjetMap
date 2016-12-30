package controller;

import model.MainModel;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Victor on 07/12/2016.
 */
public class controllerCity implements MouseListener,ActionListener {
    private MainView view;
    private MainModel model;

    public controllerCity(MainModel model, MainView view){
        this.model=model;
        this.view=view;
        view.setListListener(this,view.getMyList());
        view.setListListener(this,view.getMyList1());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass()==JList.class){
            if (e.getClickCount() == 2) {

                JList list= (JList) e.getSource();
                String selectedItem = (String) list.getSelectedValue();
                String[] m1;
                String[] m2;
                if (list.equals(view.getMyList())){
                    m1=  getStringTabFromJlistPlusAddElement(view.getMyList1(),selectedItem);
                    m2=removeFromJlist(view.getMyList(),selectedItem);
                    view.getMyList1().setListData(m1);
                    view.getMyList().setListData(m2);
                }
                else {
                    m1=getStringTabFromJlistPlusAddAlementPlusSort(view.getMyList(),selectedItem);
                    m2=removeFromJlist(view.getMyList1(),selectedItem);
                    view.getMyList().setListData(m1);
                    view.getMyList1().setListData(m2);
                }
                deselectCheck();
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void deselectCheck(){
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
    public String[] getStringTabFromJlistPlusAddElement(JList list,String element){
        String [] st=new String[list.getModel().getSize()];
        for (int i = 0; i < list.getModel().getSize(); i++) {
           st[i]= (String) list.getModel().getElementAt(i);
        }

        if (st.length==0){
            st=new String[1];
            st[0]=element;
        }
        else {
            String[] tmp=new String[st.length+1];
            for (int i = 0; i < st.length; i++) {
                tmp[i]=st[i];
            }
            tmp[st.length]=element;
            st=tmp;
        }
        return st;
    }

    public String[] getStringTabFromJlistPlusAddAlementPlusSort(JList list,String element){
        String [] st=new String[list.getModel().getSize()+1];
        for (int i = 0; i < list.getModel().getSize(); i++) {
            st[i]= (String) list.getModel().getElementAt(i);
        }

        st[st.length-1]=element;
        ArrayList tmp2=new ArrayList();
        for (int i = 0; i < st.length; i++) {
            tmp2.add(st[i]);
        }
        Collections.sort(tmp2);
        st= (String[]) tmp2.toArray(new String[0]);
        return st;
    }
    public String[] removeFromJlist(JList list,String element){
        String [] st=new String[list.getModel().getSize()];
        for (int i = 0; i < list.getModel().getSize(); i++) {
            st[i]= (String) list.getModel().getElementAt(i);
        }
        String[] tmp=new String[st.length-1];
        int i=0;
        int j=0;
        while (i<tmp.length){

            if (!st[j].equals(element)){
                tmp[i]=st[j];
                i++;
            }
            j++;
        }
        return tmp;
    }
    public void switchAll(JList from,JList to){
        ArrayList<String> toStr=new ArrayList<>();
        for (int i = 0; i < to.getModel().getSize(); i++) {
            toStr.add((String) to.getModel().getElementAt(i));
        }
        for (int i = 0; i < from.getModel().getSize(); i++) {
            toStr.add((String) from.getModel().getElementAt(i));
        }
        Collections.sort(toStr);
        String[] st= (String[]) toStr.toArray(new String[0]);
        to.setListData(st);
        from.setListData(new String[0]);
        deselectCheck();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnShiftAllLeft())){
            switchAll(view.getMyList1(),view.getMyList());
        }
        else{
            switchAll(view.getMyList(),view.getMyList1());
        }
    }
}
