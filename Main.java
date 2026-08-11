import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner in = new Scanner(System.in);

        System.out.println("===== GUESS THE NUMBER =====");
        System.out.println();
        System.out.println("1. Easy    - 4 digits");
        System.out.println("2. Medium  - 6 digits");
        System.out.println("3. Hard    - 8 digits");
        System.out.println("4. Extreme - 10 digits");
        System.out.println();

        int choice;

        while (true) {
            System.out.print("Choose difficulty: ");
            choice = in.nextInt();

            if (choice >= 1 && choice <= 4) {
                break;
            }

            System.out.println("Please choose between 1 and 4.");
        }

        int digits;

        if (choice == 1) {
            digits = 4;
        } else if (choice == 2) {
            digits = 6;
        } else if (choice == 3) {
            digits = 8;
        } else {
            digits = 10;
        }

        String secret = generateNumber(digits, random);

        int attempts = 0;

        System.out.println();
        System.out.println("I have selected a " + digits + "-digit number.");
        System.out.println("Try to guess it!");
        System.out.println();

        while (true) {

            System.out.print("Enter your guess: ");
            String guess = in.next();

            if (guess.length() != digits) {
                System.out.println("Please enter exactly " + digits + " digits.");
                continue;
            }

            if (!isNumber(guess)) {
                System.out.println("Please enter digits only.");
                continue;
            }

            attempts++;

            int correctPositions = 0;
            int correctDigits = 0;

            // Count correct positions
            for (int i = 0; i < digits; i++) {

                if (secret.charAt(i) == guess.charAt(i)) {
                    correctPositions++;
                }
            }

            // Count correct digits
            boolean[] used = new boolean[digits];

            for (int i = 0; i < digits; i++) {

                for (int j = 0; j < digits; j++) {

                    if (!used[j] && secret.charAt(j) == guess.charAt(i)) {
                        correctDigits++;
                        used[j] = true;
                        break;
                    }
                }
            }

            System.out.println();
            System.out.println("Correct digits: " + correctDigits);
            System.out.println("Correct positions: " + correctPositions);
            System.out.println();

            if (correctPositions == digits) {
                System.out.println("🎉 Congratulations!");
                System.out.println("You guessed the number!");
                System.out.println("Attempts: " + attempts);
                break;
            }
        }

        in.close();
    }

    public static String generateNumber(int digits, Random random) {

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < digits; i++) {

            int digit = random.nextInt(10);

            // First digit cannot be 0
            if (i == 0 && digit == 0) {
                digit = 1 + random.nextInt(9);
            }

            number.append(digit);
        }

        return number.toString();
    }

    public static boolean isNumber(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }
}