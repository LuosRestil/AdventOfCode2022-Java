package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/resources/inputs/Day04.txt"));
        List<String> lines = new ArrayList<>();
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }

        // Part 1
        int fullOverlaps = 0;
        for (String line : lines) {
            String[] elfPair = line.split(",");
            int[] elf1 = Arrays.stream(elfPair[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elf2 = Arrays.stream(elfPair[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if ((elf1[0] >= elf2[0] && elf1[1] <= elf2[1]) || (elf2[0] >= elf1[0] && elf2[1] <= elf1[1])) {
                fullOverlaps++;
            }
        }
        System.out.println("Answer 1: " + fullOverlaps);

        // Part 2
        int totalOverlaps = 0;
        for (String line : lines) {
            String[] elfPair = line.split(",");
            int[] elf1 = Arrays.stream(elfPair[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] elf2 = Arrays.stream(elfPair[1].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] smallerStartElf = elf1[0] <= elf2[0] ? elf1 : elf2;
            int[] greaterStartElf = smallerStartElf == elf1 ? elf2 : elf1;
            if (greaterStartElf[0] <= smallerStartElf[1]) {
                totalOverlaps++;
            }
        }
        System.out.println("Answer 2: " + totalOverlaps);
    }
}
