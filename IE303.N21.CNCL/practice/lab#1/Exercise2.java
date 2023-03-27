import java.util.Scanner;

public class Exercise2{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        do{
            System.out.print("Nhap n (n > 0): ");
            n = scanner.nextInt();
        }while(n <= 0);
        System.out.print("Nhap x: ");
        double x = scanner.nextDouble();
        double divided = 1;
        double divisor = 0;
        double sum = 0;
        for(int i = 1; i <= n ; ++i){
            divided*= x;
            divisor+=i;
            sum += divided/divisor;
             
        }
        System.out.println("S(" + n + ") = " + sum );
        scanner.close();
    }
}
