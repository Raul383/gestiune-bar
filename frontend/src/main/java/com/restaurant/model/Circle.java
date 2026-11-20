package com.restaurant.model;
    // clasa Circle pentru obstacole si mese
	// scris 18 Nov Big 26
public class Circle implements Shape {
    private Point centru;
    private double raza;
    
    // constructori pentru diferite definitii ale obiectelor noastre 
    // scris 18 Nov Big 26
    Circle (double raza, Point point)
    {
    	this.centru = point;
    	this.raza = raza;
    }
    
    Circle (Point point, double raza)
    {
    	this.centru = point;
    	this.raza = raza;
    }
    
    Circle (double xCoord, double yCoord, double raza)
    {
    	this.centru = new Point(xCoord, yCoord);
    	this.raza = raza;
    }

    // 	metoda esteInInterior verifica daca elementul (care implementeaza Shape) este in interiorul restaurantului (ca de nu da eroare)
    // scris 18 Nov Big 26
    @Override
    public boolean esteInInterior(double lungime, double latime) {
        return centru.getX() - raza >= 0 && centru.getY() - raza >= 0
            && centru.getX() + raza <= latime && centru.getY() + raza <= lungime;
    }

    // 	metoda seSuprapune verifica daca elementele (care implementeaza Shape) sunt Suprapuse (ca de nu da eroare)
    // scris 18 Nov Big 26
    @Override
    public boolean seSuprapune(Shape alta) {
        if (alta instanceof Circle c) {
            return seSuprapuneCerc(c);
        }
        if (alta instanceof Rectangle r) {
            return seSuprapuneDreptunghi(r);
        }
        throw new IllegalArgumentException("Formă necunoscută: " + alta.getClass());
    }
    
    // metoda verifica daca contine un punct forma util la selectii
    // scris 19 Nov Big 26
    @Override
    public boolean continePunct(double x, double y) {
        double dx = centru.getX() - x;
        double dy = centru.getY() - y;
        return dx*dx + dy*dy <= raza * raza;
    }

    
    // 	metoda verifica daca se suprapune forma noastra (Circle) cu o alta forma Rectangle
    // scris 18 Nov Big 26
    
    public boolean seSuprapuneDreptunghi(Rectangle r) {
        
            double cx = Math.max(r.xMin(), Math.min(centru.getX(), r.xMax()));
            double cy = Math.max(r.yMin(), Math.min(centru.getY(), r.yMax()));
            double dx = centru.getX() - cx;
            double dy = centru.getY() - cy;
            return dx*dx + dy*dy < raza * raza;
    }
    
    // 	metoda verifica daca se suprapune forma noastra (Circle) cu o alta forma Circle
    // scris 18 Nov Big 26
    
    public boolean seSuprapuneCerc(Circle c) {
        
    	double dx = centru.getX() - c.centru.getX();
        double dy = centru.getY() - c.centru.getY();
        return dx*dx + dy*dy < (raza + c.raza) * (raza + c.raza);
}
    
}

//Author: Ash
