package com.moppletop.aoc.day2;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;
import java.util.Arrays;

public class Day2 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day2();
    }

    private Day2() throws IOException {
        super(2);
    }

    @Override
    public void run() {
        int[] numbers = Arrays.stream(inputSplit(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        out(run(numbers.clone(), 12, 2));

        int target = 19690720;

        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                if (run(numbers.clone(), noun, verb) == target) {
                    out(100 * noun + verb);
                    return;
                }
            }
        }
    }

    private int run(int[] numbers, int noun, int verb) {
        numbers[1] = noun;
        numbers[2] = verb;

        int pos = 0;
        int opcode;

        while ((opcode = numbers[pos]) != 99) {

            int valA = numbers[numbers[pos + 1]];
            int valB = numbers[numbers[pos + 2]];
            int posR = numbers[pos + 3];

            if (opcode == 1) {
                numbers[posR] = valA + valB;
            } else if (opcode == 2) {
                numbers[posR] = valA * valB;
            }

            pos += 4;
        }

        return numbers[0];
    }
}
