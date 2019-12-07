package com.moppletop.aoc.day5;

import com.moppletop.aoc.AOCPuzzle;

import java.io.IOException;
import java.util.Arrays;

public class Day5 extends AOCPuzzle {

    public static void main(String[] args) throws IOException {
        new Day5();
    }

    private Day5() throws IOException {
        super(5);
    }

    @Override
    public void run() {
        int[] numbers = Arrays.stream(inputSplit(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        run(numbers);
    }

    private void run(int[] numbers) {
        int pos = 0;
        int instruct;
        int opcode;
        int[] params;

        while (true) {
            instruct = numbers[pos];
            opcode = instruct % 100;

            if (opcode == 99) {
                return;
            }

            switch (opcode) {
                case 1:
                    params = getParams(numbers, pos, instruct, 3);
                    numbers[params[2]] = numbers[params[0]] + numbers[params[1]];
                    pos += 4;
                    break;
                case 2:
                    params = getParams(numbers, pos, instruct, 3);
                    numbers[params[2]] = numbers[params[0]] * numbers[params[1]];
                    pos += 4;
                    break;
                case 3:
                    numbers[numbers[pos + 1]] = 5;
                    pos += 2;
                    break;
                case 4:
                    params = getParams(numbers, pos, instruct, 1);
                    out(numbers[params[0]]);
                    pos += 2;
                    break;
                case 5:
                    params = getParams(numbers, pos, instruct, 2);

                    if (numbers[params[0]] != 0) {
                        pos = numbers[params[1]];
                    } else {
                        pos += 3;
                    }
                    break;
                case 6:
                    params = getParams(numbers, pos, instruct, 2);

                    if (numbers[params[0]] == 0) {
                        pos = numbers[params[1]];
                    } else {
                        pos += 3;
                    }
                    break;
                case 7:
                    params = getParams(numbers, pos, instruct, 3);
                    numbers[params[2]] = (numbers[params[0]] < numbers[params[1]]) ? 1 : 0;
                    pos += 4;
                    break;
                case 8:
                    params = getParams(numbers, pos, instruct, 3);
                    numbers[params[2]] = (numbers[params[0]] == numbers[params[1]]) ? 1 : 0;
                    pos += 4;
                    break;
                default:
                    System.err.println("Invalid opcode " + opcode);
                    return;
            }
        }
    }

    private int[] getParams(int[] numbers, int pos, int instruct, int paramsToGet) {
        instruct /= 100; // remove last 2 digits
        pos++; // don't include the opcode & param modes

        int[] modes = new int[paramsToGet];
        int i = 0;

        while (instruct > 0 && i < modes.length) {
            modes[i++] = instruct % 10;
            instruct /= 10;
        }

        i = 0;

        int[] params = new int[paramsToGet];
        int val;

        for (int mode : modes) {
            if (mode == 0) {
                val = numbers[pos + i];
            } else if (mode == 1) {
                val = pos + i;
            } else {
                System.err.println("Invalid mode " + mode);
                return null;
            }

            params[i++] = val;
        }

        return params;
    }
}
