package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        // Part 1
        Scanner s = new Scanner(new File("src/main/resources/inputs/Day02.txt"));
        int score = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            char opponentMove = line.charAt(0);
            char myMove = (char) (line.charAt(2) - 23);
            score += calculateMatchScore1(myMove, opponentMove);
        }
        System.out.println("Answer 1: " + score);

        // Part 2
        s = new Scanner(new File("src/main/resources/inputs/Day02.txt"));
        score = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            char opponentMove = line.charAt(0);
            char requiredResult = line.charAt(2);
            score += calculateMatchScore2(opponentMove, requiredResult);
        }
        System.out.println("Answer 2: " + score);
    }

    public static int calculateMatchScore1(char myMove, char opponentMove) {
        Map<Character, Character> beats = Map.of(
                'C', 'B',
                'B', 'A',
                'A', 'C'
        );
        int moveScore = myMove - 64;
        int resultScore = 0;
        if (myMove == opponentMove) {
            resultScore += 3;
        } else if (beats.get(myMove) == opponentMove) {
            resultScore += 6;
        }
        return moveScore + resultScore;
    }

    public static int calculateMatchScore2(char opponentMove, char requiredResult) {
        int resultScore = (requiredResult - 'X') * 3;
        Map<Character, Map<Character, Character>> requiredMoves = Map.of(
                'A', Map.of(
                        'X', 'C',
                        'Y', 'A',
                        'Z', 'B'
                ),
                'B', Map.of(
                        'X', 'A',
                        'Y', 'B',
                        'Z', 'C'
                ),
                'C', Map.of(
                        'X', 'B',
                        'Y', 'C',
                        'Z', 'A'
                )
        );
        char requiredMove = requiredMoves.get(opponentMove).get(requiredResult);
//        // Alternative approach that could allow for more than three options
//        int possibleMoves = 3; // could be passed in instead of defined here
//        char[] moves = new char[possibleMoves];
//        for (int i = 0; i < possibleMoves; i++) {
//            moves[i] = (char) ('A' + i);
//        }
//        int indexOfOpponentMove = opponentMove - 'A';
//        int requiredMoveOffset = requiredResult - 'X' - 1;
//        int indexOfRequiredMove = indexOfOpponentMove + requiredMoveOffset;
//        if (indexOfRequiredMove < 0) indexOfRequiredMove = moves.length - 1;
//        else if (indexOfRequiredMove ==  moves.length) indexOfRequiredMove = 0;
//        char requiredMove = moves[indexOfRequiredMove];
        int moveScore = requiredMove - 64;
        return resultScore + moveScore;
    }
}
