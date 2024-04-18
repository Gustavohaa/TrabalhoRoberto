package Service;

import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);

    public static int lerInteiro() {
        return sc.nextInt();
    }

    public static String lerString() {
        return sc.nextLine();
    }

}