package com.company;

public class Converter {

    public static String arrayToString (int [][] range){
        String res = "";
        for(int i = 0; i < range.length; i++){
            for(int j = 0; j < range.length; j++){
                res = res.concat(String.valueOf(range[i][j]));
            }
        }
        return res;
    }

    public static int [][] stringToArray (String res){
        int length =(int) Math.round(Math.sqrt(res.length()));
        int [][] range = new int[length][length];
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(i == 0){
                    range[i][j] = Integer.parseInt(res.substring(j, j+1));
                }
                else {
                    range[i][j] = Integer.parseInt(res.substring(j+(length*i), j+(length*i)+1));
                }
            }
        }
        return range;
    }
}
