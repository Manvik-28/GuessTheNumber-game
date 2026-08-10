
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        int secret = 1000 + random.nextInt(9000);

        System.out.println("Secret number: " + secret);
    }
}