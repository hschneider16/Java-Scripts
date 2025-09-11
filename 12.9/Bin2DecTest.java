import java.util.Scanner;

public class Bin2DecTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// prompt for binary string
		System.out.print("Enter a binary number: ");
		try {
			System.out.println("Decimal equivalent: " + Bin2Dec.bin2Dec(input.nextLine()));
		}
		// throw binaryformatexception if not binary
		catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
	}
}