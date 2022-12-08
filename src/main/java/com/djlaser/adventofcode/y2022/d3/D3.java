package com.djlaser.adventofcode.y2022.d3;

import com.djlaser.adventofcode.util.AOCDay;

public class D3 extends AOCDay {
	public static final String PRIORITIES = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void challenge1() {
        String input = readInput(false);
        String[] rucksacks = input.split("\r?\n");
        int score = 0;

        for (String rucksack : rucksacks) {
            String compartment1 = rucksack.substring(0, (rucksack.length() / 2));
            String compartment2 = rucksack.substring((rucksack.length() / 2));

            for (char item: compartment1.toCharArray()) {
                if (compartment2.contains("" + item)){
					score += PRIORITIES.indexOf(item);
                    break;
                }
            }
        }
        System.out.println(score);
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        String[] rucksacks = input.split("\r?\n");
        int score = 0;

        for (int i = 0; i < rucksacks.length; i += 3) {
			String bag1 = rucksacks[i];
			String bag2 = rucksacks[i + 1];
			String bag3 = rucksacks[i + 2];

            for (char item: bag1.toCharArray()) {
                if (bag2.contains("" + item) & bag3.contains("" + item)){
					score += PRIORITIES.indexOf(item);
                    break;
                }
            }
        }
        System.out.println(score);
    }
}
