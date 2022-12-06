package y2022.d2;

import util.AOCDay;

import java.util.Arrays;
import java.util.List;

public class D2 extends AOCDay {
    public enum RPS {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        public final int points;

        RPS(int points) {
            this.points = points;
        }

        public enum WinState {
            WIN(6),
            DRAW(3),
            LOSS(0);
            public final int points;

            WinState(int points) {
                this.points = points;
            }

            public WinState getOpposite() {
                return switch (this){
                    case WIN -> LOSS;
                    case DRAW -> DRAW;
                    case LOSS -> WIN;
                };
            }

            public static WinState getFromChar(char character) {
                return switch (character){
                    case 'X' -> LOSS;
                    case 'Y' -> DRAW;
                    case 'Z' -> WIN;
                    default -> throw new IllegalStateException("Character must be one of: X,Y,Z");
                };
            }
        }

        public static RPS getFromChar(char character) {
            return switch (character) {
                case 'X','A' -> ROCK;
                case 'Y','B' -> PAPER;
                case 'Z','C' -> SCISSORS;
                default -> throw new IllegalArgumentException("Character must be one of: A,B,C,X,Y,Z");
            };
        }

        public int getPointsForCompetitor(WinState winState) {
            return getCompetitorForWinState(winState).points;
        }

        public RPS getCompetitorForWinState(WinState winState) {
            return switch (this) {
                case ROCK -> switch (winState) {
                    case WIN -> SCISSORS;
                    case DRAW -> ROCK;
                    case LOSS -> PAPER;
                };
                case PAPER -> switch (winState) {
                    case WIN -> ROCK;
                    case DRAW -> PAPER;
                    case LOSS -> SCISSORS;
                };
                case SCISSORS -> switch (winState) {
                    case WIN -> PAPER;
                    case DRAW -> SCISSORS;
                    case LOSS -> ROCK;
                };
            };
        }

        public int getPointsAgainst(RPS competitor){
            return getWinStateAgainst(competitor).points;
        }

        public WinState getWinStateAgainst(RPS competitor){
            return switch (this) {
                case ROCK -> switch (competitor) {

                    case ROCK -> WinState.DRAW;
                    case PAPER -> WinState.LOSS;
                    case SCISSORS -> WinState.WIN;
                };

                case PAPER -> switch (competitor) {

                    case ROCK -> WinState.WIN;
                    case PAPER -> WinState.DRAW;
                    case SCISSORS -> WinState.LOSS;
                };

                case SCISSORS -> switch (competitor) {

                    case ROCK -> WinState.LOSS;
                    case PAPER -> WinState.WIN;
                    case SCISSORS -> WinState.DRAW;
                };

            };
        }
    }

    @Override
    public void challenge1() {
        String input = readInput(false);
        List<String> instructions =
                Arrays.stream(input.split("\r?\n")).map(s -> s.replaceAll(" ", "")).toList();
        int score = 0;
        for (String instruction:instructions) {
            int currentScore = 0;
            RPS myChoice = RPS.getFromChar(instruction.charAt(1));
            RPS yourChoice = RPS.getFromChar(instruction.charAt(0));
            currentScore += myChoice.points;
            currentScore += myChoice.getPointsAgainst(yourChoice);
            score += currentScore;
        }
        System.out.println(score);
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        List<String> instructions =
                Arrays.stream(input.split("\r?\n")).map(s -> s.replaceAll(" ", "")).toList();
        int score = 0;
        for (String instruction:instructions) {
            int currentScore = 0;
            RPS yourChoice = RPS.getFromChar(instruction.charAt(0));
            RPS.WinState myState = RPS.WinState.getFromChar(instruction.charAt(1));
            currentScore += yourChoice.getPointsForCompetitor(myState.getOpposite());
            currentScore += myState.points;
            score += currentScore;
        }
        System.out.println(score);
    }
}
