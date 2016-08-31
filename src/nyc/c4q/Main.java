package nyc.c4q;

import java.util.Scanner;

public class Main {

    public static boolean loopGame = true;
    public static void main(String[] args) {

        while (loopGame) {
            game();
            loopGame = continueGame();
        }
    }

    public static boolean continueGame() {
        System.out.print("\nPlay again? (yes/no): ");
        return handleInput(readInput());
    }

    public static boolean handleInput(String input) {
        boolean valid = false;
        boolean runGameAgain = false;
        while (!valid) {
            if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
                valid = true;
                runGameAgain = true;
            } else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
                valid = true;
                runGameAgain = false;
                System.out.println("exiting...");
            } else {
                System.out.print("Invalid input, (e.g. yes/y or no/n)\nEnter: ");
                input = readInput();
            }
        }
        return runGameAgain;

    }
    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void game() {
        Hangman hangman = new Hangman();
        while (hangman.getMisses() < 5) {
            hangman.printCurrentWord();
            hangman.promptPlayer("Enter a letter: ");
            hangman.readLetter();
            hangman.checkLetter();
            if (hangman.guessedSuccessfully()) {
                break;
            }
            System.out.println(Drawing.get(hangman.getMisses()));
            System.out.println("Misses -> " + hangman.getMisses());
        }
        if (hangman.guessedSuccessfully()) {
            System.out.println("Success");
        } else {
            System.out.println("The answer was " + hangman.getSecretWord());
        }
    }
}
