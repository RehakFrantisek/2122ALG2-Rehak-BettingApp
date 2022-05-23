package utils;

import app.Bet;
import app.Money;
import app.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {

    public static void createData() {
        File folder = new File("data");
        if(!folder.exists()){
            folder.mkdir();
            //System.out.println(folder + " created");
            //System.out.println("File doesnt exists");
        }
    }

    public static void createFolder(String foldername){
        File folder = new File("data//"+foldername);
        if(!folder.exists()){
            folder.mkdir();
            //System.out.println(folder + " created");
            //System.out.println("File doesnt exists");
        }
        else {
            System.out.println("Folder 'username' exists");
        }
    }

    public static void createFile(String filename, String data) throws IOException {
        File file = new File(data+"//"+filename);
        if(!file.exists()){
            file.createNewFile();
            //System.out.println(filename + " created");
            //System.out.println("doesnt exists");
        }
        else {
            System.out.println("File login exists");
        }
    }

    public static void appendToFile(String filename, String input)
    {
        try
        {
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

    public static boolean checkBetHistory(String username){
        File file = new File("data//"+username+"//bets.csv");
        if(file.exists() && file.length() != 0){
            return true;
        }
        else{
            return false;
        }
    }

    public static String[] readCSV(String filepath){
        String data = "";
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("readCSV error");
            e.printStackTrace();
        }
        //System.out.println(data);
        return data.split("\n");
    }

}
