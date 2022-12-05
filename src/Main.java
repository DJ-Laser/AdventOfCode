import util.AOCYear;
import y2022.Y2022;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static AOCDate defaultDate =
            new AOCDate(2022, 3);

    public static Map<Integer, AOCYear> years = Map.ofEntries(
            makeYear(new Y2022())
    );

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

    private static Map.Entry<Integer, AOCYear> makeYear(AOCYear year) {
        return new AbstractMap.SimpleEntry<>(
                year.getYear(), year
        );
    }

    public static AOCYear getYear(int year) throws IllegalArgumentException{
        if(!years.containsKey(year)){
            throw new IllegalArgumentException("No such year " + year + " in years map");
        }

        return years.get(year);
    }

    public static void runYear(int year, int day, int challenge) {
        getYear(year).runDay(day, challenge);
    }

    private record AOCDate(int year, int day, int challenge, String strYear, String strDay, String strChallenge) {
        public AOCDate(int year, int day, int challenge){
            this(year, day, 1,
                    String.valueOf(year),
                    String.valueOf(day),
                    String.valueOf(challenge));
        }

        public AOCDate(int year, int day){
            this(year, day, 1);
        }
    }
}