import java.util.Scanner;
public class Exercise3{
    public static void main(String[] args){
        double edges[] = new double[3];
        Scanner scanner = new Scanner(System.in);
        for(int i = 0 ; i< 3; ++i){
            edges[i] = scanner.nextDouble();
        }
        scanner.close();
        if(edges[0] + edges[1] <= edges[2] || edges[1] + edges[2] <= edges[0] || edges[0] + edges[2] <= edges[1]){
            System.out.println("Ba canh nhap vao khong the la tam giac");
        }
        else{
            double p = (edges[0] + edges[1] + edges[2])/2;
            System.out.println("Chu vi tam giac: " + p*2);
            System.out.println("Dien tich tam giac: " + Math.pow(p*(p-edges[0])*(p-edges[1])*(p-edges[2]),0.5));
        }
    }
}
