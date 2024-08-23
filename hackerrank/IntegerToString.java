package hackerrank;

import java.util.Scanner;

public class IntegerToString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        String str = String.valueOf(n);
        System.out.println(str);

    }
}
