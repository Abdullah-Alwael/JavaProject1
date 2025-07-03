import java.util.Random;

public class TicTacToe {
    public static void main(String[] args) {
        int[][] gameBoard = new int[3][3];
        /*
        * Rules & Guidelines
          • should use all java basics concept:
          * Variables, Scanner, Conditions, Loops, Arrays, Methods, exception handling, switch.
        * */
    }

    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }
}
