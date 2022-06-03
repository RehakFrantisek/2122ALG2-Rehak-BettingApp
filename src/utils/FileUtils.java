package utils;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Frantisek Rehak
 */
public class FileUtils {

    public static void createFolder(String foldername){
        /**
         * This method create folder.
         * @param foldername Folder name being created.
         */
        File folder = new File(foldername);
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
        file.createNewFile();

        /*
        if(!file.exists()){
            file.createNewFile();
        }
        else {
            //System.out.println("File login exists");
        }

         */
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
            doesFileExists(filename);
            myFile.createNewFile();
            FileWriter csvWriter = new FileWriter(filename, true);
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            csvWriter.write(sb.toString());
            sb.append("\n");
            csvWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("Error with writing to " + filename);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filename
     * @return
     */
    public static void doesFileExists(String filename) throws FileNotFoundException{
        /**
         * This method check if file exists
         * @param filename File name of file being checked.
         */
        File myFile = new File(filename);
        if(myFile.exists()){
            return;
        }
    }

    public static void createEntryFiles() throws IOException {
        createFolder("data");
        createFile("login.csv","data");
    }

    public static void rWFile(String filename, String input) throws IOException {
        /**
         * This method write data to file.
         * @param filename File name of file where data are write.
         * @param input Data.
         */
        FileWriter csvWriter = new FileWriter(filename, false);
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append("\n");
        csvWriter.write(sb.toString());
        csvWriter.close();
    }

    public static String[] readCSV(String filepath) throws FileNotFoundException{
        /**
         * This method return data from CSV file.
         * @param filepath File path of file being read.
         * @return Return data from file.
         */
        String data = "";
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        return data.split("\n");
    }

}
