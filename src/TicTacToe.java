import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    final static int xSign = 10; // this number will represent the x sign
    final static int oSign = 20; // this number will represent the @ sign
    final static int min = 0, max = 8; // for random number range
    static int numberOfOccupied = 0;

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
        int round = 0; // for the current round being played
        boolean incorrectSelection = true; // for enforcing correct selections
        int whoWon; // store the number of the sign of the winner 10 for X or 20 for Oh
        int userSign = 0; // the sign is either 10 = X or 20 = @ not zero but oh
        int computerSign = 0; // the sign is either 10 = X or 20 = @ not zero but oh
        int userPoints = 0, computerPoints = 0; // for comparing who won more!
        int selectedPosition = -1;

        printHeaderFooter("Welcome to the Tic-tac-toe game!");
        printGameRules();

//        3- Should Display a Game Board.
        printBoard(gameBoard);

//        4- Ask player position. sign?
        System.out.println("which sign will you choose? (X or @):");
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
                    case '@':
                    case 'o':
                    case 'O':
                        userSign = oSign;
                        computerSign = xSign;
                        incorrectSelection = false;
                        break;
                    default:
                        throw new InputMismatchException("This is not X or @, please try again");
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

            printBoard(gameBoard); // print it again after player has selected a position
//        7- Checks if either player or Computer has won.
            whoWon = whoWon(gameBoard); // check who won the game

            if (whoWon == userSign) {
                printHeaderFooter("Congratulations, you have won the game!");
                userPoints++;
                break;
            }
            if (numberOfOccupied > 8){ //no one has won the game
                printHeaderFooter("No more positions, it is a draw!");
                break;
            }

            incorrectSelection = true; //assume incorrect selections for the upcoming logic
            printHeaderFooter("Computer's turn:");
            System.out.println("Please wait for the computer to think . . .");
//        6- Computer chose random position and check valid position.

            do { // make the computer try again and again until its randomly selected position is not occupied
                try {
                    selectedPosition = randomNumber(min,max); // not really thinking or anything
                    incorrectSelection = isOccupiedAndSet(selectedPosition, computerSign,gameBoard);

                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter a valid number");
                } catch (Exception e) {
                    System.out.println("An error occurred :(");
                    System.out.println(e.getMessage());
                }

            } while (incorrectSelection);

            printBoard(gameBoard); // print it again after player has selected a position
//        7- Checks if either player or Computer has won.
            whoWon = whoWon(gameBoard); // check who won the game

            if (whoWon == computerSign) {
                printHeaderFooter("Bad Luck, the computer has won the game!");
                computerPoints++;
                break;
            }
            if (numberOfOccupied > 8){ //no one has won the game
                printHeaderFooter("No more positions, it is a draw!");
                break;
            }

        } while (true); // the game loop

        printHeaderFooter("Game Over!");


    } // End of Main

    //        2- Use method.
    public static int whoWon(int[][] board) {

        // first check for horizontal win (3) cases
        if (board[0][0] == board[0][1]
        && board[0][1] == board[0][2]){ // if first row is equal example:{20 20 20} then return the value of player sign
            return board[0][0];
        }
        if (board[1][0] == board[1][1]
        && board[1][1] == board[1][2]){ //if second row is equal
            return board[1][0];
        }
        if (board[2][0] == board[2][1]
        && board[2][1] == board[2][2]){ // if third row is equal
            return board[2][0];
        }

        // second check for vertical win cases (3)
        if (board[0][0] == board[1][0]
                && board[1][0] == board[2][0]){ // if first column is equal
            return board[0][0];
        }
        if (board[0][1] == board[1][1]
                && board[1][1] == board[2][1]){ //if second column is equal
            return board[0][1];
        }
        if (board[0][2] == board[1][2]
                && board[1][2] == board[2][2]){ // if third column is equal
            return board[0][2];
        }

        return -1; // no one has won if it does not equal to the sign number 10 = X 20 = @;
    }

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
                    System.out.print("X");
                } else if (i == 20) {
                    System.out.print("@");
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
                "    there are two signs, X and @, you have to select one first",
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
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 1:
                if (gameBoard[0][1] == 1) { // it is not occupied
                    gameBoard[0][1] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 2:
                if (gameBoard[0][2] == 2) { // it is not occupied
                    gameBoard[0][2] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 3:
                if (gameBoard[1][0] == 3) { // it is not occupied
                    gameBoard[1][0] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 4:
                if (gameBoard[1][1] == 4) { // it is not occupied
                    gameBoard[1][1] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 5:
                if (gameBoard[1][2] == 5) { // it is not occupied
                    gameBoard[1][2] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 6:
                if (gameBoard[2][0] == 6) { // it is not occupied
                    gameBoard[2][0] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 7:
                if (gameBoard[2][1] == 7) { // it is not occupied
                    gameBoard[2][1] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            case 8:
                if (gameBoard[2][2] == 8) { // it is not occupied
                    gameBoard[2][2] = playerSign;
                    numberOfOccupied++;
                    return false;
                } else { // it is already occupied
                    return true;
                }
            default:
                throw new InputMismatchException("Invalid Position!");
        }
    }
}
