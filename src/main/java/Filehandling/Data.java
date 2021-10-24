package Filehandling;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Data {

    private Map<String, String> data;
    private String filename;

    public Data(File file) throws FileNotFoundException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(file)));
        data = new HashMap<>();
        this.filename = file.getAbsolutePath();

        String[] lineData;
        while(s.hasNextLine()){
            lineData = s.nextLine().split(" \\|\\|\\| ");
            if(lineData.length != 2){
                continue; // ignore.
            }
            data.put(lineData[0],lineData[1]);
        }

        s.close();
    }

    public String getData(String s){
        if(data.containsKey(s)) {
            return data.get(s);
        }
        return "0"; // default values.
    }

    public void setData(String k, String v){
        // Do NOT have |'s in the key or value. They will not be loaded up properly.
        data.put(k, v);
    }
    public void setData(String k, int v){
        setData(k, Integer.toString(v));
    }

    public void wipeData(){
        data.clear();
    }

    public String getFilename(){
        return filename;
    }

    public void printTo(PrintStream printer){
        data.forEach((k, v) -> printer.println(k + " ||| " +  v));
    }

}
