// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 8/29/25
// Compares two arrays to determine if they are identical or not

import java.util.Scanner;

public class ArrayComparer {

    // Compare lengths
    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) {
            return false;
        }
        // Length counter
        for (int i = 0; i < m1.length; i++) {
            if (m1[i].length != m2[i].length) {
                return false;
            }
            // Compare similarity
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt for the 2 arrays
        System.out.print("Enter list1: ");
        int[][] list1 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list1[i][j] = input.nextInt();
            }
        }

        System.out.print("Enter list2: ");
        int[][] list2 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list2[i][j] = input.nextInt();
            }
        }

        // Check if they are identical
        if (equals(list1, list2)) {
            System.out.println("The two arrays are identical");
        } else {
            System.out.println("The two arrays are not identical");
        }
    }
}
