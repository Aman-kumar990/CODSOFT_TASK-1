import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 7;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalAttempts = 0;

        System.out.println("NUMBER GAME");
        System.out.println("Try to guess the randomly generated number.");

        boolean playAgain = true;

        while (playAgain) {
            roundsPlayed++;

            RoundResult result = playRound(roundsPlayed);
            totalAttempts += result.attempts;

            if (result.won) {
                roundsWon++;
            }

            System.out.println("\nScore");
            System.out.println("Rounds played: " + roundsPlayed);
            System.out.println("Rounds won: " + roundsWon);
            System.out.println("Total attempts used: " + totalAttempts);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = scanner.next().trim().toLowerCase();
            playAgain = choice.equals("yes") || choice.equals("y");
        }

        System.out.println("\nThanks for playing!");
        System.out.println("Final score: " + roundsWon + " win(s) out of " + roundsPlayed + " round(s).");

        scanner.close();
    }

    private static RoundResult playRound(int roundNumber) {
        int secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attempts = 0;

        System.out.println("\nRound " + roundNumber);
        System.out.println("I have selected a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

        while (attempts < MAX_ATTEMPTS) {
            int guess = readInteger("Enter your guess: ");
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Correct! You guessed the number in " + attempts + " attempt(s).");
                return new RoundResult(true, attempts);
            } else if (guess < secretNumber) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }

            int remainingAttempts = MAX_ATTEMPTS - attempts;
            if (remainingAttempts > 0) {
                System.out.println("Attempts remaining: " + remainingAttempts);
            }
        }

        System.out.println("Sorry, you used all your attempts. The number was " + secretNumber + ".");
        return new RoundResult(false, attempts);
    }

    private static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Please enter a valid whole number.");
            scanner.next();
        }
    }

    private static class RoundResult {
        private final boolean won;
        private final int attempts;

        private RoundResult(boolean won, int attempts) {
            this.won = won;
            this.attempts = attempts;
        }
    }
}
