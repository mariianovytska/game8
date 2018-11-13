package com.company;

import java.util.UUID;

public class Game8 {

    private static String ideal = "123456780";

    public String play(int [][] range){
        String file = fileNameBuilder();
        int step = 0;
        return step(range, file, step);
    }

    private String step(int [][] range, String file, int step){
        System.out.println("\nSTEP NUMBER: "+step++);
        String newRange = Converter.arrayToString(range);
        System.out.println("STEP RANGE: "+newRange);
        if(!newRange.equals(ideal)){
            FileBuilder.rewriteFile(file, Converter.arrayToString(range));
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
            return next(range, file, startI, startJ, step);
        } else {
            return "CONGRATS!! You WIN the 'GAME 8' on step: "+step;
        }
    }

    private String next(int [][] range, String file, int startI, int startJ, int step){
        if(startJ + 1 < range.length){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI][startJ + 1];
            newRange[startI][startJ + 1] = 0;
            if(!FileBuilder.isAlreadyInFile(file, newRange)){
                System.out.println("Saved range direction 1: "+ Converter.arrayToString(newRange));
                saveRangeInFile(newRange, file, false);
            }
        }
        if(startJ - 1 >= 0){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI][startJ - 1];
            newRange[startI][startJ - 1] = 0;
            if(!FileBuilder.isAlreadyInFile(file, newRange)){
                System.out.println("Saved range direction 2: "+ Converter.arrayToString(newRange));
                saveRangeInFile(newRange, file, false);
            }
        }
        if(startI - 1 >= 0){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI - 1][startJ];
            newRange[startI - 1][startJ] = 0;
            if(!FileBuilder.isAlreadyInFile(file, newRange)){
                System.out.println("Saved range direction 3: "+ Converter.arrayToString(newRange));
                saveRangeInFile(newRange, file, false);
            }
        }
        if(startI + 1 < range.length){
            int [][] newRange = copyArray(range);
            newRange[startI][startJ] = range[startI + 1][startJ];
            newRange[startI + 1][startJ] = 0;
            if(!FileBuilder.isAlreadyInFile(file, newRange)){
                System.out.println("Saved range direction 4: "+ Converter.arrayToString(newRange));
                saveRangeInFile(newRange, file, false);
            }
        }
        return step(FileBuilder.getRange(file), file, step);
    }

    private void saveRangeInFile (int [][] range, String file, Boolean bool){
        String base = Converter.arrayToString(range);
        if(bool){
            base = base.concat("t");
        } else {
            base = base.concat("f");
        }
        FileBuilder.writeToFile(file, base);
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

    private String fileNameBuilder(){
        return UUID.randomUUID().toString()+".txt";

    }
}
