package util;

import java.util.ArrayList;

public class Censorer {

    private ArrayList<String> swears = new ArrayList<>();

    public void addSwear(String s){
        swears.add(s);
    }

    public boolean checkSwear(String check){
        for(String s:swears){
            if(check.contains(s))
                return true;
        }
        return false;
    }

}
