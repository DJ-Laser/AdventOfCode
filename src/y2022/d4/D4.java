package y2022.d4;

import java.util.Arrays;
import java.util.List;

import util.AOCDay;

public class D4 extends AOCDay {

    @Override
    public void challenge1() {
        String input = readInput(false);
        String[] pairs = input.split("\r?\n");
        int score = 0;

        for (String pair : pairs) {
            List<int[]> sectionIds = Arrays.stream(pair.split(","))
                .map(s ->
                    Arrays.stream(s.split("-"))
                    .mapToInt(Integer::parseInt).toArray()
                )
                .toList();
            int[] section1 = sectionIds.get(0);
            int[] section2 = sectionIds.get(1);

            if(section1[0] <= section2[0] & section1[1] >= section2[1]) {
                score ++;
            } else if(section1[0] >= section2[0] & section1[1] <= section2[1]) {
                score++;
            }
        }
        System.out.println(score);
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        String[] pairs = input.split("\r?\n");
        int score = 0;

        for (String pair : pairs) {
            List<int[]> sectionIds = Arrays.stream(pair.split(","))
                    .map(s ->
                            Arrays.stream(s.split("-"))
                                    .mapToInt(Integer::parseInt).toArray()
                    )
                    .toList();
            int[] section1 = sectionIds.get(0);
            int[] section2 = sectionIds.get(1);

            System.out.print(Arrays.toString(section1) + Arrays.toString(section2));

            if(section1[1] >= section2[0] & section1[0] <= section2[1]) {
                score ++;
                System.out.print(" overlap");
            }
            System.out.println();
        }
        System.out.println(score);
    }
}
