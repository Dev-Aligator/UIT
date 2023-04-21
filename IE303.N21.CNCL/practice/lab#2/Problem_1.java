import java.util.Scanner;
public class Problem_1{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap toa do diem A: ");
        Point A = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Nhap toa do diem B: ");
        Point B = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.println("Khoang cach A, B: " + A.distanceTo(B));

        System.out.print("Nhap phan so a: ");
        PhanSo a = new PhanSo(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Nhap phan so b: ");
        PhanSo b = new PhanSo(scanner.nextDouble(), scanner.nextDouble());
        System.out.println("Tong hai phan so: " + a.cong(b));
        System.out.println("Hieu hai phan so: " + a.tru(b));
        System.out.println("Tich hai phan so: " + a.nhan(b));
        System.out.println("Thuong hai phan so: " + a.chia(b));
       
        // Square
        System.out.print("Hinh vuong\nNhap diem tren cung ben trai: ");
        Point topL = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Nhap diem duoi cung ben phai: ");
        Point bottomR = new Point(scanner.nextDouble(), scanner.nextDouble());
        Square square = new Square(topL, bottomR);
        System.out.println("Square" + square.toString());

        // Circle 
        System.out.print("Hinh tron\nNhap tam duong tron: ");
        Point center = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Nhap ban kinh: ");
        Double radius = scanner.nextDouble();
        Circle circle = new Circle(center, radius);
        System.out.println(circle.toString());
        
        // Triangle
        System.out.print("Hinh tam giac\nNhap 3 dinh cua tam giac: ");
        Point p1 = new Point(scanner.nextDouble(), scanner.nextDouble());
        Point p2 = new Point(scanner.nextDouble(), scanner.nextDouble());
        Point p3 = new Point(scanner.nextDouble(), scanner.nextDouble());
        Triangle triangle = new Triangle(p1,p2,p3);
        System.out.println(triangle.toString());
        
        // Rectangle 
        System.out.print("hinh chu nhat\nNhap diem tren cung ben trai: ");
        topL = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Nhap diem duoi cung ben phai");
        bottomR = new Point(scanner.nextDouble(), scanner.nextDouble());
        Rectangle rectangle = new Rectangle(topL, bottomR);
        System.out.println("Rectangle" + rectangle.toString());

        scanner.close();
        
    }

}

interface Shape{
    public double getArea();
    public double getPerimeter();
}

class Square extends Rectangle implements Shape{
    public Square(Point topLeft, Point bottomRight){
        super(topLeft, bottomRight);
    }
}

class Circle implements Shape {
    private Point center;
    private double radius;

    // constructor
    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public String toString(){
        return "Circle: " + center.toString() + radius + " Area: " + getArea() + " Perimeter: " + getPerimeter();
    }
}
class Rectangle implements Shape{
    private Point topLeft;
    private Point bottomRight;
    public Rectangle(){

    }
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    @Override
    public double getArea(){
        return Math.abs(topLeft.getX() - bottomRight.getX())*Math.abs(topLeft.getY() - bottomRight.getY());
    }
    @Override
    public double getPerimeter(){
        return 2*(Math.abs(topLeft.getX()-bottomRight.getX()) + Math.abs(topLeft.getY() - bottomRight.getY()));
    }
    public String toString(){
        return topLeft.toString() + new Point(topLeft.getX(), bottomRight.getY()).toString() + new Point(bottomRight.getX(), topLeft.getY()).toString() + bottomRight.toString() + " Area: " + this.getArea() + " Perimeter: " + this.getPerimeter();
    }
 
}

class Triangle implements Shape{
    private Point a,b,c;

    public Triangle(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double getPerimeter(){
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a);
    }
    @Override
    public double getArea(){
        double p = this.getPerimeter()/2;
        return Math.sqrt(p*(p-a.distanceTo(b)*(p-b.distanceTo(c)*(p-c.distanceTo(a)))));

    }
    public String toString(){
        return "Triangle: " + a.toString() + b.toString() + c.toString() + " Area: " + this.getArea() + " Perimeter: " + this.getPerimeter();
    }
}
class Point {
    private double x;
    private double y;

    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Point p) {
        double dx = x - p.getX();
        double dy = y - p.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    public String toString(){
        return "(" + x + ", " + y + ")"; 
    }
        
}
class PhanSo {
    private double tuSo;
    private double mauSo;

    public PhanSo(double tuSo, double mauSo) {
        this.tuSo = tuSo;
        this.mauSo = mauSo;
    }

    public double getTuSo() {
        return tuSo;
    }

    public void setTuSo(int tuSo) {
        this.tuSo = tuSo;
    }

    public double getMauSo() {
        return mauSo;
    }

    public void setMauSo(double mauSo) {
        this.mauSo = mauSo;
    }

    public PhanSo cong(PhanSo ps) {
        double ts = this.tuSo * ps.mauSo + ps.tuSo * this.mauSo;
        double ms = this.mauSo * ps.mauSo;
        return new PhanSo(ts, ms).rutGon();
    }

    public PhanSo tru(PhanSo ps) {
        double ts = this.tuSo * ps.mauSo - ps.tuSo * this.mauSo;
        double ms = this.mauSo * ps.mauSo;
        return new PhanSo(ts, ms).rutGon();
    }

    public PhanSo nhan(PhanSo ps) {
        double ts = this.tuSo * ps.tuSo;
        double ms = this.mauSo * ps.mauSo;
        return new PhanSo(ts, ms).rutGon();
    }

    public PhanSo chia(PhanSo ps) {
        double ts = this.tuSo * ps.mauSo;
        double ms = this.mauSo * ps.tuSo;
        return new PhanSo(ts, ms).rutGon();
    }

    public PhanSo rutGon() {
        double gcd = timUSCLN(this.tuSo, this.mauSo);
        double tu = this.tuSo / gcd;
        double mau = this.mauSo / gcd;
        return new PhanSo(tu,mau);
    }

    public double timUSCLN(double a, double b) {
        if (b == 0) {
            return a;
        }
        return timUSCLN(b, a % b);
    }

    public String toString() {
        return this.tuSo + "/" + this.mauSo;
    }
}
    
