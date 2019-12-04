package com.moppletop.aoc.day4;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;
import java.util.stream.IntStream;

public class Day4 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day4();
    }

    public Day4() throws IOException {
        super(4);
    }

    @Override
    public void run() {
        String[] split = inputSplit("-");
        int min = Integer.parseInt(split[0]), max = Integer.parseInt(split[1]);

        out(IntStream.rangeClosed(min, max)
                .filter(value -> isPossiblePassword(value, false))
                .count());

        out(IntStream.rangeClosed(min, max)
                .filter(value -> isPossiblePassword(value, true))
                .count());
    }

    private boolean isPossiblePassword(int num, boolean strictDoubles) {
        int[] digits = new int[6];
        int i = 6;

        while (num > 0) {
            digits[--i] = num % 10;
            num /= 10;
        }

        boolean ignore = false;
        boolean doubles = false;

        for (int j = 1; j < digits.length; j++) {
            int current = digits[j];
            int last = digits[j - 1];

            // Keep ignoring if the digit is the same as the last one
            if (ignore) {
                if (current == last) {
                    continue;
                }

                ignore = false;
            }

            if (current == last) {
                if (strictDoubles && j < digits.length - 1) {
                    int next = digits[j + 1];

                    if (current == next) {
                        ignore = true;
                        continue;
                    } else {
                        doubles = true;
                    }
                } else {
                    doubles = true;
                }
            }

            if (current < last) {
                return false;
            }
        }

        return doubles;
    }
}
