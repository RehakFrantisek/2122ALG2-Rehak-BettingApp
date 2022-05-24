package utils;

import java.io.*;
import java.util.Scanner;

public class FileUtils {

    public static void createData() {
        File folder = new File("data");
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    public static void createFolder(String foldername){
        File folder = new File("data//"+foldername);
        if(!folder.exists()){
            folder.mkdir();
        }
        else {
            //System.out.println("Folder 'username' exists");
        }
    }

    public static void createFile(String filename, String data) throws IOException {
        File file = new File(data+"//"+filename);
        if(!file.exists()){
            file.createNewFile();
        }
        else {
            //System.out.println("File login exists");
        }
    }

    public static void appendToFile(String filename, String input)
    {
        try
        {
            File myFile = new File(filename);
            if(!doesFileExists(filename)){
                myFile.createNewFile();
            }
            FileWriter csvWriter = new FileWriter(filename, true);
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            sb.append("\n");
            csvWriter.write(sb.toString());
            csvWriter.close();
        } catch (IOException e)
        {
            System.out.println("Error with writing to " + filename);
            e.printStackTrace();
        }
    }

    public static boolean doesFileExists(String filename){
        File myFile = new File(filename);
        if(!myFile.exists()){
            return false;
        }
        return true;
    }

    public static void rWFile(String filename, String input)
    {
        try
        {
            FileWriter csvWriter = new FileWriter(filename, false);
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            sb.append("\n");
            csvWriter.write(sb.toString());
            csvWriter.close();
        } catch (IOException e)
        {
            System.out.println("Error with writing to " + filename);
            e.printStackTrace();
        }
    }


    public static String[] readCSV(String filepath){
        String data = "";
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("readCSV error");
            e.printStackTrace();
        }
        return data.split("\n");
    }

}
