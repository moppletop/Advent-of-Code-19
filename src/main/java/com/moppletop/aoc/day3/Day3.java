package com.moppletop.aoc.day3;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day3 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day3();
    }

    private Day3() throws IOException {
        super(3);
    }

    @Override
    public void run() {
        String[] line1 = inputList().get(0).split(","), line2 = inputList().get(1).split(",");

        Wire wire1 = new Wire(line1), wire2 = new Wire(line2);

        Set<Long> intersections = new HashSet<>(wire1.path.keySet());
        intersections.retainAll(wire2.path.keySet());
        intersections.remove(0L);

        int dist = Integer.MAX_VALUE, steps = Integer.MAX_VALUE;

        for (long intersection : intersections) {
            // Part 1
            int x = Math.abs((int) (intersection >> 32));
            int y = Math.abs((int) intersection);
            int xy = x + y;

            if (xy < dist) {
                dist = xy;
            }

            // Part 2
            int stepsSum = wire1.path.get(intersection) + wire2.path.get(intersection);

            if (stepsSum < steps) {
                steps = stepsSum;
            }
        }

        out(dist);
        out(steps);
    }

    private int getStepX(String step) {
        char dir = step.charAt(0);

        if (dir == 'L') {
            return -Integer.parseInt(step.substring(1));
        } else if (dir == 'R') {
            return Integer.parseInt(step.substring(1));
        }

        return 0;
    }

    private int getStepY(String step) {
        char dir = step.charAt(0);

        if (dir == 'D') {
            return -Integer.parseInt(step.substring(1));
        } else if (dir == 'U') {
            return Integer.parseInt(step.substring(1));
        }

        return 0;
    }

    private class Wire {

        String[] steps;
        Map<Long, Integer> path;
        int totalSteps;
        int x, y;

        Wire(String[] steps) {
            this.steps = steps;
            this.path = new HashMap<>(steps.length * 10);

            int dx, dy;

            for (String step : steps) {
                dx = getStepX(step);
                dy = getStepY(step);

                if (dx < 0) {
                    for (int i = x - 1; i >= x + dx; i--) {
                        add(i, y);
                    }
                } else if (dx > 0) {
                    for (int i = x + 1; i <= x + dx; i++) {
                        add(i, y);
                    }
                } else if (dy < 0) {
                    for (int i = y - 1; i >= y + dy; i--) {
                        add(x, i);
                    }
                } else if (dy > 0) {
                    for (int i = y + 1; i <= y + dy; i++) {
                        add(x, i);
                    }
                }

                x += dx;
                y += dy;
            }
        }

        void add(int x, int y) {
            long merged = (long) x << 32 | y & 0xFFFFFFFFL;
            ++totalSteps;
            path.computeIfAbsent(merged, k -> totalSteps);
        }
    }
}
