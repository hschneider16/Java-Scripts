// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/10/25
// Implements hex2Dec method to throw a NumberFormatException if the string isnt a hex.

public class HexFormatException extends NumberFormatException {
	private final String hex;

	public HexFormatException(String hex) {
		super("Not a hex number");
		this.hex = hex;
	} 

	public String getHex() {
		return hex;
	}
}