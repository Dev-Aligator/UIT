
public class USFlag {
    public static void main(String[] args) {
        String s1 = "* * * * * * ==================================\n * * * * *  ==================================";
        String s2 = "==============================================";
        for (int i = 0; i < 4; i++) {
            System.out.println(s1);
        }
        System.out.println("* * * * * * ==================================");
        for (int i = 0; i < 6; i++) {
            System.out.println(s2);
        }
    }
}

