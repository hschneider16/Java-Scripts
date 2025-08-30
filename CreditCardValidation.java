// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 8/28/25
// Checks the validity of a credit card number with Luhn's method

import java.util.Scanner;

public class CreditCardValidation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number: ");
        long cardNumber = input.nextLong();

        if (isValid(cardNumber)) {
            System.out.println(cardNumber + " is valid");
        } else {
            System.out.println(cardNumber + " is invalid");
        }

        input.close();
    }

    // Return true if card number is valid
    public static boolean isValid(long number) {
        int total = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return (total % 10 == 0);
    }

    // Get results from Step 2
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(numStr.charAt(i)) * 2;
            sum += getDigit(digit);
        }
        return sum;
    }

    // Return number if it is a single digit, otherwise, sum of the two digits
    public static int getDigit(int number) {
        return (number < 10) ? number : (number % 10 + number / 10);
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(numStr.charAt(i));
        }
        return sum;
    }

    // Return true if number d is prefix for number
    public static boolean prefixMatched(long number, int d) {
        String numStr = Long.toString(number);
        String prefix = Integer.toString(d);
        return numStr.startsWith(prefix);
    }

    // Return number of digits in d
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    // Return first k number of digits from number. If the * number of digits is less than k, return number.
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (k >= numStr.length()) {
            return number;
        }
        return Long.parseLong(numStr.substring(0, k));
    }
}
