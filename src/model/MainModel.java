package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainModel {
    private HashMap<String,ArrayList<PointInteret>> edifices;
    private HashMap<String,BufferedImage> pictoImages;

    CvsReader reader;
    public MainModel() {
        edifices= new HashMap<>();
        pictoImages= new HashMap<>();
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
        edifices.put("musee",museeArrayList);
        initPicto(edifices.keySet());

    }
    public BufferedImage getPictoImages(String str) {
        return pictoImages.get(str);
    }
    public void initPicto(Set<String> picto){
        for(String str:picto){
            try {
                if (str.equals(" site archéologique")){
                    pictoImages.put(str,ImageIO.read(new File("pictogrammes/site archéologique.png")));
                }
                else {
                    pictoImages.put(str, ImageIO.read(new File("pictogrammes/" + str + ".png")));
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(str);
            }
        }
    }
    public HashMap<String, ArrayList<PointInteret>> getEdifices() {
        return edifices;
    }
}
