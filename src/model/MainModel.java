package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainModel {
    private HashMap<String,ArrayList<PointInteret>> edifices;
    CvsReader reader;
    public MainModel() {
        edifices= new HashMap<>();
        reader=new CvsReader();
        ArrayList<String[]> tmp=reader.readFileSeparatedvalues("MonumentsHistoriquesFrancheComte.csv");
        tmp.remove(0);
        for (String[] str:tmp) {
            MonumentHistorique monumentHistoriquetmp=new MonumentHistorique(str);
            if(edifices.containsKey(monumentHistoriquetmp.getCategorie())){
                edifices.get(monumentHistoriquetmp.getCategorie()).add(monumentHistoriquetmp);
            }
            else {
                ArrayList<PointInteret> tmpArray=new ArrayList<>();
                tmpArray.add(monumentHistoriquetmp);
                edifices.put(monumentHistoriquetmp.getCategorie(),tmpArray);
            }
        }

        ArrayList<String[]> tmpMusee=reader.readFileSeparatedvalues("Musee.csv");
        ArrayList<PointInteret> museeArrayList=new ArrayList<>();
        tmpMusee.remove(0);
        for (String[] str:tmpMusee) {
            Musee tmp1=new Musee(str);
            museeArrayList.add(tmp1);

        }
        edifices.put("Musée",museeArrayList);

    }
    public HashMap<String, ArrayList<PointInteret>> getEdifices() {
        return edifices;
    }

}
