package y2022.d7;

import java.util.*;

import org.jetbrains.annotations.Nullable;
import util.AOCDay;

public class D7 extends AOCDay {

    public abstract class Node implements Comparable<Node> {
        protected String name;
        protected DirectoryTree parent;
        public abstract int getFileSize();
        public abstract Map<String, Node> getChildren();

        @Nullable
        public DirectoryTree getParent() {
            return parent;
        }

        public String getName() {
            return name;
        }

        public int compareTo(Node o){
            return Integer.compare(this.getFileSize(), o.getFileSize());
        }
    }

    public class DirectoryTree extends Node {
        DirectoryTree(String name) {
            this.name = name;
        }

        public void addNode(Node node) {
            node.parent = this;
            items.put(node.name, node);
        }

        private final Map<String, Node> items = new HashMap<>();

        @Override
        public int getFileSize() {
            return items.values().stream().mapToInt(Node::getFileSize).sum();
        }

        @Override
        public Map<String, Node> getChildren() {
            return items;
        }
    }

    public class File extends Node {
        private final int fileSize;

        File(int fileSize, String name) {
            this.name = name;
            this.fileSize = fileSize;
        }

        @Override
        public int getFileSize() {
            return fileSize;
        }

        @Override
        public Map<String, Node> getChildren() {
            return null;
        }
    }

    public DirectoryTree parseFileSystem(String input) {
        List<String> lines = input.lines().toList();
        DirectoryTree fileDrive = new DirectoryTree("ElfDrive:");
        fileDrive.addNode(new DirectoryTree("/"));
        Deque<String> dirStack = new ArrayDeque<>(List.of("/"));

        for (int i = 0; i < lines.size(); ) {
            String line = lines.get(i);

            if (line.contains("$ cd")) {
                String[] params = line.split(" ");

                switch (params[2]){
                    case ("..") -> dirStack.pop();
                    case ("/") -> dirStack = new ArrayDeque<>(List.of("/"));
                    default -> dirStack.push(params[2]);
                }
                i++;
            } else {
                for(i += 1; i < lines.size() && !lines.get(i).contains("$"); i++) {
                    line = lines.get(i);

                    String[] fileProps = line.split(" ");
                    DirectoryTree currentDir = fileDrive;

                    for (Iterator<String> it = dirStack.descendingIterator(); it.hasNext(); ) {
                        String folder = it.next();
                        currentDir = (DirectoryTree) currentDir.getChildren().get(folder);
                    }

                    if (fileProps[0].equals("dir")) {
                        currentDir.addNode(new DirectoryTree(fileProps[1]));
                    } else {
                        currentDir.addNode(new File(Integer.parseInt(fileProps[0]), fileProps[1]));
                    }
                }
            }
        }
        return fileDrive;
    }

    @Override
    public void challenge1() {
        String input = readInput(false);
        DirectoryTree fileDrive = parseFileSystem(input);
        int totalSize = pt1fileTotal(fileDrive);
        System.out.println(totalSize);
    }

    public int pt1fileTotal(DirectoryTree fileDrive) {
        int size = 0;
        for (Node node:fileDrive.getChildren().values()) {
            int currentSize = node.getFileSize();
            if (node instanceof DirectoryTree) {
                if (currentSize > 100000){
                    currentSize = 0;
                }
                size += currentSize + pt1fileTotal((DirectoryTree) node);
            }
        }
        return size;
    }

    public DirectoryTree pt2findDirectory(DirectoryTree fileDrive, int targetSize) {
        DirectoryTree bestDirectory = new DirectoryTree("EMPTY_DIRTREE");
        for (Node node:fileDrive.getChildren().values()) {
            if (node instanceof DirectoryTree tree) {
                int currentSize = tree.getFileSize();
                int bestSize = bestDirectory.getFileSize();

                DirectoryTree returnedDirectory = pt2findDirectory(tree, targetSize);
                int returnedSize = returnedDirectory.getFileSize();
               if (bestSize == 0 | (returnedSize != 0 && returnedSize < bestSize)){
                    bestDirectory = returnedDirectory;
                }
                bestSize = bestDirectory.getFileSize();
                if (currentSize >= targetSize) {

                    if (bestSize == 0 | currentSize < bestSize) {
                        bestDirectory = tree;
                    }
                }
            }
        }
        return bestDirectory;
    }

    @Override
    public void challenge2() {
        String input = readInput(false);
        DirectoryTree fileDrive = parseFileSystem(input);
        int totalDiskSpace = 70000000;
        int requiredSpace = 30000000;
        int totalFileSize = fileDrive.getFileSize();
        int unusedSpace = totalDiskSpace - totalFileSize;
        int targetDeletionAmt = requiredSpace - unusedSpace;
        DirectoryTree bestDir = pt2findDirectory(fileDrive, targetDeletionAmt);
        System.out.println(bestDir.getFileSize());
    }
}
