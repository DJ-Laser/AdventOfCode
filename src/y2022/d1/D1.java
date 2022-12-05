package y2022.d1;

import util.AOCDay;


import java.util.*;

public class D1 extends AOCDay {

    @Override
    public void challenge1() {
        String input = readInput(false);
        String[] groups = input.split("\r\n\r\n");
        int maxValue = 0;

        for (String group: groups) {
            int totalValue = Arrays.stream(group.split("\r\n")).mapToInt(Integer::parseInt).sum();
            if (totalValue > maxValue){
                maxValue = totalValue;
            }
        }
        System.out.println(maxValue);
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        String[] groups = input.split("\r\n\r\n");

        ArrayList<Integer> maxValues = new ArrayList<>(Collections.singletonList(0));

        for (String group: groups) {
            Integer totalValue = Arrays.stream(group.split("\r\n")).mapToInt(Integer::parseInt).sum();

            for(int i = 0; i < maxValues.size(); i++){
                if(totalValue > maxValues.get(i)){
                    maxValues.add(totalValue);

                    Collections.sort(maxValues);
                    Collections.reverse(maxValues);

                    if (maxValues.size() > 3){
                        maxValues.remove(maxValues.size()-1);
                    }
                    break;
                }
            }
        }
        System.out.println(maxValues.stream().mapToInt(i -> i).sum());
    }
}
