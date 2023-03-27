import java.util.Scanner;
public class Problem_2{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        System.out.println(String.format("%.2f", Math.pow(a,5) - 2*Math.sqrt(Math.abs(b)) + a*b*c));
        scanner.close();
        
    }
}
