package com.restaurant.model;
    // clasa Circle pentru obstacole si mese
	// scris 18 Nov Big 26
public class Circle implements Shape {
    private Point centru;
    private double raza;
    
    // constructori pentru diferite definitii ale obiectelor noastre 
    // scris 18 Nov Big 26
    public Circle (double raza, Point point)
    {
        this.centru = point;
        this.raza = raza;
    }
    
    public Circle (Point point, double raza)
    {
        this.centru = point;
        this.raza = raza;
    }
    
    public Circle (double xCoord, double yCoord, double raza)
    {
        this.centru = new Point(xCoord, yCoord);
        this.raza = raza;
    }

    // 	metoda esteInInterior verifica daca elementul (care implementeaza Shape) este in interiorul restaurantului (ca de nu da eroare)
    // scris 18 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor
    @Override
    public boolean esteInInterior(double lungime, double latime) {
        boolean returnedValue;

        returnedValue = centru.getX() - raza >= 0
                && centru.getY() - raza >= 0
                && centru.getX() + raza <= lungime
                && centru.getY() + raza <= latime;

        return returnedValue;
    }

    // 	metoda seSuprapune verifica daca elementele (care implementeaza Shape) sunt Suprapuse (ca de nu da eroare)
    // scris 18 Nov Big 26
    // updatat 26 Nov Big 26 : am facut sa avem un singur return pentru o structura mai usor de urmarit
    @Override
    public boolean seSuprapune(Shape alta) {

        boolean returnedValue;

        if (alta instanceof Circle c) {
            returnedValue = seSuprapuneCerc(c);
        }else if (alta instanceof Rectangle r) {
            returnedValue = seSuprapuneDreptunghi(r);
        } else{
            throw new IllegalArgumentException("Formă necunoscută: " + alta.getClass());
        }

        return returnedValue;
    }
    
    // metoda verifica daca contine un punct forma util la selectii
    // scris 19 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor
    @Override
    public boolean continePunct(double x, double y) {
        boolean returnedValue;

        double dx = centru.getX() - x;
        double dy = centru.getY() - y;
        
        returnedValue = dx*dx + dy*dy <= raza * raza;
        
        return returnedValue;
    }

    
    // 	metoda verifica daca se suprapune forma noastra (Circle) cu o alta forma Rectangle
    // scris 18 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor 
    
    public boolean seSuprapuneDreptunghi(Rectangle r) {
            boolean returnedValue;
        
            double cx = Math.max(r.xMin(), Math.min(centru.getX(), r.xMax()));
            double cy = Math.max(r.yMin(), Math.min(centru.getY(), r.yMax()));
            double dx = centru.getX() - cx;
            double dy = centru.getY() - cy;

            returnedValue = (dx*dx + dy*dy < raza * raza);

            return returnedValue;
    }
    
    // 	metoda verifica daca se suprapune forma noastra (Circle) cu o alta forma Circle
    // scris 18 Nov Big 26
    // modificat 26 Nov Big 26 : am pus returnedValue sa fie mai usor pentru debugging in viitor
    
    public boolean seSuprapuneCerc(Circle c) {
        boolean returnedValue;
        
        double dx = centru.getX() - c.centru.getX();
        double dy = centru.getY() - c.centru.getY();

        returnedValue = dx*dx + dy*dy < (raza + c.raza) * (raza + c.raza);

        return returnedValue;
    }
    
    // Getter scris in 26 Nov Big 26
    public double getX(){
        return this.centru.getX();
    }

    // Getter scris in 26 Nov Big 26
    public double getY(){
        return this.centru.getY();
    }

    // Getter scris in 26 Nov Big 26
    public Point getCenter(){
        return this.centru;
    }

    // Getter scris in 26 Nov Big 26
    public double getRadius(){
        return this.raza;
    }
}

//Author: Ash
