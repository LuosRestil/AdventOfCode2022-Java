package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) throws FileNotFoundException {
        // Part 1
        Scanner s = new Scanner(new File("src/main/resources/inputs/Day01.txt"));
        List<Integer> elfCalories = new ArrayList<>();
        int currentElfCalories = 0;
        while (s.hasNextLine()){
            String line = s.nextLine();
            if (line.isEmpty()) {
                elfCalories.add(currentElfCalories);
                currentElfCalories = 0;
            } else {
                currentElfCalories += Integer.parseInt(line);
            }
        }
        s.close();

        elfCalories.sort(Collections.reverseOrder());

        int answer = elfCalories.get(0);
        System.out.println("Answer 1: " + answer);

        // Part 2
        answer = elfCalories
                .subList(0, 3)
                .stream()
                .reduce(Integer::sum)
                .orElseThrow();
        System.out.println("Answer 2: " + answer);
    }
}
