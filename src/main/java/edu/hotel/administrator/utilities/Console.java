package edu.hotel.administrator.utilities;

import java.util.Scanner;

public class Console {
     private final Scanner in = new Scanner(System.in);

    public String prompt(String label) {
        System.out.print(label);
        return in.nextLine().trim();
    }

    public int promptInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    public void header(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public void info(String msg) { System.out.println(msg); }
    public void ok(String msg) { System.out.println("✅ " + msg); }
    public void err(String msg) { System.out.println("❌ " + msg); }
}
