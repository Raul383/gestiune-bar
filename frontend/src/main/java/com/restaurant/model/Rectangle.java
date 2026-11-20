package com.restaurant.model;

public class Rectangle implements Shape{
	private Point corner1;
	private Point corner2;
	
	 // constructori pentru diferite definitii ale obiectelor noastre 
	
	Rectangle(Point newPoint1, Point newPoint2){
		this.corner1 = newPoint1;
		this.corner2 = newPoint2;
	}
	Rectangle(double corner1X, double corner1Y, double corner2X, double corner2Y){
		this.corner1 = new Point(corner1X, corner1Y);
		this.corner2 = new Point(corner2X, corner2Y);
	}
	Rectangle(Point newPoint1, double corner2X, double corner2Y){
		this.corner1 = newPoint1;
		this.corner2 = new Point(corner2X, corner2Y);
	}
	Rectangle(double corner1X, double corner1Y, Point newPoint2){
		this.corner1 = new Point(corner1X, corner1Y);
		this.corner2 = newPoint2;
	}
	
    // functii de ajutor
	// scris 18 Nov Big 26
    protected double xMin() { return Math.min(corner1.getX(), corner2.getX()); }
    protected double xMax() { return Math.max(corner1.getX(), corner2.getX()); }
    protected double yMin() { return Math.min(corner1.getY(), corner2.getY()); }
    protected double yMax() { return Math.max(corner1.getY(), corner2.getY()); }
    
	 // metoda esteInInterior verifica daca elementul (care implementeaza Shape) este in interiorul restaurantului (ca de nu da eroare (p.s. mult spus eroare dar nu returneaza true))
	 // scris 18 Nov Big 26
    @Override
    public boolean esteInInterior(double lungime, double latime) {
        return xMin() >= 0 && yMin() >= 0
            && xMax() <= latime && yMax() <= lungime;
    }
    
    // 	metoda seSuprapune verifica daca elementele (care implementeaza Shape) sunt Suprapuse (ca de nu da eroare)
    // scris 18 Nov Big 26
    
    @Override
    public boolean seSuprapune(Shape alta) {
        if (alta instanceof Rectangle r) {
            return seSuprapuneDreptunghi(r);
        }
        if (alta instanceof Circle c) {
            return c.seSuprapune(this);   // delegi cercului (el știe dreptunghi)
        }
        throw new IllegalArgumentException("Formă necunoscută: " + alta.getClass());
    }
    
    // metoda ce verifica daca un punct e in interiorul formei noastre
    // scris 18 Nov Big 26
    @Override
    public boolean continePunct(double x, double y) {
        return x >= xMin() && x <= xMax()
            && y >= yMin() && y <= yMax();
    }
    
    
    // 	metoda verifica daca se suprapune forma noastra (Rectangle) cu o alta forma Rectangle
    // scris 18 Nov Big 26
    
    public boolean seSuprapuneDreptunghi(Rectangle r) {
        return !(xMax() <= r.xMin() || xMin() >= r.xMax()
              || yMax() <= r.yMin() || yMin() >= r.yMax());
    }
	
	public Point getCorner1()
	{
		return corner1;
	}
	
	public Point getCorner2() 
	{
		return corner2;
	}
}

//Author: Ash
