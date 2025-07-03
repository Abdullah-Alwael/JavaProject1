import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    final static int xSign = 10; // this number will represent the x sign
    final static int oSign = 20; // this number will represent the O sign

    public static void main(String[] args) {
/*
        * Rules & Guidelines
          • should use all java basics concept:
          * {Variables}, {Scanner}, {Conditions}, {Loops}, {Arrays}, {Methods}, exception handling, switch.
 */

//        Minimum Requirements
//        1- Use 2D Array.
        int[][] gameBoard = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}}; // the numbers represent the position number to ask the user

        Scanner input = new Scanner(System.in);
        int min, max; // for random number range
        int round; // for the current round being played
        boolean won; // checking if there is a win case
        int whoWon; // store the number of the sign of the winner
        int userSign; // the sign is either 10 = X or 20 = O not zero but oh
        int computerSign; // the sign is either 10 = X or 20 = O not zero but oh
        int userPoints, computerPoints;

//        3- Should Display a Game Board.
        printHeaderFooter("Welcome to the Tic-tac-toe game!");
        printGameRules();
        printBoard(gameBoard);
//        do {

//        4- Ask player position.
//        5- Check if the position available, if not available keep asking the player to enter a
//        valid position.
//        6- Computer chose random position and check valid position.
//        7- Checks if either player or Computer has won.
//
//        } while (true); // the game loop

        printHeaderFooter("Game Over!");


    }

    //        2- Use method.
    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    public static void printBoard(int[][] board) {
        int verticalLines = 2;
        for (int[] raw : board) {
            for (int i : raw) {
                for (int j = 0; j <= 21; j++) { // add spaces
                    if (j == 11) {
                        System.out.print("+");
                    } else {
                        System.out.print(" ");
                    }
                }
                if (i == 10) {
                    System.out.print("O");
                } else if (i == 20) {
                    System.out.print("X");
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();
            if (verticalLines > 0) {
                for (int j = 0; j <= 85; j++) { // add vertical lines
                    if (j == 34 || j == 57) {
                        System.out.print("+");
                    } else {
                        System.out.print("_");
                    }
                }
                verticalLines--;
            }

            System.out.println();
        }
    }

    public static void printHeaderFooter(String message) {
        int slashes = 42;
        System.out.println();
        for (int i = 0; i <= slashes - (message.length() / 2); i++) {
            System.out.print("-");
        }
        System.out.print(message);
        for (int i = 0; i <= slashes - (message.length() / 2); i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println();
    }

    public static void printGameRules() {
        String[] rules = {
                "Game rules:",
                "In tic-tac-toe to win, you need to score 3 points as follows:",
                "    there are two signs, X and O, you have to select one first",
                "    score adjacent positions with the same sign in a row X X X",
                "    or a vertical column like this: ",
                "     X",
                "     X",
                "     X",
                "    Or diagonally like this: ",
                "     X |",
                "         X |",
                "             X |",
                "    to finally win the game!",
                "You will be playing against the computer, good luck!"
        };

        for (String rule : rules) {
            for (int i = 0; i <= 10; i++) {
                System.out.print(" ");
            }
            System.out.print("* ");
            System.out.print(rule);
            System.out.println();
        }

        System.out.println();
    }
}
