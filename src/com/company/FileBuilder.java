package com.company;

import java.io.*;
import java.util.UUID;

public class FileBuilder {

    static void readFromFile(String fileName){
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");
        }
    }

    static void writeToFile(String fileName, String text){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

    static boolean isAlreadyInFile(String fileName, int [][] range){
        String line = null;
        String res = Converter.arrayToString(range);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                if(line.equals(res)){
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");
        }
        return false;
    }

    static int[][] getRangeAt(String fileName, int index){
        String line = null;
        int [][] range = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                if (i++ == index) {
                    range = Converter.stringToArray(line);
                    bufferedReader.close();
                    return range;
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");
        }
        return range;
    }

    public static String fileNameBuilder(){
        return UUID.randomUUID().toString()+".txt";

    }

}
