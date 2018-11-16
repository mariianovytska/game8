package com.company;

public class Game8v2 {

    private static String ideal = "123456780";
    private int step = 0;

    public String playGame8(int [][] range){
        String file = FileBuilder.fileNameBuilder();
        System.out.println("\nRANGE NUMBER: "+ ++step);
        System.out.println(toString(range));
        String base = Converter.arrayToString(range);
        FileBuilder.writeToFile(file, base);
        int index = 0;
        while (true){
            int[][] newRange = copyArray(FileBuilder.getRangeAt(file, index++));
            if(newRange == null){
                break;
            }
            if(!Converter.arrayToString(newRange).equals(ideal)){
                System.out.println("\nRANGE NUMBER: "+ ++step);
                System.out.println(toString(newRange));
                next(newRange, file);
            } else {
                return "CONGRATS!! You WIN the 'GAME 8' on step: "+step;
            }
        }
        return "Sorry You FAILED GAME 8";
    }

    private void next(int [][] range, String file){
        int startI = 0;
        int startJ = 0;
        for(int i = 0; i < range.length; i++){
            for (int j = 0; j < range.length; j++){
                if(range[i][j] == 0){
                    startI = i;
                    startJ = j;
                }
            }
        }
        saveDirections(range, file, startI, startJ);
    }

    private void saveDirections(int [][] range, String file, int startI, int startJ){
        if(startJ + 1 < range.length){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI][startJ + 1];
            newRange[startI][startJ + 1] = 0;
            saveRangeInFile(file, newRange, 1);
        }
        if(startJ - 1 >= 0){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI][startJ - 1];
            newRange[startI][startJ - 1] = 0;
            saveRangeInFile(file, newRange, 2);
        }
        if(startI - 1 >= 0){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI - 1][startJ];
            newRange[startI - 1][startJ] = 0;
            saveRangeInFile(file, newRange, 3);
        }
        if(startI + 1 < range.length){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI + 1][startJ];
            newRange[startI + 1][startJ] = 0;
            saveRangeInFile(file, newRange, 4);
        }
    }

    private void saveRangeInFile(String file, int [][] range, Integer direction){
        if(!FileBuilder.isAlreadyInFile(file, range)){
            System.out.println("Saved range direction "+direction+": "+ Converter.arrayToString(range));
            String base = Converter.arrayToString(range);
            FileBuilder.writeToFile(file, base);
        }
    }

    private int[][] copyArray(int [][] arr){
        int [][] copy = new int [arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                copy[i][j] = arr [i][j];
            }
        }
        return copy;
    }

    private String toString(int [][] range){
        return String.valueOf(range[0][0])+" "+String.valueOf(range[0][1])+" "+String.valueOf(range[0][2])+
                "\n"+String.valueOf(range[1][0])+" "+String.valueOf(range[1][1])+" "+String.valueOf(range[1][2])+
                "\n"+String.valueOf(range[2][0])+" "+String.valueOf(range[2][1])+" "+String.valueOf(range[2][2]);
    }
}
