import java.util.Calendar;
import java.util.Scanner;

class Solution1{
    private Calendar a,b;
    public Solution1(Calendar a, Calendar b){
        this.a = a;
        this.b = b;
    }
    public void solve(){
        if (a.compareTo(b) < 0) {
            System.out.println("Ngày a trước ngày b");
        } else if (a.compareTo(b) > 0) {
            System.out.println("Ngày a sau ngày b");
        } else {
            System.out.println("Ngày a và ngày b cùng ngày");
        }

    }
}
class Solution2{
    private Calendar a;
    public Solution2(Calendar a){
        this.a = a;
    }
    public void solve(){
        a.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("Ngày trước của ngày a: " + a.get(Calendar.DAY_OF_MONTH) + "/" + (a.get(Calendar.MONTH) + 1) + "/" + a.get(Calendar.YEAR));
        a.add(Calendar.DAY_OF_MONTH, 2);
        System.out.println("Ngày tiếp theo của ngày a: " + a.get(Calendar.DAY_OF_MONTH) + "/" + (a.get(Calendar.MONTH) + 1) + "/" + a.get(Calendar.YEAR));
    }
}
class Solution3{
    private Calendar a;
    public Solution3(Calendar a){
        this.a = a;
    }
    public void solve(){
        System.out.println("Ngày a là ngày thứ " + a.get(Calendar.DAY_OF_YEAR) + " trong năm");
    }
}
class Solution4{
    private Calendar a;
    public Solution4(Calendar a){
        this.a = a;
    }
    public void solve(){
        System.out.println("Tháng " + (a.get(Calendar.MONTH) + 1) + " năm " + a.get(Calendar.YEAR) + " có " + a.getActualMaximum(Calendar.DAY_OF_MONTH) + " ngày");
    }
}
class Solution5{
    private Calendar a;
    public Solution5(Calendar a){
        this.a = a;
    }
    public void solve(){
        if (a.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
            System.out.println("Năm " + a.get(Calendar.YEAR) + " là năm nhuận");
        } else {
            System.out.println("Năm " + a.get(Calendar.YEAR) + " không phải là năm nhuận");
        }
    }
}

public class Exercise6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập ngày a (dd/mm/yyyy):");
        String aStr = scanner.nextLine();
        Calendar a = Calendar.getInstance();
        String[] aArr = aStr.split("/");
        a.set(Integer.parseInt(aArr[2]), Integer.parseInt(aArr[1]) - 1, Integer.parseInt(aArr[0]));

        System.out.println("Nhập ngày b (dd/mm/yyyy):");
        String bStr = scanner.nextLine();
        Calendar b = Calendar.getInstance();
        String[] bArr = bStr.split("/");
        b.set(Integer.parseInt(bArr[2]), Integer.parseInt(bArr[1]) - 1, Integer.parseInt(bArr[0]));
        if(!isValidDate(a) || !isValidDate(b)){
            System.out.println("Ngay nhap vao khong hop le");
            return;
        }
        Solution1 solution1 = new Solution1(a,b);
        solution1.solve();
        Solution2 solution2 = new Solution2(a);
        solution2.solve();
        Solution3 solution3 = new Solution3(a);
        solution3.solve();
        Solution4 solution4 = new Solution4(a);
        solution4.solve();
        Solution5 solution5 = new Solution5(a);
        solution5.solve();
    }

    public static boolean isValidDate(Calendar a){
       boolean isValid = true;
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH);
        int day = a.get(Calendar.DATE);
        if (year < 1 || month < 1 || month > 12) {
            isValid = false;
        } else {
        int maxDays = 31;
        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                maxDays = 29; // Năm nhuận
            } else {
                maxDays = 28; // Năm không nhuận
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        }

        if (day < 1 || day > maxDays) {
            isValid = false;
        }
    }

    return isValid;
    } 
}
