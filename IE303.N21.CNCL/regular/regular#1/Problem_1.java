
import java.util.Scanner;
public class Problem_1{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println("max = " + Math.max(a,b));
        System.out.println("min = " + Math.min(a,b));
        scanner.close();
    }
}
