package com.restaurant.model;

// clasa Circle pentru obstacole si mese
// scris 18 Nov Big 26
public interface Shape {
	// scris 18 Nov Big 26
    boolean seSuprapune(Shape alta);
    // scris 18 Nov Big 26
    boolean esteInInterior(double lungime, double latime); 
    // scris 19 Nov Big 26
    boolean continePunct(double x, double y);
    
    // metoda ce verifica daca un punct e in interiorul formei noastre
    // scris 19 nov Big 26
    public default boolean continePunct(Point point)
    {
    	if (point == null) 
    		return false;
    	
    	return continePunct(point.getX(), point.getY());
    }
}
