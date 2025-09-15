public class Circle
		extends GeometricObject implements Comparable<Circle> {
	private double radius;

	public Circle() {
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, 
		String color, boolean filled) {
		this.radius = radius;
		setColor(color);
		setFilled(filled);
	}

	/** Return radius */
	public double getRadius() {
		return radius;
	}

	/** Set a new radius */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override /** Return area */
	public double getArea() {
		return radius * radius * Math.PI;
	}

	/** Return diameter */
	public double getDiameter() {
		return 2 * radius;
	}

	@Override /** Return perimeter */
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}

	@Override //"Two circle objects are equal if their radii are the same"
	public boolean equals(Object o) {
		return this.compareTo((Circle)o) == 0;
	}

	@Override // comparable
	public int compareTo(Circle o) {
		if (this.radius > o.radius)
			return 1;
		else if (this.radius < o.radius)
			return -1;
		else
			return 0;
	}
}