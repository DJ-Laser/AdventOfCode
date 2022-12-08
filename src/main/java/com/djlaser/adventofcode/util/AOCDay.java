package com.djlaser.adventofcode.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AOCDay {

    public void runChallenge(int challenge){
        if(challenge > 1){
            challenge2();
        } else {
            challenge1();
        }
    }

    protected String readInput(boolean loadExample) {
        URL filePath = this.getClass().getResource(loadExample ? "example.txt" : "input.txt");
        try {
            assert filePath != null;
            return new String(Files.readAllBytes(Paths.get(filePath.toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void challenge1();
    public abstract void challenge2();
}

