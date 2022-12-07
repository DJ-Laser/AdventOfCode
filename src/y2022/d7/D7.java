package y2022.d7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AOCDay;

public class D7 extends AOCDay{

    @Override
    public void challenge1() {
        String input = readInput(true);
        String[] lines = (String[]) input.lines().toArray();
        Map<String, Object> fileDrive = new HashMap<>(Map.of("/", new HashMap<>()));
        Deque<String> dirStack = new ArrayDeque<>(List.of("/"));

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.contains("$")) {
                String[] params = line.split(" ");
                switch (params[1]){
                    case ("..") -> {
                        dirStack.pop();
                    }
                    case ("/") -> {
                        dirStack = new ArrayDeque<>(List.of("/"));
                    }
                    default -> {
                        dirStack.push(params[2]);
                    }
                }
                } else {
                    
                }
            }
        }

    @Override
    public void challenge2() {
        
    }
    
}
