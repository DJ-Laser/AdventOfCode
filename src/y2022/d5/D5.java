package y2022.d5;

import util.AOCDay;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class D5 extends AOCDay {
    @Override
    public void challenge1() {
        String input = readInput(false);
        String[] lines = input.split("\r?\n");
        List<Deque<Character>> containerStacks = new ArrayList<>();
        List<String> containerLines = new ArrayList<>();
        boolean doMovements = false;

        for (String line:lines) {
            if(doMovements){
                parseMovement(line, containerStacks, false);
            } else if (line.equals("")) {
                doMovements = true;
            } else if(line.contains("[")){
                containerLines.add(line);
            } else {
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != ' '){
                        containerStacks.add(new ArrayDeque<>());
                    }
                }

                for (String container : containerLines) {
                    parseContainerLine(container, containerStacks);
                }
            }
        }
        for (Deque<Character> stack : containerStacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }

    private void parseMovement(String line, List<Deque<Character>> containerStacks, boolean chal2) {
        int[] movementParams = line.chars()
            .filter(Character::isDigit)
            .map(i -> Integer.parseInt("" + (char) i))
            .toArray();
        if (movementParams.length  == 4) {
            movementParams[0] = Integer.parseInt("" + movementParams[0] + movementParams[1]);
            movementParams[1] = movementParams[2];
            movementParams[2] = movementParams[3];
        }
        Deque<Character> from = containerStacks.get(movementParams[1] - 1);
        Deque<Character> to = containerStacks.get(movementParams[2] - 1);
        
        if (!chal2) {
            for (int i = 0; i < movementParams[0]; i++) {
                to.push(from.pop());
            }
        } else {
            Character[] containers = new Character[movementParams[0]];
            for (int i = 0; i < movementParams[0]; i++) {
                containers[i] = from.pop();
            }
            for (int i = containers.length - 1; i > -1; i--) {
                to.push(containers[i]);
            }
        }
    }

    private void parseContainerLine(String line, List<Deque<Character>> containerStacks) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i += 4) {
            int j = i / 4;
            if (chars[i] == '['){
                containerStacks.get(j).addLast(chars[i + 1]);
            }
        }
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        String[] lines = input.split("\r?\n");
        List<Deque<Character>> containerStacks = new ArrayList<>();
        List<String> containerLines = new ArrayList<>();
        boolean doMovements = false;

        for (String line:lines) {
            if(doMovements){
                parseMovement(line, containerStacks, true);
            } else if (line.equals("")) {
                doMovements = true;
            } else if(line.contains("[")){
                containerLines.add(line);
            } else {
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != ' '){
                        containerStacks.add(new ArrayDeque<>());
                    }
                }

                for (String container : containerLines) {
                    parseContainerLine(container, containerStacks);
                }
            }
        }
        for (Deque<Character> stack : containerStacks) {
            System.out.print(stack.peek());
        }
        System.out.println();
    }
}
