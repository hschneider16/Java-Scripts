// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/24/25
// Check whether a Java source-code file has correct pairs of grouping symbols.

import java.io.*;
import java.util.Stack;

public class MatchGroupingSymbols {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols <filename>");
            return;
        }

        String filename = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // create a stack
            Stack<Character> stack = new Stack<>();

            // read chars from the file
            while ((line = br.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            System.out.println("Incorrect grouping symbols in " + filename);
                            return;
                        }
                    }
                }
            }

            // print results
            if (stack.isEmpty()) {
                System.out.println("All grouping symbols in " + filename + " match.");
            } else {
                System.out.println("Incorrect grouping symbols in " + filename);
            }

        // error if an exception is caught
        } catch (IOException e) {
            System.out.println("Couldn't read file. " + e.getMessage());
        }
    }
    
    // defines matching pairs
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') || (opening == '{' && closing == '}') || (opening == '[' && closing == ']');
    }
}
