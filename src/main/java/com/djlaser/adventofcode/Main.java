package com.djlaser.adventofcode;

import com.djlaser.adventofcode.util.AOCYear;

import java.util.*;

public class Main {

    public static AOCDate defaultDate =
            new AOCDate(2022, 11);

    private static int tryGetArg(String[] args, int index, int defaultInt) {
        int arg = defaultInt;
        try {
            arg = Integer.parseInt(Objects.requireNonNull(args[index]));
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException ignored) {
        }
        return arg;
    }

    public static void main(String[] args) {
        int year = tryGetArg(args, 2, defaultDate.year());
        int day = tryGetArg(args, 1, defaultDate.day());
        int challenge = tryGetArg(args, 0, defaultDate.challenge());
        runYear(year, day, challenge);
    }

    public static AOCYear getYear(int year) {
        return new AOCYear(year);
    }

    public static void runYear(int year, int day, int challenge) {
        getYear(year).runDay(day, challenge);
    }

    private record AOCDate(int year, int day, int challenge, String strYear, String strDay, String strChallenge) {
        public AOCDate(int year, int day, int challenge) {
            this(year, day, 1,
                    String.valueOf(year),
                    String.valueOf(day),
                    String.valueOf(challenge));
        }

        public AOCDate(int year, int day) {
            this(year, day, 1);
        }
    }
}