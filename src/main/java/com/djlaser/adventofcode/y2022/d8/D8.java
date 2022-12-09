package com.djlaser.adventofcode.y2022.d8;

import java.util.ArrayList;
import java.util.List;

import com.djlaser.adventofcode.util.AOCDay;

public class D8 extends AOCDay {
    public enum Direction {
        UP(0, 1, "Up"),
        DOWN(0, -1, "Down"),
        LEFT(-1, 0, "Left"),
        RIGHT(1, 0, "Right");

        public final int row;
        public final int column;
        public final String name;
        
        Direction(int row, int column, String name){
            this.row = row;
            this.column = column;
            this.name = name;
        }
    }

    @Override
    public void challenge1() {
        String input = readInput(false);
        String[] lines = input.split("\r?\n");
        int[][] trees = new int[lines.length][];
        int score = 0;

        for (int i = 0; i < lines.length; i++) {
            trees[i] = lines[i].chars().map(c -> Integer.parseInt("" + (char) c)).toArray();
        }

        for (int i = 1; i < trees.length - 1; i++) {

            for (int j = 1; j < trees[i].length - 1; j++) {
                int treeHeight = trees[i][j];

                for (Direction dir : Direction.values()) {
                    boolean stop = false;

                    for (int k = 1; ; k++) {
                        int row = i + dir.row * k;
                        int column = j + dir.column * k;

                        if (row < 0 || row >= trees.length) {
                            break;
                        } else if (column < 0 || column >= trees.length) {
                            break;
                        }

                        int otherTreeHeight = trees[row][column];
                        if (treeHeight <= otherTreeHeight) {
                            break;
                        } else if (row == 0 || row == trees.length -1) {
                            stop = true;
                            score++;
                            break;
                        } else if (column == 0 || column == trees.length -1) {
                            stop = true;
                            score++;
                            break;
                        }
                    }

                    if (stop) {
                        break;
                    }
                }
            }
        }

        System.out.println(score + trees.length * 4 - 4);
    }
    
    @Override
    public void challenge2() {
        String input = readInput(false);
        String[] lines = input.split("\r?\n");
        int[][] trees = new int[lines.length][];
        int bestScore = 0;

        for (int i = 0; i < lines.length; i++) {
            trees[i] = lines[i].chars().map(c -> Integer.parseInt("" + (char) c)).toArray();
        }

        for (int i = 1; i < trees.length - 1; i++) {

            for (int j = 1; j < trees[i].length - 1; j++) {
                int treeHeight = trees[i][j];

                List<Integer> scores = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    int score = 0;

                    for (int k = 1; ; k++) {
                        int row = i + dir.row * k;
                        int column = j + dir.column * k;
                        
                        if (row < 0 || row >= trees.length) {
                            break;
                        } else if (column < 0 || column >= trees.length) {
                            break;
                        }

                        score ++;

                        int otherTreeHeight = trees[row][column];
                        if (treeHeight <= otherTreeHeight) {
                            break;
                        }
                    }
                    
                    scores.add(score);
                }
                int myScore = scores.stream().reduce(1, (sub, el) -> sub * el);

                if (myScore > bestScore) {
                    bestScore = myScore;
                }
            }
        }

        System.out.println(bestScore);
    }
}