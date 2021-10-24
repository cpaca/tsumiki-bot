package util;

import core.Main;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static util.BonusFunctions.*;
public class ImageStorage {
    public ArrayList<ImageStorage> childfiles = new ArrayList<>();
    public ArrayList<ZipEntry> images = new ArrayList<>();

    private ZipFile file;
    public ImageStorage(ZipFile zip){
        file = zip;
        search();
    }

    private void search(){

        /*
        try{
            if(file != null){
                Enumeration enumEntries = file.entries();
                ArrayList<ZipEntry> entries = new ArrayList<>();
                while (enumEntries.hasMoreElements()){
                    entries.add(ZipEntry.class.cast(enumEntries.nextElement()));
                }
                for ( ZipEntry entry : entries ) {
                    if(entry.isDirectory()){
                        childfiles.add(new ImageStorage(entry))
                    }
                    String name = entry.getName();
                    String type = "";
                    boolean founddot = false;
                    for ( String letter : name.split("") ) {
                        if(founddot){
                            type += letter;
                        }
                        else if(letter.equals(".")){
                            founddot = true;
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println("exception!");
        }
        //*/
    }
}
