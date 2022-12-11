package com.djlaser.adventofcode.y2022.d10;

import com.djlaser.adventofcode.util.AOCDay;

import java.util.List;

public class D10 extends AOCDay {

    @Override
    public void challenge1() {
        String input = readInput();
        int registerX = 1;
        int signalSum = 0;
        int i = 0;
        int subCycle = 0;

        List<String> lines = input.lines().toList();
        for (int cycleNum = 1; i < lines.size(); cycleNum++) {
            String line = lines.get(i);
            String[] args = line.split(" ");

            if ((cycleNum + 20) % 40 == 0) {
                signalSum += cycleNum * registerX;
            }

            if (args[0].equals("addx")) {
                if (subCycle == 1) {
                    registerX += Integer.parseInt(args[1]);
                    subCycle = 0;
                    i ++;
                } else {
                    subCycle ++;
                }
            } else {
                i ++;
            }
        }

        System.out.println(signalSum);
    }

    @Override
    public void challenge2() {
        String input = readInput();
        int registerX = 1;
        int i = 0;
        int subCycle = 0;

        List<String> lines = input.lines().toList();
        for (int cycleNum = 1; i < lines.size(); cycleNum++) {
            String line = lines.get(i);
            String[] args = line.split(" ");

            if (Math.abs(cycleNum % 40 - registerX - 1) <= 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }

            if (cycleNum  % 40 == 0) {
                System.out.println();
            }

            if (args[0].equals("addx")) {
                if (subCycle == 1) {
                    registerX += Integer.parseInt(args[1]);
                    subCycle = 0;
                    i ++;
                } else {
                    subCycle ++;
                }
            } else {
                i ++;
            }
        }
    }
}
