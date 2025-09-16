// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/15/25
/* Test program for Rational.java that prompts 
the user to enter two rational numbers and 
then displays the results */

import java.math.BigInteger;
import java.util.Scanner;

public class TestRational {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // get user input for first rational number
        System.out.print("Enter the first rational number (numerator then denominator): ");
        BigInteger n1 = input.nextBigInteger();
        BigInteger d1 = input.nextBigInteger();
        Rational rational1 = new Rational(n1, d1);

        // get user input for second rational number
        System.out.print("Enter the second rational number (numerator then denominator): ");
        BigInteger n2 = input.nextBigInteger();
        BigInteger d2 = input.nextBigInteger();
        Rational rational2 = new Rational(n2, d2);

        // doing math with the rationals + displaying results
        System.out.println(rational1 + " + " + rational2 + " = " + rational1.add(rational2));
        System.out.println(rational1 + " - " + rational2 + " = " + rational1.subtract(rational2));
        System.out.println(rational1 + " * " + rational2 + " = " + rational1.multiply(rational2));
        System.out.println(rational1 + " / " + rational2 + " = " + rational1.divide(rational2));

        System.out.println(rational2 + " is " + rational2.doubleValue());
    }
}