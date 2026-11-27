package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// refactor latime in inaltime in data de 26 Nov Big 2026
public class HartaRestaurant {
    private double lungime;
    private double inaltime;
    private ArrayList<Shape> obstacole;
    private ArrayList<Shape> mese;

    // Lungime = dimensiunea pe axa Ox (orizontală).
    // Lățime = dimensiunea pe axa Oy (verticală).

    // constructor HartaRestaurant (pentru viitor, fa-o ca sa fie clasa singleton)
    public HartaRestaurant(double newLungime, double newInaltime) {
        this.lungime = newLungime;
        this.inaltime = newInaltime;
        this.obstacole = new ArrayList<>();
        this.mese = new ArrayList<>();
    }

    // verifica daca obiectul nostru se suprapune cu o lista de alte obiecte toate implementand interfata SHAPE
    // Modificat in data de 26 NOV Big 26 pentru un cod mai curat
    private boolean verificaLoc(ArrayList<Shape> existente, Shape obiect) {
        boolean interior = obiect.esteInInterior(lungime, inaltime);
        
        boolean returnedValue;
        returnedValue = true;

        if (!interior) {
            System.out.println("RESPINS: in afara restaurantului");
            returnedValue = false;
        } else {
            for (Shape s : existente) {
                if (s.seSuprapune(obiect)){ 
                    returnedValue = false;
                    break;
                }
            }
        }
        return returnedValue;
    }

    // gaseste un element dupa coordonate (mun la selectia elementelor)
    public Shape gasesteElementLa(double x, double y) {
        for (Shape s : mese) {
            if (s.continePunct(x, y)) return s;
        }
        for (Shape s : obstacole) {
            if (s.continePunct(x, y)) return s;
        }
        return null;
    }

    // gaseste un element si il sterge
    // scris 19 Nov Big 26
    public boolean stergeElement(Shape element) {
        for (int i = 0; i < mese.size(); i++) {
            if (mese.get(i) == element) { mese.remove(i); return true; }
        }
        for (int i = 0; i < obstacole.size(); i++) {
            if (obstacole.get(i) == element) { obstacole.remove(i); return true; }
        }
        return false;
    }

    // da pop la un element dupa coordonate
    public boolean stergeElement(double x, double y) {
        Shape gasit = gasesteElementLa(x, y);
        if (gasit == null) return false;
        return stergeElement(gasit);   // presupunem că stergeElement(Shape) întoarce boolean
    }

    public void stergeToateElementele(List<Shape> elemente) {
        if (elemente == null || elemente.isEmpty()) return;
        stergeToateDin(mese, elemente);
        stergeToateDin(obstacole, elemente);
    }

    private static void stergeToateDin(List<Shape> lista, List<Shape> deSters) {
        lista.removeIf(s -> contineIdentic(deSters, s));
    }

    private static boolean contineIdentic(List<Shape> lista, Shape element) {
        
        for (Shape s : lista) {
            if (s == element) return true;
        }
        return false;
    }

    // da pop la un element (adica sterge si il returneaza pentru a nu scapa de el)
    // returneaza un obiect obtional deci ne arata ca poate returneaza un null
    // scris 19 Nov Big 26

    public Optional<Shape> popElement(Shape element) {
        if (element == null) return Optional.empty();

        for (int i = 0; i < mese.size(); i++) {
            if (mese.get(i) == element) {
                return Optional.of(mese.remove(i));
            }
        }
        for (int i = 0; i < obstacole.size(); i++) {
            if (obstacole.get(i) == element) {
                return Optional.of(obstacole.remove(i));
            }
        }
        return Optional.empty();
    }

    // da pop la un element dupa coordonate
    public Optional<Shape> popElement(double x, double y) {
        Shape gasit = gasesteElementLa(x, y);
        if (gasit == null) return Optional.empty();
        return popElement(gasit);
    }

    public List<Shape> popToate(List<Shape> elemente) {
        if (elemente == null || elemente.isEmpty()) return List.of();
        List<Shape> scoase = new ArrayList<>();
        for (Shape s : elemente) {
            popElement(s).ifPresent(scoase::add);
        }
        return scoase;
    }

    public List<Shape> elementeInAfara(double lungimeNoua, double inaltimeNoua) {
        List<Shape> ieșite = new ArrayList<>();
        for (Shape s : mese) {
            if (!s.esteInInterior(lungimeNoua, inaltimeNoua)) ieșite.add(s);
        }
        for (Shape s : obstacole) {
            if (!s.esteInInterior(lungimeNoua, inaltimeNoua)) ieșite.add(s);
        }
        return ieșite;
    }

    // modificat 26 Nov Big 26 : am pus returned value sa fie mai usor pentru debugging in viitor

    public int addObstacol(Shape obstacol) {

        int returnedValue;
        returnedValue = -1;

        if (verificaLoc(obstacole, obstacol) && verificaLoc(mese, obstacol)) {
            obstacole.add(obstacol);
            returnedValue = 0;
        }
        return returnedValue;
    }

    // metode pentru a adauga obstacol Rectangular pentru toti constructorii 
    // scris 18 Nov Big 26

    public int addObstacol(Point point1, Point point2)
    {
        Rectangle obstacol = new Rectangle(point1, point2);
        return addObstacol(obstacol);
    }

    public int addObstacol(Point point1, double coordX, double coordY)
    {
        Rectangle obstacol = new Rectangle(point1, coordX, coordY);
        return addObstacol(obstacol);
    }

    public int addObstacol( double coordX, double coordY,Point point1)
    {
        Rectangle obstacol = new Rectangle( coordX, coordY, point1);
        return addObstacol(obstacol);
    }

    public int addObstacol(double coordX1, double coordY1, double coordX2, double coordY2)
    {
        Rectangle obstacol = new Rectangle( coordX1, coordY1, coordX2, coordY2);
        return addObstacol(obstacol);
    }

    // metode pentru a adauga obstacol Circle pentru toti constructorii
    // scris 18 Nov Big 26

    public int addObstacol( double coordX, double coordY, double raza)
    {
        Circle obstacol = new Circle( coordX, coordY, raza);
        return addObstacol(obstacol);
    }

    public int addObstacol( Point point, double raza)
    {
        Circle obstacol= new Circle(point, raza);
        return addObstacol(obstacol);
    }

    public int addObstacol(double raza, Point point)
    {
        Circle obstacol = new Circle( raza, point);
        return addObstacol(obstacol);
    }

    public int addMasa(Shape masa) {
        if (verificaLoc(obstacole, masa) && verificaLoc(mese, masa)) {
            this.mese.add(masa);
            return 0;
        }
        return -1;
    }

    // metode pentru a adauga masa Rectangular pentru toti constructorii
    // scris 18 Nov Big 26

    public int addMasa(Point point1, Point point2)
    {
        Rectangle masa = new Rectangle(point1, point2);
        return addMasa(masa);
    }

    public int addMasa(Point point1, double coordX, double coordY)
    {
        Rectangle masa = new Rectangle(point1, coordX, coordY);
        return addMasa(masa);
    }

    public int addMasa( double coordX, double coordY,Point point1)
    {
        Rectangle masa = new Rectangle( coordX, coordY, point1);
        return addMasa(masa);
    }

    public int addMasa(double coordX1, double coordY1, double coordX2, double coordY2)
    {
        Rectangle masa = new Rectangle( coordX1, coordY1, coordX2, coordY2);
        return addMasa(masa);
    }

    // metode pentru a adauga masa Circle pentru toti constructorii
    // scris 18 Nov Big 26
    public int addMasa( double coordX, double coordY, double raza)
    {
        Circle masa = new Circle( coordX, coordY, raza);
        return addMasa(masa);
    }

    public int addMasa( Point point, double raza)
    {
        Circle masa = new Circle(point, raza);
        return addMasa(masa);
    }

    public int addMasa(double raza, Point point)
    {
        Circle masa = new Circle( raza, point);
        return addMasa(masa);
    }

    // metoda pentru a modifica dimensiunile restaurantului soft (adica nu sterge nimic fara sa il returneze)
    //  scris 19 Nov Big 26
    public List<Shape> setRestaurantDimensionsSoft(Double lungimeNoua, Double inaltimeNoua) {
        // null = nu modific acea dimensiune
        double l = (lungimeNoua == null) ? this.lungime : lungimeNoua;
        double w = (inaltimeNoua == null)  ? this.inaltime  : inaltimeNoua;

        // valori invalide -> excepție
        if (l <= 0 || w <= 0 || Double.isNaN(l) || Double.isNaN(w)
            || Double.isInfinite(l) || Double.isInfinite(w)) {
            throw new IllegalArgumentException("Dimensiunile trebuie să fie pozitive și finite");
        }

        this.lungime = l;
        this.inaltime = w;
        return popToate(elementeInAfara(l, w));
    }

    // metoda pentru a modifica dimensiunile restaurantului hard (distruge fara a se mai gandi in spate)
    //  scris 19 Nov Big 26
    public void setRestaurantDimensionsHard(Double lungimeNoua, Double inaltimeNoua) {
        // null = nu modific acea dimensiune
        double l = (lungimeNoua == null) ? this.lungime : lungimeNoua;
        double w = (inaltimeNoua == null)  ? this.inaltime  : inaltimeNoua;

        // valori invalide -> excepție
        if (l <= 0 || w <= 0 || Double.isNaN(l) || Double.isNaN(w)
            || Double.isInfinite(l) || Double.isInfinite(w)) {
            throw new IllegalArgumentException("Dimensiunile trebuie să fie pozitive și finite");
        }

        this.lungime = l;
        this.inaltime = w;
        stergeToateElementele(elementeInAfara(l, w));
    }

    // metode getter pentru a lua dimensiunile
    // scris 25 NOV Big 26
    public double getLungime() {
        return lungime;
    }

    // scris 25 NOV Big 26
    public double getInaltime() {
        return inaltime;
    }

    public List<Shape> getObstacole() {
        return obstacole;
    }

    public List<Shape> getMese() {
        return mese;
    }

}


//Author: Ash