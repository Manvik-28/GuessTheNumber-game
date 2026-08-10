import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner in = new Scanner(System.in);

        String secret = String.valueOf(1000 + random.nextInt(9000));

        System.out.println("Welcome to Guess The Number!");
        System.out.println("I have selected a 4-digit number.");
        System.out.println();
        int attempts=0;
        while (true) {

            System.out.print("Enter your guess: ");
            String guess = in.next();
            attempts++;
            if (guess.length() != 4) {
                System.out.println("Please enter exactly 4 digits.");
                continue;
            }

            int correctPositions = 0;
            int correctDigits = 0;

            // Count correct positions
            for (int i = 0; i < 4; i++) {
                if (secret.charAt(i) == guess.charAt(i)) {
                    correctPositions++;
                }
            }

            // Count correct digits
            boolean[] used = new boolean[4];

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    if (!used[j] && secret.charAt(j) == guess.charAt(i)) {
                        correctDigits++;
                        used[j] = true;
                        break;
                    }
                }
            }

            System.out.println("Correct digits: " + correctDigits);
            System.out.println("Correct positions: " + correctPositions);
            System.out.println();

            if (correctPositions == 4) {
                System.out.println("Congratulations! You guessed the number!");
                System.out.println("Attempts: " + attempts);
                break;
            }
        }

        in.close();
    }
}