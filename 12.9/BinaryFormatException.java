// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/10/25
// Implements bin2Dec to throw a BinaryFormatException if the string isnt binary.

public class BinaryFormatException extends NumberFormatException {
	private final String binaryString;

	public BinaryFormatException(String binaryString) {
		super("Not a binary number");
		this.binaryString = binaryString;
	}

	public String getBinaryString() {
		return binaryString;
	}
}