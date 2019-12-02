package com.moppletop.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public abstract class AOCPuzzle implements Runnable {

    private final List<String> puzzleInput;

    public AOCPuzzle(int day) throws IOException {
        File file = new File("input" + File.separator + day + ".dat");
        this.puzzleInput = Files.readAllLines(file.toPath());

        run();
    }

    protected final List<String> inputList() {
        return puzzleInput;
    }

    protected final String inputFirstLine() {
        return puzzleInput.get(0);
    }

    protected final String[] inputSplit(String delimiter) {
        return puzzleInput.get(0).split(delimiter);
    }

    protected final String inputJoined(String delimiter) {
        return String.join(delimiter, puzzleInput);
    }

    protected final void out(Object message) {
        System.out.println(message);
    }

}
