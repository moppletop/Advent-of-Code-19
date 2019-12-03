package com.moppletop.aoc.day1;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;

public class Day1 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day1();
    }

    private Day1() throws IOException {
        super(1);
    }

    @Override
    public void run() {
        // Part 1
        int part_1 = inputList().stream()
                .mapToInt(Integer::parseInt)
                .map(this::calcFuel)
                .sum();

        out(part_1);

        // Part 2
        int part_2 = inputList().stream()
                .mapToInt(Integer::parseInt)
                .map(this::calcFuelRecursive)
                .sum();

        out(part_2);
    }

    private int calcFuel(int module) {
        return Math.floorDiv(module, 3) - 2;
    }

    private int calcFuelRecursive(int module) {
        int fuel = calcFuel(module);

        if (fuel < 0) {
            return 0;
        }

        return fuel + calcFuelRecursive(fuel);
    }
}
