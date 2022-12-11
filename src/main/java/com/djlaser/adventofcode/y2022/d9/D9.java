package com.djlaser.adventofcode.y2022.d9;

import com.djlaser.adventofcode.util.AOCDay;

import java.util.*;

public class D9 extends AOCDay {
    public enum Direction {
        Up("U", 0, 1),
        Down("D", 0, -1),
        Left("L", -1, 0),
        Right("R", 1, 0);

        public final String name;
        public final int x;
        public final int y;

        private static Direction getFromString(String name) throws IllegalArgumentException {
            for (Direction dir:Direction.values()) {
                if (dir.name.equals(name)) {
                    return dir;
                }
            }

            throw new IllegalArgumentException("Invalid name. Names are: U, D, L, R");
        }

        Direction(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    public static class Position implements Cloneable {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Position clone() {
            Position o;
            try {
                o = (Position) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            o.x = this.x;
            o.y = this.y;
            return o;
        }

        public void move(Direction dir, int amt) {
            this.move(dir.x * amt, dir.y * amt);
        }

        public void move(int x, int y) {
            this.x += x;
            this.y += y;
        }

        public boolean isAdjacentTo(Position otherPos) {
            int xDist = Math.abs(this.x - otherPos.x);
            int yDist = Math.abs(this.y - otherPos.y);
            return xDist <= 1 && yDist <= 1;
        }

        public boolean isNotAdjacentTo(Position otherPos) {
            return !isAdjacentTo(otherPos);
        }

        public boolean isPosInDir(Direction dir, Position otherPos) {
            int xComp = Integer.compare(otherPos.x, this.x);
            int yComp = Integer.compare(otherPos.y, this.y);
            return ((xComp == dir.x || dir.x == 0) && (yComp == dir.y || dir.y == 0));
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Position pos) {
                return x == pos.x && y == pos.y;
            }
            return false;
        }
    }

    @Override
    public void challenge1() {
        String input = readInput();
        List<String> lines = input.lines().toList();
        Position head = new Position(0, 0);
        Position tail = new Position(0, 0);
        Set<Position> tailPositions = new HashSet<>(Set.of(tail.clone()));

        for (String line: lines) {
            String[] args = line.split(" ");
            String dirName = args[0];
            int amt = Integer.parseInt(args[1]);
            Direction headDir = Direction.getFromString(dirName);

            for(int i = 0; i < amt; i++){
                head.move(headDir, 1);

                if(tail.isNotAdjacentTo(head)) {
                    for (Direction dir : Direction.values()) {
                        if (tail.isPosInDir(dir, head)) {
                            tail.move(dir, 1);
                        }
                    }
                }
                tailPositions.add(tail.clone());
            }
        }
        System.out.println(tailPositions.size());
    }

    @Override
    public void challenge2() {
        String input = readInput();
        List<String> lines = input.lines().toList();
        Position[] rope = new Position[10];
        Set<Position> tailPositions = new HashSet<>(Set.of(new Position(0, 0)));

        for (int i = 0; i < rope.length; i++) {
            rope[i] = new Position(0, 0);
        }

        for (String line: lines) {
            String[] args = line.split(" ");
            String dirName = args[0];
            int amt = Integer.parseInt(args[1]);
            Direction headDir = Direction.getFromString(dirName);

            for(int i = 0; i < amt; i++){
                rope[0].move(headDir, 1);

                tailPositions.add(calculateTailMotions(rope, 0));
            }
        }
        System.out.println(tailPositions.size());
    }

    public Position calculateTailMotions(Position[] rope, int headIndex) {
        Position head = rope[headIndex];
        Position tail = rope[headIndex + 1];

        if(tail.isNotAdjacentTo(head)) {
            for (Direction dir : Direction.values()) {
                if (tail.isPosInDir(dir, head)) {
                    tail.move(dir, 1);
                }
            }
        }

        if (headIndex + 2 == rope.length) {
            return tail.clone();
        } else {
            return calculateTailMotions(rope, headIndex + 1);
        }
    }

    public void printTailGraph(Set<Position> tailPositions, Position[] rope) {
        int width = 0;
        int height = 0;

        int xoff = 0;
        int yoff = 0;

        List<Position> allPosses = new ArrayList<>(Arrays.stream(rope).toList());
        allPosses.addAll(tailPositions);

        for (Position pos : allPosses) {
            width = Math.max(pos.x, width);
            height = Math.max(pos.y, height);

            if (pos.x < 0) {
                xoff = Math.max(Math.abs(pos.x), xoff);
            }

            if (pos.y < 0) {
                yoff = Math.max(Math.abs(pos.y), yoff);
            }
        }

        String[][] array = new String[height + 1 + yoff][width + 1 + xoff];

        for (Position pos : tailPositions) {
            array[pos.y + yoff][pos.x + xoff] = "#";
        }

        for (int i = rope.length - 1; i >= 0; i--) {
            Position pos = rope[i];
            array[pos.y + yoff][pos.x + xoff] = Integer.toString(i);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            System.out.println(Arrays.stream(array[i])
                    .map(s -> Objects.requireNonNullElse(s, "."))
                    .reduce("", (s1, s2) -> s1 + s2)
            );
        }
        System.out.println();
    }
}
