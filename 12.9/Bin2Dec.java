// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 9/10/25
/* Creates the bin2Dec method to convert a binary string into 
a decimal number, and throws an exception if the string is not binary. */

public class Bin2Dec {
	// method for converting binary string to decimal
	public static int bin2Dec(String binaryString) throws BinaryFormatException {
		int decimal = 0;
		
		for (int i = 0, j = binaryString.length() - 1; 
			i < binaryString.length(); i++, j--) {
			    
			// check if character is a binary string
			if (binaryString.charAt(i) < '0' || binaryString.charAt(i) > '1')
				// exception if non-binary string found
				throw new BinaryFormatException(binaryString);
				
			// calculate the decimal
			decimal += (Integer.parseInt(String.valueOf(binaryString.charAt(i)))) 
				* Math.pow(2, j);
		}
		return decimal;
	} 
}