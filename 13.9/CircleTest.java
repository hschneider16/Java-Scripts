// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/6/25
// Tests the Circle class with user inputs, then displays the created circle.

import java.util.Scanner;

public class CircleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // first circle
        System.out.print("Enter a radius for Circle 1: ");
        double radius1 = scanner.nextDouble();
        System.out.print("Enter a color for Circle 1: ");
        String color1 = scanner.next();
        System.out.print("Is the circle filled? (true / false): ");
        boolean filled1 = scanner.nextBoolean();
        Circle circle1 = new Circle(radius1, color1, filled1);

        // second circle
        System.out.print("Enter a radius for Circle 2: ");
        double radius2 = scanner.nextDouble();
        System.out.print("Enter a color for Circle 2: ");
        String color2 = scanner.next();
        System.out.print("Is the circle filled? (true / false): ");
        boolean filled2 = scanner.nextBoolean();
        Circle circle2 = new Circle(radius2, color2, filled2);

		// display results
        System.out.println("1st circle radius: " + circle1.getRadius());
        System.out.println("2nd circle radius: " + circle2.getRadius());
		
		//"Two circle objects are equal if their radii are the same"
        System.out.println("The first circle is " + (circle1.equals(circle2) ? "" : "not ") +
            "equal to the second circle.");

    }
}