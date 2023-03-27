import java.util.Scanner;
public class Problem_3{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int t = scanner.nextInt();
        int remain = t % (2*k);
        if(remain <= k){
            System.out.println(remain);
        }
        else{
            System.out.println(2*k - remain);
        }
        scanner.close();
    } 
}
