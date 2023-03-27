import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Exercise9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // the size of the array
        int[] arr = new int[n];
        
        // fill the array with 1 to n
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        System.out.println("       Id | " + Arrays.toString(arr));
        // shuffle the array
        Random rand = new Random();
        for (int i = 0; i < n - 1; i++) {
            int j = rand.nextInt(n - i) + i;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        // print the shuffled array
        System.out.println("Contactee | " + Arrays.toString(arr));
        System.out.println("The following citizens are to be self-isolated:");
        int[] flags = new int[n];
        int i = 0;
        flags[0] = 1;
        System.out.print(0 + " ");
        while(true){
            if(flags[arr[i]] == 1){
                break;
            }
            System.out.print(arr[i] + " ");
            flags[arr[i]] = 1;
            i = arr[i];
        }
        scanner.close();
    }
}
