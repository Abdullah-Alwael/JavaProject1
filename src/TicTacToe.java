import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    final static int xSign = 10; // this number will represent the x sign
    final static int oSign = 20; // this number will represent the O sign

    public static void main(String[] args) {
/*
        * Rules & Guidelines
          • should use all java basics concept:
          * {Variables}, {Scanner}, {Conditions}, {Loops}, {Arrays}, {Methods}, {exception handling}, {switch}.
 */

//        Minimum Requirements
//        1- Use 2D Array.
        int[][] gameBoard = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}}; // the numbers represent the position number to ask the user

        Scanner input = new Scanner(System.in);
        String choice;
        int min, max; // for random number range
        int round = 0; // for the current round being played
        boolean won = false; // checking if there is a win case
        boolean incorrectSelection = true; // for enforcing correct selections
        int whoWon; // store the number of the sign of the winner 10 for X or 20 for O
        int userSign = 0; // the sign is either 10 = X or 20 = O not zero but oh
        int computerSign = 0; // the sign is either 10 = X or 20 = O not zero but oh
        int userPoints, computerPoints; // for comparing who won more!
        int selectedPosition = -1;

        printHeaderFooter("Welcome to the Tic-tac-toe game!");
        printGameRules();


//        4- Ask player position. sign?
        System.out.println("which sign will you choose? (X or O):");
        do { // force user to select correct sign
            try {
                choice = input.next();

                switch (choice.charAt(0)) {
                    case 'X':
                    case 'x':
                        userSign = xSign;
                        computerSign = oSign;
                        incorrectSelection = false;
                        break;
                    case '0':
                    case 'o':
                    case 'O':
                        userSign = oSign;
                        computerSign = xSign;
                        incorrectSelection = false;
                        break;
                    default:
                        throw new InputMismatchException("This is not X or O, please try again");
                }

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred :(");
                System.out.println(e.getMessage());
            }
        } while (incorrectSelection);

        incorrectSelection = true; //assume incorrect selections for the upcoming logic

        do {

//        3- Should Display a Game Board.
            printBoard(gameBoard);

//        5- Check if the position available, if not available keep asking the player to enter a valid position.
            printHeaderFooter("Your turn:");
            System.out.println("Select a position from the numbers displayed on the board: ");
            do {
                try {
                    selectedPosition = input.nextInt();

                    // set the position if not occupied, else try again
                    incorrectSelection = isOccupiedAndSet(selectedPosition, userSign, gameBoard);

                    if (incorrectSelection) {
                        System.out.println("Position " + selectedPosition
                                + " is already occupied, choose another one");
                    }

                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter a valid number");
                } catch (Exception e) {
                    System.out.println("An error occurred :(");
                    System.out.println(e.getMessage());
                } finally {
                    input.nextLine(); //flush the input
                }
            } while (incorrectSelection);

            incorrectSelection = true; //assume incorrect selections for the upcoming logic

            printBoard(gameBoard); // print it again after user has selected a position
            //TODO check if won! after each turn
            whoWon = whoWon(gameBoard);

            printHeaderFooter("Computer's turn:");
//        6- Computer chose random position and check valid position.
            whoWon = whoWon(gameBoard);

//        7- Checks if either player or Computer has won.
            if (whoWon == userSign){
                won = true;
                printHeaderFooter("Congratulations, you have won the game!");
            }
            if (whoWon == computerSign){
                won = true;
                printHeaderFooter("Bad Luck, the computer has won the game!");
            }
        } while (!won); // the game loop

        printHeaderFooter("Game Over!");


    } // End of Main

    //        2- Use method.
    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    public static void printBoard(int[][] board) {
        int verticalLines = 2;
        int horizontalLines = 6;

        for (int[] raw : board) {

            if (horizontalLines > 0) {
                for (int j = 0; j <= 85; j++) { // add vertical lines
                    if (j == 34 || j == 57) {
                        System.out.print("+");
                    } else {
                        System.out.print(" ");
                    }
                }
                horizontalLines--;
                System.out.println();
            }

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

    public static boolean isOccupiedAndSet(int position, int playerSign, int[][] gameBoard) throws InputMismatchException {
        switch (position) {
            case 0:
                if (gameBoard[0][0] == 0) { // it is not occupied
                    gameBoard[0][0] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 1:
                if (gameBoard[0][1] == 1) { // it is not occupied
                    gameBoard[0][1] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 2:
                if (gameBoard[0][2] == 2) { // it is not occupied
                    gameBoard[0][2] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 3:
                if (gameBoard[1][0] == 3) { // it is not occupied
                    gameBoard[1][0] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 4:
                if (gameBoard[1][1] == 4) { // it is not occupied
                    gameBoard[1][1] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 5:
                if (gameBoard[1][2] == 5) { // it is not occupied
                    gameBoard[1][2] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 6:
                if (gameBoard[2][0] == 6) { // it is not occupied
                    gameBoard[2][0] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 7:
                if (gameBoard[2][1] == 7) { // it is not occupied
                    gameBoard[2][1] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 8:
                if (gameBoard[2][2] == 8) { // it is not occupied
                    gameBoard[2][2] = playerSign;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            default:
                throw new InputMismatchException("Invalid Position!");
        }
    }
}
