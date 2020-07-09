/**
 * CS231 Project 5
 * Jiahao Ye
 * Prof. Layton
 */

import java.awt.Graphics;

public class Agent
{
    double x;
    double y;

    public Agent(double x0, double y0)
    {
        x = x0;
        y = y0;
    }

    /** 
     * return the x value 
     */
    public double getX()
    {
        return x;
    }

    /** 
     * return the y value 
     */
    public double getY()
    {
        return y;
    }

    /** 
     * set x value 
     */
    public void setX( double newX )
    {
        x = newX;
    }

    /** 
     * set y value 
     */
    public void setY( double newY )
    {
        y = newY;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
 
    public void updateState( Landscape scape ){}

    public void draw(Graphics g, int scale){}

    /**
     * default getCategory method, to be override in CategorizedSocialAgent
     */
    public int getCategory()
    {
        return 0;
    }

}