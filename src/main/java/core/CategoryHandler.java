package core;

import java.util.ArrayList;
import java.util.List;

public class CategoryHandler {

    public static final CategoryHandler instance = new CategoryHandler();

    private static List<String> categories = new ArrayList<>();

    private CategoryHandler(){

    }

    public static String getCategoryName(int ID){
        return categories.get(ID);
    }

    public static int getCategoryNum(String name){

        String str;
        for(int i = 0; i < categories.size(); i++){
            str = categories.get(i);
            if(str.equals(name)){
                return i;
            }
        }
        // category with identical name does NOT, **__NOT__** already exist
        for(String catName : categories){
            if(catName.equalsIgnoreCase(name)){
                System.out.println("Warning: Adding category " +
                        "\"" + name + "\"" +
                        " with equals-ignore-case name " +
                        "\"" + catName + "\"" +
                        " already present. Possibly an error!");
            }
        }

        categories.add(name);
        return categories.size() - 1;
    }

    public static boolean IsCategory(String category){
        for(String str : categories){
            if(str.equals(category))
                return true;
        }
        return false;
    }



}
