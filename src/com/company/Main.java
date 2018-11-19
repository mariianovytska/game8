package com.company;

public class Main {

    public static void main(String[] args) {
        Game8v1 game8V1 = new Game8v1();
        Game8v2 game8V2 = new Game8v2();
        int[][] range = {
                {8, 7, 6},
                {5, 4, 3},
                {0, 2, 1},
        };
        int[][] range2 = {
                {1, 2, 3},
                {4, 5, 6},
                {0, 7, 8},
        };
        int [] rule = {3,1,4,2};
//        System.out.println(game8V1.playGame8(range));
        System.out.println(game8V2.playGame8(range));
    }
}
