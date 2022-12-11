package com.djlaser.adventofcode.y2022.d11;

import com.djlaser.adventofcode.util.AOCDay;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class D11 extends AOCDay {
    public record Monkey(Deque<Integer> items, Function<Integer, Integer> operation, Predicate<Integer> test, int trueIndex, int falseIndex) {

        public static Map.Entry<Integer, Monkey> parseBlock(String block) {
            List<String> lines = block.lines().toList();
            int key = 0;
            int divisor = 1;
            int falseMonkey = 0;
            int trueMonkey = 0;
            Deque<Integer> items = new ArrayDeque<>();
            Function<Integer, Integer> operation = (j) -> j;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                switch (i) {
                    case 0 -> key = Integer.parseInt(line.substring(7, 8));
                    case 1 -> {
                        List<Integer> parsedItems = Arrays.stream(
                                line.substring(18).split(", "))
                                .map(Integer::parseInt)
                                .toList();
                        items.addAll(parsedItems);
                    }
                    case 2 -> {
                        String operationString = line.substring(19);

                        if (operationString.equals("old * old")) {
                            operation = (j) -> j * j;
                            break;
                        }

                        final int opValue = Integer.parseInt(operationString.substring(6));
                        switch (operationString.substring(0, 6)) {
                            case "old + " -> operation = (j) -> j + opValue;
                            case "old * " -> operation = (j) -> j * opValue;
                        }
                    }
                    case 3 -> divisor = Integer.parseInt(line.substring(21));
                    case 4 -> trueMonkey = Integer.parseInt(line.substring(29));
                    case 5 -> falseMonkey = Integer.parseInt(line.substring(30));
                }
            }

            final int finalDivisor = divisor;
            return new AbstractMap.SimpleEntry<>(key, new Monkey(items, operation, i -> i % finalDivisor == 0, trueMonkey, falseMonkey));
        }

        public void throwTo(Monkey ifTrue, Monkey ifFalse) {
            Integer item = items.removeFirst();
            item = operation.apply(item);
            item = Math.floorDiv(item, 3);

            if (test().test(item)) {
                ifTrue.giveItem(item);
            } else {
                ifFalse.giveItem(item);
            }
        }

        private void giveItem(Integer item) {
            items.addLast(item);
        }
    }

    @Override
    public void challenge1() {
        String input = readInput(true);
        String[] blocks = input.split("\r?\n\r?\n");
        Map<Integer, Monkey> monkeys = new HashMap<>();
        for (String block : blocks) {
            Map.Entry<Integer, Monkey> entry = Monkey.parseBlock(block);
            monkeys.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void challenge2() {

    }
}
