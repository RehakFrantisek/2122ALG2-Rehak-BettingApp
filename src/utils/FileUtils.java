package utils;

import java.io.*;
import java.sql.SQLOutput;

public class FileUtils {
    public static void createFolder(){
        File folder = new File("accounts");
        if(folder.exists()){
            System.out.println("exists");
        }
        else {
            folder.mkdir();
            System.out.println("doesnt exists");
        }
    }

    public static void createFile(String filename){
        File file = new File("//accounts//" + filename);
        if(file.exists()){
            System.out.println("exists");
        }
        else {
            System.out.println("doesnt exists");
        }
    }
    
}
