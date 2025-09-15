// GeometricObject.java: The abstract GeometricObject class
public abstract class GeometricObject {
  private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;

  /**Default construct*/
  protected GeometricObject() {
    dateCreated = new java.util.Date();
  }

  /**Construct a geometric object*/
  protected GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }

  /**Getter method for color*/
  public String getColor() {
    return color;
  }

  /**Setter method for color*/
  public void setColor(String color) {
    this.color = color;
  }

  /**Getter method for filled. Since filled is boolean,
     so, the get method name is isFilled*/
  public boolean isFilled() {
    return filled;
  }
  

  /**Setter method for filled*/
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  // Date created
  public java.util.Date getDateCreated() {
	return dateCreated;
  }

  	@Override
	public String toString() {
		return "created on " + dateCreated + "\ncolor: " + color +
			" and filled: " + filled;
	}

  /**Abstract method findArea*/
  public abstract double getArea();

  /**Abstract method getPerimeter*/
  public abstract double getPerimeter();
}
