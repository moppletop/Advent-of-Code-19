package com.moppletop.aoc.day6;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;
import java.util.*;

public class Day6 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day6();
    }

    public Day6() throws IOException {
        super(6);
    }

    private Map<String, List<String>> orbits;
    private Map<String, String> reverseOrbits;

    @Override
    public void run() {
        // Key -> Value COM -> Orbiting
        orbits = new HashMap<>(inputList().size());
        // Key -> Value Mass -> COM
        reverseOrbits = new HashMap<>(inputList().size());

        for (String line : inputList()) {
            String[] split = line.split("\\)");
            orbits.computeIfAbsent(split[0], s -> new ArrayList<>()).add(split[1]);
            reverseOrbits.put(split[1], split[0]);
        }

        out(orbits);

        // Part 1
        out(getNumberOfOrbits("COM", 1));

        // Part 2
        List<String> yourPath = getPathToCOM("YOU");
        List<String> santaPath = getPathToCOM("SAN");
        int santaIndex;

        for (int i = 0; i < yourPath.size(); i++) {
            String mass = yourPath.get(i);
            santaIndex = santaPath.indexOf(mass);

            if (santaIndex != -1) {
                out(i + santaIndex);
                break;
            }
        }
    }

    private int getNumberOfOrbits(String center, int indirectOrbits) {
        List<String> centerOrbits = orbits.get(center);

        if (centerOrbits == null) {
            return 0;
        }

        int number = 0;

        for (String centerOrbit : centerOrbits) {
            number += indirectOrbits + getNumberOfOrbits(centerOrbit, indirectOrbits + 1);
        }

        return number;
    }

    private List<String> getPathToCOM(String start) {
        String current = start;
        List<String> path = new LinkedList<>();

        while (!(current = reverseOrbits.get(current)).equals("COM")) {
            path.add(current);
        }

        return path;
    }
}
