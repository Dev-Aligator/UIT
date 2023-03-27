import java.util.Scanner;
import java.util.Random;
public class Exercise8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Nhap n: ");
        int max = input.nextInt();
        
        int secretNumber = random.nextInt(max+1); 
        int guess;
        int min = 0;

        System.out.println("(" + min +", " +max +")?");

        do {
            guess = input.nextInt();

            if (guess < min || guess > max) {
                System.out.println("Out of range. Please try again.");
                continue;
            }

            if (guess == secretNumber) {
                System.out.println("Bingo.");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low");
                min = guess + 1;
                System.out.println("(" + min +", " +max +")?");
            } else {
                System.out.println("Too high");
                max = guess - 1;
                System.out.println("(" + min +", " +max +")?");
            }

            if (max == min) {
                System.out.println("You lose");
                break;
            }
        } while (true);

        input.close();
    }
}
