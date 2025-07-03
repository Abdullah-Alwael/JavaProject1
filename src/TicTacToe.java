import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
/*
        * Rules & Guidelines
          • should use all java basics concept:
          * Variables, Scanner, Conditions, Loops, {Arrays}, {Methods}, exception handling, switch.
 */

//        Minimum Requirements
//        1- Use 2D Array.
        int[][] gameBoard = new int[3][3];
        Scanner input = new Scanner(System.in);
        int min, max;
        int round;
        boolean won;
        int userPoints, computerPoints;

//        3- Should Display a Game Board.
        System.out.println("------------------------------Welcome to the Tic-tac-toe game!------------------------");

        printBoard(gameBoard);
//        do {
//
//
//        }while (true); // the game loop

        System.out.println("-----------------------------------------Game Over!-----------------------------------");


//        4- Ask player position.
//        5- Check if the position available, if not available keep asking the player to enter a
//        valid position.
//        6- Computer chose random position and check valid position.
//        7- Checks if either player or Computer has won.
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
                if (i == 1) {
                    System.out.print("O");
                } else if (i == 2) {
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
}
