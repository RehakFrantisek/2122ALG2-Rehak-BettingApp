package utils;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Frantisek Rehak
 */
public class FileUtils {
    /**
     * This is class representing utilies for working with files.
     */
    public static void createData() {
        /**
         * This method create folder data
         */
        File folder = new File("data");
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    public static void createFolder(String foldername){
        /**
         * This method create folder.
         * @param foldername Folder name being created.
         */
        File folder = new File("data//"+foldername);
        if(!folder.exists()){
            folder.mkdir();
        }
        else {
            //System.out.println("Folder 'username' exists");
        }
    }

    public static void createFile(String filename, String data) throws IOException {
        /**
         * This method create file
         * @param filename File name being created.
         */
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
        /**
         * This method append data to file
         * @param filename File name where data are appended.
         * @param input Data.
         */
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

    /**
     *
     * @param filename
     * @return
     */
    public static boolean doesFileExists(String filename){
        /**
         * This method check if file exists
         * @param filename File name of file being checked.
         */
        File myFile = new File(filename);
        if(!myFile.exists()){
            return false;
        }
        return true;
    }

    public static void rWFile(String filename, String input)
    {
        /**
         * This method write data to file.
         * @param filename File name of file where data are write.
         * @param input Data.
         */
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
        /**
         * This method return data from CSV file.
         * @param filepath File path of file being read.
         * @return Return data from file.
         */
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
