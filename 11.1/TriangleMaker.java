// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/6/25
// Tests the Triangle class with user inputs, then displays the created triangle.

import java.util.Scanner;

public class TriangleMaker {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

        // prompt for 3 sides
        System.out.print("Input 3 sides for the triangle: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();
        
        // prompt for color
        System.out.print("Enter a color: ");
        String color = input.next();
        
        // prompt for if the triangle is filled or not
        System.out.print("Is the triangle filled? (true / false): ");
        boolean filled = input.nextBoolean();
        
        // creates the triangle with user's inputs
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);
        
        // displays results
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Color: " + triangle.getColor());
        System.out.println("Is the triangle filled?: " + triangle.isFilled());
    }
}