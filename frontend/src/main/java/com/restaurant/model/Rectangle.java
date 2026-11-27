package com.restaurant.model;

public class Rectangle implements Shape{
    private Point corner1;
    private Point corner2;

        // constructori pentru diferite definitii ale obiectelor noastre 

    public Rectangle(Point newPoint1, Point newPoint2){
        this.corner1 = newPoint1;
        this.corner2 = newPoint2;
    }
    public Rectangle(double corner1X, double corner1Y, double corner2X, double corner2Y){
        this.corner1 = new Point(corner1X, corner1Y);
        this.corner2 = new Point(corner2X, corner2Y);
    }
    public Rectangle(Point newPoint1, double corner2X, double corner2Y){
        this.corner1 = newPoint1;
        this.corner2 = new Point(corner2X, corner2Y);
    }
    public Rectangle(double corner1X, double corner1Y, Point newPoint2){
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
        // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor
    @Override
    public boolean esteInInterior(double lungime, double inaltime) {
        boolean returnedValue;
        
        returnedValue = xMin() >= 0 && yMin() >= 0
                    && xMax() <= lungime && yMax() <= inaltime;

        return returnedValue;
    }

    // 	metoda seSuprapune verifica daca elementele (care implementeaza Shape) sunt Suprapuse (ca de nu da eroare)
    // scris 18 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor

    @Override
    public boolean seSuprapune(Shape alta) {
        boolean returnedValue;

        if (alta instanceof Rectangle r) {
            returnedValue = seSuprapuneDreptunghi(r);
        }else if (alta instanceof Circle c) {
            returnedValue = c.seSuprapune(this);   // delegi cercului (el știe dreptunghi)
        } else{
            throw new IllegalArgumentException("Formă necunoscută: " + alta.getClass());
        }

        return returnedValue;
    }

    // metoda ce verifica daca un punct e in interiorul formei noastre
    // scris 18 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor
    @Override
    public boolean continePunct(double x, double y) {

        boolean returnedValue;

        returnedValue = x >= xMin() && x <= xMax()
                     && y >= yMin() && y <= yMax();

        return returnedValue;
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

    // Getter scris in 26 Nov Big 26
    public double getXMin() {
        return xMin();
    }

    // Getter scris in 26 Nov Big 26
    public double getXMax() {
        return xMax();
    }

    // Getter scris in 26 Nov Big 26
    public double getYMin() {
        return yMin();
    }

    // Getter scris in 26 Nov Big 26
    public double getYMax() {
        return yMax();
    }

    // Getter scris in 26 Nov Big 26
    public double getLength() {
        return yMax() - yMin();
    }

    // Getter scris in 26 Nov Big 26
    public double getWidth() {
        return xMax() - xMin();
    }

}

//Author: Ash
