package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day03 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("src/main/resources/inputs/Day03.txt"));
        List<String> sacks = new ArrayList<>();
        while (s.hasNextLine()) {
            sacks.add(s.nextLine());
        }

        // Part 1
        int priorityTotal = 0;
        for (String sack : sacks) {
            String compartment1 = sack.substring(0, sack.length() / 2);
            String compartment2 = sack.substring(sack.length() / 2);
            Set<Character> comp1Unique = compartment1.chars()
                    .mapToObj(e -> (char) e).collect(Collectors.toSet());
            for (char c : compartment2.toCharArray()) {
                if (comp1Unique.contains(c)) {
                    priorityTotal += getPriority(c);
                    break;
                }
            }
        }
        System.out.println("Answer 1: " + priorityTotal);

        // Part 2
        priorityTotal = 0;
        String[] trio = new String[3];
        for (int i = 0; i < sacks.size(); i++) {
            trio[i % 3] = (sacks.get(i));
            if (i % 3 == 2) {
                priorityTotal += getPriority(findBadgeType(trio));
                trio = new String[3];
            }
        }
        System.out.println("Answer 2: " + priorityTotal);
    }

    public static int getPriority(char c) {
        return c > 96 ? c - 96 : c - 38;
    }

    public static char findBadgeType(String[] trio) {
        Set<Character> commonItems = trio[0].chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        for (String elf : trio) {
            Set<Character> items = elf.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            commonItems.retainAll(items);
        }
        return new ArrayList<>(commonItems).get(0);
    }
}
