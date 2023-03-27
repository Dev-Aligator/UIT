import java.util.Scanner;
class Solution1{
    private int n;
    public Solution1(int n){
       this.n = n; 
    }
    public void solve() {
        System.out.print("Cac uoc so: ");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

class Solution2{
    private int n;
    public Solution2(int n){
        this.n = n;
    }
    public void solve(){
        int count = 0;
        while(n>0){
            count++;
            n/=10;
        }
        System.out.println("So chu so: " + count);
    }
}

class Solution3{
    private int n;
    public Solution3(int n){
        this.n = n;
    }
    public void solve(){
        String str = Integer.toString(n);
        int left = 0;
        int right = str.length() - 1;
        while(left<right){
            if(str.charAt(left) != str.charAt(right)){
                System.out.println("Khong phai la so doi xung");
                return;
            }
            left++;
            right--;
        }
        System.out.println("La so doi xung");
    }
}

class Solution4{
    private int n;
    public Solution4(int n){
        this.n = n;
    }
    public void solve(){
        int sqrt = (int) Math.sqrt(n);
        if(sqrt*sqrt == n){
            System.out.println("La mot so chinh phuong");
        }
        else{
            System.out.println("Khong phai la mot so chinh phuong");
        }
    }
}
public class Exercise4{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        do{
            System.out.print("Nhap so nguyen duong n: ");
            n = scanner.nextInt(); 
        }while(n<1);
        Solution1 solution1 = new Solution1(n);
        solution1.solve();
        Solution2 solution2 = new Solution2(n);
        solution2.solve();
        Solution3 solution3 = new Solution3(n);
        solution3.solve();
        Solution4 solution4 = new Solution4(n);
        solution4.solve();
    }
}
