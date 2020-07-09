/**
 * CS231 Final Project 10
 * Jiahao Ye
 * Prof. Layton
 */

import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.LinkedList;

public class Landscape
{
    int width;
    int height;
    LinkedList<Agent> listForeground = new LinkedList<Agent>();
    LinkedList<Agent> listBackground = new LinkedList<Agent>();

    public Landscape ( int w, int h )
    {
        this.width = w;
        this.height = h;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void addForegroundAgent( Agent a )
    {
        listForeground.addFirst(a);
    }
    
    public void addBackgroundAgent( Agent a )
    {
        listBackground.addFirst(a);
    }

    /**
     * go through each agents and call each's draw method to draw the cell
     */
    public  void draw(Graphics g, int scale)
    {
        for ( Agent a : listBackground)
            a.draw(g, scale);

        for ( Agent a : listForeground)
            a.draw(g, scale);
    }
}