/*---------------------------------------
|               Triangle                |
-----------------------------------------
| - side1: double                       |
| - side2: double                       |
| - side3: double                       |
-----------------------------------------
| + Triangle()                          |
| +Triangle(side1: double, side2: double, side3: double) |
| + getSide1(): double                   |
| + getSide2(): double                   |
| + getSide3(): double                   |
| + getPerimeter(): double               |
| + getArea(): double                    |
| + toString(): String                   |
-----------------------------------------*/

public class Triangle extends GeometricObject {

    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    // no-arg constructor for default triangle
    public Triangle() {
    }

    // constructor for triangle with specified side1, side2, and side3
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // accessor methods
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // calculate the area
    @Override
    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    // calculate perimeter
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    // returns string description of triangle
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2
                + " side3 = " + side3;
    }
}
