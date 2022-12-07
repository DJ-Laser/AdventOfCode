package y2022.d6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.AOCDay;

public class D6 extends AOCDay {

    @Override
    public void challenge1() {
        String input = readInput(false);
        System.out.println(findPacket(input, 4));
    }

    private int findPacket(String input, int numChars) {
        char[] dataBuffer = input.toCharArray();
        for (int i = numChars; i < dataBuffer.length; i++) {
            List<Character> lastChars = new ArrayList<>();
            boolean match = false;
            for (char c:Arrays.copyOfRange(dataBuffer, i - numChars, i)) {
                if (lastChars.contains(c)) {
                    match = true;
                    break;
                } else {
                    lastChars.add(c);
                }
            }
            if (!match) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        System.out.println(findPacket(input, 14));
    }
    
}
