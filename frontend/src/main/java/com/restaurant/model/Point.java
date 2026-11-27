package com.restaurant.model;

public class Point {
    private double xCoord;
    private double yCoord;
    public Point(double newX, double newY)
    {
        this.xCoord = newX;
        this.yCoord = newY;
    }

    public void setX(int newX)
    {
        this.xCoord = newX;
    }

    // scris in 19 Nov Big 26

    public void setX(double newX)
    {
        this.xCoord = newX;
    }

    public void setY(int newY)
    {
        this.yCoord = newY;
    }

    // scris in 19 Nov Big 26

    public void setY(double newY)
    {
        this.yCoord = newY;
    }

    public double getX()
    {
        return xCoord;
    }

    public double getY()
    {
        return yCoord;
    }

}

//Author: Ash
