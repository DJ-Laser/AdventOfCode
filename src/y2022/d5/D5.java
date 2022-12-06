package y2022.d5;

import util.AOCDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class D5 extends AOCDay {
    @Override
    public void challenge1() {
        String input = readInput(true);
        String[] lines = input.split("\r?\n");
        List<Stack<Character>> containerStacks = new ArrayList<>();
        List<String> instructions = new ArrayList<>();
        boolean writeInstructions = false;

        for (String line:lines) {
            if(writeInstructions){
                instructions.add(line);
                continue;
            } else if (line.equals("")) {
                writeInstructions = true;
                continue;
            }

            if(line.contains("[")){
                parseContainerLine(line, containerStacks);
            }
        }
    }

    private void parseContainerLine(String line, List<Stack<Character>> containerStacks) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i += 0) {
            char c = chars[i];
            if (c == '[') {
                i++;

            } else {
                i += 4;
            }
        }
    }

    @Override
    public void challenge2() {

    }
}
