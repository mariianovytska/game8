package com.company;

public class Game8v2 {

    private static String ideal = "123456780";
    private int step = 0;
    private String file = FileBuilder.fileNameBuilder();
    private int index = 1;
    private int k;
    private boolean first = false;
    private int st = 0;

    public String playGame8(int [][] range){
        String base = Converter.arrayToString(range);
        String isFind = "N";
        if(base.equals(ideal)){
            return "CONGRATS!! You WIN the 'GAME 8' on step: "+step;
        } else {
            FileBuilder.writeToFile(file, base);
            while (isFind.equals("N")) {
                int[][] newRange = null;
                if (k == 0) {
                    if (!first) {
                        newRange = FileBuilder.getRangeAt(file, --index);
                        first = true;
                    } else {
                        newRange = FileBuilder.getRangeAt(file, index-1-st);
                        ++st;
                    }
                } else {
                    st = 0;
                    newRange = FileBuilder.getRangeAt(file, index);
                }
                if (newRange == null) {
                    break;
                }
                System.out.println("\nRANGE NUMBER: " + ++step);
                System.out.println(toString(newRange));
                int[] start = startPoint(newRange);
                isFind = saveDirections(newRange, start);
            }
        }
        return "\nCONGRATS!! You WIN the 'GAME 8' on step: "+step
                + "\n" + toString(Converter.stringToArray(isFind));
    }

    private  int [] startPoint(int [][] range){
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
        return new int[] {startI, startJ};
    }

    private String saveDirections(int [][] range, int [] startPoints){
        k = 0;
        int i = startPoints[0];
        int j = startPoints[1];
        int [][] newRange = copyArray(range);
        while (j + 1 < range.length){
            newRange[i][j] = range[i][j + 1];
            newRange[i][j + 1] = 0;
            saveRangeInFile(newRange, 1);
            if(Converter.arrayToString(newRange).equals(ideal)){
                return Converter.arrayToString(newRange);
            }
            ++j;
        }
        i = startPoints[0];
        j = startPoints[1];
        newRange = copyArray(range);
        while (j - 1 >= 0){
            newRange[i][j] = range[i][j - 1];
            newRange[i][j - 1] = 0;
            saveRangeInFile(newRange, 2);
            if(Converter.arrayToString(newRange).equals(ideal)){
                return Converter.arrayToString(newRange);
            }
            --j;
        }
        i = startPoints[0];
        j = startPoints[1];
        newRange = copyArray(range);
        while (i - 1 >= 0){
            newRange[i][j] = range[i - 1][j];
            newRange[i - 1][j] = 0;
            saveRangeInFile(newRange, 3);
            if(Converter.arrayToString(newRange).equals(ideal)){
                return Converter.arrayToString(newRange);
            }
            --i;
        }
        i = startPoints[0];
        j = startPoints[1];
        newRange = copyArray(range);
        while (i + 1 < range.length){
            newRange[i][j] = range[i + 1][j];
            newRange[i + 1][j] = 0;
            saveRangeInFile(newRange, 4);
            if(Converter.arrayToString(newRange).equals(ideal)){
                return Converter.arrayToString(newRange);
            }
            ++i;
        }
        return "N";
    }

    private void saveRangeInFile(int [][] range, int direction){
        if(!FileBuilder.isAlreadyInFile(file, range)){
            System.out.println("Saved range direction "+direction+": "+ Converter.arrayToString(range));
            String base = Converter.arrayToString(range);
            FileBuilder.writeToFile(file, base);
            ++index;
            ++k;
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
