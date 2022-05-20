package utils;

import java.io.*;

public class FileUtils {

    public static void createData(){
        File folder = new File("data");
        if(folder.exists()){
            System.out.println("File exists");
        }
        else {
            folder.mkdir();
            //System.out.println(folder + " created");
            //System.out.println("File doesnt exists");
        }
    }

    public static void createFolder(String foldername){
        File folder = new File("data//"+foldername);
        if(folder.exists()){
            System.out.println("File exists");
        }
        else {
            folder.mkdir();
            //System.out.println(folder + " created");
            //System.out.println("File doesnt exists");
        }
    }

    public static void createFile(String foldername, String filename) throws IOException {
        File file = new File("data//"+foldername+"//"+filename);
        if(file.exists()){
            System.out.println("User exists");
        }
        else {
            file.createNewFile();
            //System.out.println(filename + " created");
            //System.out.println("doesnt exists");
        }
    }

    public static void appendToFile(String filename, String input)
    {
        try
        {
            PrintWriter csvWriter = new PrintWriter(filename);
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
}
