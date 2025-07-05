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
            surrounded means {done};
 * */

//        Minimum Requirements
//        1- Use 2D Array.
        int[][] gameBoard = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}}; // the numbers represent the position number to ask the user

        Scanner input = new Scanner(System.in);
        String choice;
        int round = 0, rounds = 0, selection; // for the current and max round(s) being played
        boolean incorrectSelection = true; // for enforcing correct selections
        int whoWon; // store the number of the sign of the winner 10 for X or 20 for Oh
        int userSign = 0; // the sign is either 10 = X or 20 = @ not zero but oh
        int computerSign = 0; // the sign is either 10 = X or 20 = @ not zero but oh
        int userPoints = 0, computerPoints = 0; // for comparing who won more!
        int selectedPosition;

        printHeaderFooter("Welcome to the Tic-tac-toe game!");
        printGameRules();

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

        // Extra credits:
        // The user has two options:
        System.out.println("          * Select game mode:");
        System.out.println("               * 1: Play one round");
        System.out.println("               * 2: Play 3 rounds and then determine the winner");
        incorrectSelection = true; //assume incorrect selections for the upcoming logic

        do { // force user to select correct option
            try {
                selection = input.nextInt();

                switch (selection) {
                    case 1:
                        rounds = 1;
                        incorrectSelection = false;
                        break;
                    case 2:
                        rounds = 3;
                        incorrectSelection = false;
                        break;
                    default:
                        throw new InputMismatchException("This is not an option, please try again");
                }

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Select a valid option");
            } catch (Exception e) {
                System.out.println("An error occurred :(");
                System.out.println(e.getMessage());
            } finally {
                input.nextLine();
            }
        } while (incorrectSelection);

        for (int i = 0; i <= rounds - 1; i++) {

            round++;
            if (rounds != 1) { // if the rounds are not only 1 round, display score
                printHeaderFooter("Round " + round);
                printHeaderFooter("User points = " + userPoints + ", computer points = " + computerPoints);
            }
            // 3- Should Display a Game Board.
            printBoard(gameBoard);

            do {

//        5- Check if the position available, if not available keep asking the player to enter a valid position.
                printHeaderFooter("Your turn:");
                System.out.println("Select a position from the numbers displayed on the board: ");
                incorrectSelection = true; //assume incorrect selections for the upcoming logic

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
                    printHeaderFooter("Congratulations, you have won this round!");
                    userPoints++;
                    break;
                }
                if (numberOfOccupied > 8) { //no one has won the game
                    printHeaderFooter("No more positions, it is a draw!");
                    break;
                }

//        6- Computer chose random position and check valid position.
                printHeaderFooter("Computer's turn:");
                System.out.println("Please wait for the computer to think . . .");
                incorrectSelection = true; //assume incorrect selections for the upcoming logic

                do { // make the computer try again and again until its randomly selected position is not occupied
                    try {
                        selectedPosition = randomNumber(min, max); // the computer is not really thinking or anything
                        incorrectSelection = isOccupiedAndSet(selectedPosition, computerSign, gameBoard);

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
                    printHeaderFooter("Bad Luck, the computer has won this round!");
                    computerPoints++;
                    break;
                }
                if (numberOfOccupied > 8) { //no one has won the game
                    printHeaderFooter("No more positions, it is a draw!");
                    break;
                }

            } while (true); // the game loop

            if (userPoints > 1) {
                printHeaderFooter("User points = " + userPoints + ", computer points = " + computerPoints);
                printHeaderFooter("Congratulations, you won the game!");
                break;
            }
            if (computerPoints > 1) {
                printHeaderFooter("User points = " + userPoints + ", computer points = " + computerPoints);
                printHeaderFooter("Bad Luck, the computer has won the game!");
                break;
            }

            // reset the board
            resetTheBoard(gameBoard);
        } // the rounds loop


        printHeaderFooter("Game Over!");


    } // End of Main

    //        2- Use method.
    public static void resetTheBoard(int[][] board){
        int number = 0;
        numberOfOccupied = 0;
        for (int i = 0; i <= board.length-1; i++) {
            for (int j = 0; j <=board[i].length-1 ; j++) {
                board[i][j] = number;
                number++;
            }
        }
    }
    public static int whoWon(int[][] board) {

        // first check for horizontal win (3) cases
        if (board[0][0] == board[0][1]
                && board[0][1] == board[0][2]) { // if first row is equal example:{20 20 20} then return the value of player sign
            return board[0][0];
        }
        if (board[1][0] == board[1][1]
                && board[1][1] == board[1][2]) { //if second row is equal
            return board[1][0];
        }
        if (board[2][0] == board[2][1]
                && board[2][1] == board[2][2]) { // if third row is equal
            return board[2][0];
        }

        // second check for vertical win cases (3)
        if (board[0][0] == board[1][0]
                && board[1][0] == board[2][0]) { // if first column is equal
            return board[0][0];
        }
        if (board[0][1] == board[1][1]
                && board[1][1] == board[2][1]) { //if second column is equal
            return board[0][1];
        }
        if (board[0][2] == board[1][2]
                && board[1][2] == board[2][2]) { // if third column is equal
            return board[0][2];
        }

        // third check for diagonal win cases (2)
        if (board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) { //if first diagonal is equal
            return board[0][0];
        }
        if (board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) { // if second diagonal is equal
            return board[0][2];
        }

        return -1; // no one has won if it does not equal to the sign number 10 = X 20 = @;
    }

    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    public static void printBoard(int[][] board) {
        int lines = 2;
        int verticalLines = lines;
        int horizontalLines = lines;
        boolean startOfRow;
        int numberOfSignsToPrint;

        for (int[] row : board) {
            startOfRow = true;
            numberOfSignsToPrint = 3;

            while (verticalLines > 0) {
                for (int j = 0; j <= 57; j++) { // add vertical lines
                    if (j == 34 || j == 57) {
                        System.out.print("┃");
                    } else {
                        System.out.print(" ");
                    }
                }
                verticalLines--;
                System.out.println();
            }

            verticalLines = lines;

            for (int i : row) {// add spaces and signs
                if (startOfRow){
                    for (int j = 0; j <= 21; j++) {
                        System.out.print(" ");
                    }

                } else {
                    for (int j = 0; j <= 10; j++) {
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
                numberOfSignsToPrint--;

                if (startOfRow){
                    for (int j = 0; j <= 10; j++) {
                        System.out.print(" ");
                    }
                    System.out.print("┃");
                    startOfRow = false;
                } else if (numberOfSignsToPrint !=0){
                    for (int j = 0; j <= 9; j++) {
                        System.out.print(" ");
                    }
                    System.out.print("┃");

                }

            }

            System.out.println();
            while (verticalLines > 0) {
                for (int j = 0; j <= 57; j++) { // add vertical lines
                    if (j == 34 || j == 57) {
                        System.out.print("┃");
                    } else {
                        System.out.print(" ");
                    }
                }
                verticalLines--;
                System.out.println();
            }

            verticalLines = lines;

            if (horizontalLines > 0) {
                for (int j = 0; j <= 82; j++) { // add horizontal lines ___+___
                    if (j == 34 || j == 57) {
                        System.out.print("┃");
                    } else if (j > 10){
                        System.out.print("━");
                    } else {
                        System.out.print(" ");
                    }
                }
                horizontalLines--;
            }

            System.out.println();
        }
    }

    public static void printHeaderFooter(String message) {
        int slashes = 42;
        for (int i = 0; i <= slashes - (message.length() / 2); i++) {
            System.out.print("-");
        }
        System.out.print(message);
        for (int i = 0; i <= slashes - (message.length() / 2); i++) {
            System.out.print("-");
        }
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
