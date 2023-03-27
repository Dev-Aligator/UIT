import java.util.Scanner;

public class Problem_4{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int l = String.valueOf(n).length();
        int count = m / (int) Math.pow(10, l);
        if (m % (int) Math.pow(10, l) >= n) {
            count += 1;
        }
        System.out.println(count);
        scanner.close();

    }
}
