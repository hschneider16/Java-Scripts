// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 8/27/25
// Converts feet and meters and puts them in a table.

public class FootMeterConversion {

    // Convert feet to meters
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    // Convert meters to feet
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    public static void main(String[] args) {
        // Table header
        System.out.println("Feet     Meters     | Meters     Feet");
        System.out.println("--------------------------------------------------");

        // Table of feet to meters-meters to feet
        for (double feet = 1.0, meters = 20.0; feet <= 10.0; feet++, meters += 5) {
            System.out.printf("%-8.1f %-10.3f | %-10.1f %-7.3f\n", feet, footToMeter(feet), meters, meterToFoot(meters));
        }

    }
}
