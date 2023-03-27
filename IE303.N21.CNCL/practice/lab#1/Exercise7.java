import java.util.Arrays;
import java.util.Scanner;

public class Exercise7{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap n: ");
        int n = scanner.nextInt();
        int[] a = new int[n];
        System.out.print("Nhap cac phan tu cua A: ");
        for(int i = 0; i < n ; ++ i){
            a[i] = scanner.nextInt();
        }
        System.out.print("Nhap m: ");
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = (int) (Math.random() * 100);
        }
        scanner.close();
        System.out.println("Array B: " + Arrays.toString(b));

        int[] C = Arrays.copyOf(a, n);
        if(b.length < 3){
            System.out.println("B chi co 2 phan tu");
            return;
        }
        System.arraycopy(b, m - 3, C, 0, 3);

        Arrays.sort(C);
        System.out.println("Array C: " + Arrays.toString(C));
    }
}
