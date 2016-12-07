package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Victor on 30/11/2016.
 */
public class MainModel {
    private HashMap<String,ArrayList<PointInteret>> edifices;
    private HashMap<String,BufferedImage> pictoImages;



    private ArrayList<String> villes;

    CvsReader reader;
    public MainModel() {
        HashSet<String> villestmp=new HashSet<>();
        edifices= new HashMap<>();
        pictoImages= new HashMap<>();
        reader=new CvsReader();
        ArrayList<String[]> tmp=reader.readFileSeparatedvalues("MonumentsHistoriquesFrancheComte.csv");
        tmp.remove(0);
        for (String[] str:tmp) {
            MonumentHistorique monumentHistoriquetmp=new MonumentHistorique(str);
            villestmp.add(monumentHistoriquetmp.getCommune());
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
            villestmp.add(tmp1.getCommune());
        }
        edifices.put("musee",museeArrayList);
        initPicto(edifices.keySet());
        villes=new ArrayList<>(villestmp);
        Collections.sort(villes);

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
    public ArrayList<String> getVilles() {
        return villes;
    }
    public List<PointInteret> searchByName(String search){
        ArrayList list=new ArrayList();
        for (ArrayList<PointInteret> listpt:edifices.values()){
            for (PointInteret pt:listpt){
                if (pt.getDesignation().contains(search)){
                    list.add(pt);
                }
            }
        }
        return list;
    }
}
