import java.net.Proxy;
import java.util.Scanner;
class Solution1{
    private String s1,s2;
    public Solution1(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
    }
    public void solve(){
        int totalLength = s1.length() +s2.length();
        System.out.println("Tong chieu dai 2 chuoi:"+ totalLength );
    }
}
class Solution2{
    private String s1;
    public Solution2(String s1){
        this.s1 = s1;
    }
    public void solve(){
        if(s1.length() < 3){
            System.out.println("Chuoi s1 chi co " + s1.length() + " ki tu:" +s1);
            return;
        }
        String firstThreeChars = s1.substring(0, 3);
        System.out.println("3 kí tự đầu tiên của chuỗi s1: " + firstThreeChars);
    }
}
class Solution3{
    private String s2;
    public Solution3(String s2){
        this.s2 = s2;
    }
    public void solve(){
        if(s2.length() <3){
            System.out.println("Chuoi s2 chi co " +s2.length() +" ki tu: " +s2);
            return;
        }
        String lastThreeChars = s2.substring(s2.length()-3);
        System.out.println("3 kí tự cuoi cua s2: " + lastThreeChars);
    }
}
class Solution4{
    private String s1;
    public Solution4(String s1){
        this.s1 = s1;
    }
    public void solve(){
        if(s1.length() < 7){
            System.out.println("Chuoi s1 chi co " + s1.length() + " ki tu nen khong ton tai ki tu thu 6");
            return;
        }
        System.out.println("Ki tu thu 6 cua chuoi s1: " + s1.charAt(6));
    }
}

class Solution5{
    private String s1,s2;
    public Solution5(String s1,String s2){
        this.s1 = s1;
        this.s2 = s2;
    }
    public void solve(){
        if(s1.equals(s2)){
            System.out.println("Hai chuoi s1 va s2 bang nhau");
        }
        else{
            System.out.println("Hai chuoi s1 va s2 khong bang nhau");
        }
    }
}
class Solution6{
    private String s1,s2;
    public Solution6(String s1,String s2){
        this.s1 = s1;
        this.s2 = s2;
    }
    public void solve(){
        int index = s1.indexOf(s2);
        if (index != -1) {
            System.out.println("Chuỗi s2 xuất hiện trong chuỗi s1 tại vị trí: " + index);
        } else {
            System.out.println("Chuỗi s2 không xuất hiện trong chuỗi s1.");
        }
    }
}


public class Exercise5{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap 2 chuoi: ");
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();
        Solution1 solution1 = new Solution1(s1,s2);
        solution1.solve();
        
        Solution2 solution2 = new Solution2(s1);
        solution2.solve();
        Solution3 solution3 = new Solution3(s2);
        solution3.solve();
        Solution4 solution4 = new Solution4(s1);
        solution4.solve();
        Solution5 solution5 = new Solution5(s1,s2);
        solution5.solve();
        Solution6 solution6 = new Solution6(s1,s2);
        solution6.solve();
    }

}
