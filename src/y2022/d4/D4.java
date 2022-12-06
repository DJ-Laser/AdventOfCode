package y2022.d4;

import java.util.Arrays;
import java.util.List;

import util.AOCDay;

public class D4 extends AOCDay {

    @Override
    public void challenge1() {
        String input = readInput(true);
        String[] pairs = input.split("\r?\n");

        for (String pair : pairs) {
            List<int[]> sectionIds = Arrays.stream(pair.split(", "))
                .map(s ->
                    Arrays.stream(s.split("-"))
                    .mapToInt(ss -> Integer.parseInt(ss)).toArray()
                )
                .toList();
            int[] section1 = sectionIds.get(0);
            int[] section2 = sectionIds.get(1);
            int score = 0;

            if(section1[0] < section2[0] & section1[1] > section2[1]){
                score ++;
            }

            if(section1[0] < section2[0] & section1[1] > section2[1]){
                score++;
            }
            System.out.println(score);
        }
    }

    @Override
    public void challenge2() {
              
    }
    
}
