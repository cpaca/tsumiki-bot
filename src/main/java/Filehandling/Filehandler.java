package Filehandling;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class Filehandler {

    public static final File BaseFile = new File(System.getProperty("user.dir"));
    public static final String FileSeparator = System.getProperty("file.separator");

    @NotNull
    public static File getStorageFile(){
        File file = new File(BaseFile.getAbsolutePath() + "\\DataStorage");
        if(file.getAbsoluteFile().exists())
            return file;
        try{
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ioe){
            System.out.println("FileHandler error: 1!");
            ioe.printStackTrace();
        }
        return file;
    }

    @NotNull
    public static String getStorageFilePath(){
        return BaseFile.getAbsolutePath() + "\\DataStorage";
    }

    @NotNull
    public static File getFile(String pathname){
        return getFile(pathname, true);
    }

    @NotNull
    public static File getFile(String pathname, boolean mustExist){
        pathname = getStorageFilePath() + "\\" + pathname;
        pathname = pathname.replace("/",FileSeparator);
        pathname = pathname.replace("\\",FileSeparator);
        return getRawFile(pathname, true);
    }

    @NotNull
    public static File getRawFile(String pathname){
        return getRawFile(pathname, true);
    }

    @NotNull
    public static File getRawFile(String pathname, boolean mustExist){
        File file = new File(pathname);
        if(!mustExist){
            return file;
        }
        if(file.getAbsoluteFile().exists())
            return file;
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ioe){
            System.out.println("FileHandler error: 2!");
            ioe.printStackTrace();
        }
        return file;
    }

    @Nullable
    public static Data getConfig(String type){
        File file = getFile("config/" + type + ".txt");
        try {
            return new Data(file);
        } catch (FileNotFoundException fnfe) {
            System.out.println("**Theoretically impossible Error Code: -1**");
            fnfe.printStackTrace();
        }
        return null;
    }

    // this saves ANY form of [Data] class
    // Works for user, global config, etc.
    public static void saveData(Data c){
        File file = getRawFile(c.getFilename());
        PrintStream out;
        try{
            out = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException fnfe){
            System.out.println("**Theoretically Impossible Error Code: -2");
            fnfe.printStackTrace();
            return;
        }
        c.printTo(out);
        out.close();
    }

    public static Data getUserData(String ID){
        File file = getFile("user/" + ID + ".txt");
        try{
            return new Data(file);
        } catch (FileNotFoundException fnfe){
            System.out.println("**Theoretically impossible Error Code: -3");
            fnfe.printStackTrace();
        }
        return null;
    }
    public static Data getUserData(long ID){
        return getUserData("" + ID);
    }
    public static Data getUserData(User u){
        return getUserData(u.getId());
    }
}
