// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
// 8/31/25
// Creates polygons with specified number of sides, length, x, and y coordinates.

public class RegularPolygon {
    private int n;
    private double side;
    private double x;
    private double y;

    // polygon with default values
    public RegularPolygon() {
        this.n = 3;
        this.side = 1;
        this.x = 0;
        this.y = 0;
    }

    // creates polygon with specified number of sides and length
    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
        this.x = 0;
        this.y = 0;
    }

    // creates polygon with specified number of sides, length, x, and y
    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    // gets the number of sides
    public int getN() {
        return n;
    }

    // sets the number of sides
    public void setN(int n) {
        this.n = n;
    }

    // gets side length
    public double getSide() {
        return side;
    }

    // sets side length
    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // calculate perimeter
    public double getPerimeter() {
        return n * side;
    }

    // calculate area
    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }

    public static void main(String[] args) {
        RegularPolygon polygon1 = new RegularPolygon();
        RegularPolygon polygon2 = new RegularPolygon(6, 4);
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);

        System.out.println("Polygon 1's perimeter is: " + polygon1.getPerimeter() + ", and its area: " + polygon1.getArea());
        System.out.println("Polygon 2's perimeter is: " + polygon2.getPerimeter() + ", and its area: " + polygon2.getArea());
        System.out.println("Polygon 3's perimeter is: " + polygon3.getPerimeter() + ", and its area: " + polygon3.getArea());
    }
}