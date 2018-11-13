package com.company;

import java.io.*;

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

    static void rewriteFile(String fileName, String text){
        File file = new File(fileName);
        if(text.length() == 9){
            writeToFile(fileName, text);
        }
        String newLine = text.substring(0,9).concat("t");
        File temp = null;
        String charset = "UTF-8";
        try{
            temp = File.createTempFile("file", ".txt", file.getParentFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
            for (String line; (line = reader.readLine()) != null;) {
                line = line.replace(text, newLine);
                writer.println(line);
            }
            reader.close();
            writer.close();
            file.delete();
            temp.renameTo(file);
        } catch (IOException e){
            System.out.println( "Error reading file '" + fileName + "'");
        }
    }

    static boolean isAlreadyInFile(String fileName, int [][] range){
        String line = null;
        String res = Converter.arrayToString(range);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                if(line.substring(0, 9).equals(res)){
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

    static int[][] getRange(String fileName){
        String line = null;
        int [][] range = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                if(line.substring(9).equals("f")){
                    range = Converter.stringToArray(line.substring(0, 9));
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
}
