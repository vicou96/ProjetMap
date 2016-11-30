package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 30/11/2016.
 */
public class CvsReader {
    public static String getResourcePath(String fileName) {
        final File f = new File("");
        final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
        return dossierPath;
    }

    public static File getResource(String fileName) {
        final String completeFileName = getResourcePath(fileName);
        File file = new File(completeFileName);
        return file;
    }

    public static List<String> readFile(File file) {

        List<String> result = new ArrayList<String>();

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
    public ArrayList<String[]> readFileSeparatedvalues(String filename){
        ArrayList<String []> data=new ArrayList<>();
        File g1=getResource(filename);
        List<String> cvs=readFile(g1);
        for (String line : cvs) {
            String[] oneData = line.split("\t");
            data.add(oneData);
        }
        return data;
    }

}
