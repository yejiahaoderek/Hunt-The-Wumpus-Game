/**
 * CS231 Final Project 10
 * Jiahao Ye
 * Prof. Layton
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Graphics;

public class Vertex extends Agent implements Comparable<Vertex>
{
    private Vertex[] neighbors;
    private int cost;
    private boolean marked;
    private boolean visible;

    // public static void main(String[] args)
    // {
        
    // }

    public Vertex(double x0, double y0)
    {
        super(x0,y0);
        neighbors = new Vertex[4];
        marked = false;
        visible = false;
    }

    /** return the opposite direction */
    public static Direction opposite(Direction d)
    {
        if (d ==  Direction.NORTH)
            return Direction.SOUTH;
        else if (d ==  Direction.SOUTH)
            return Direction.NORTH;
        else if (d ==  Direction.EAST)
            return Direction.WEST;
        else 
            return Direction.EAST;
    }

    /** connect the object Vertex to the other Vertex in the direction of dir */
    public void connect(Vertex other, Direction dir)
    {
        neighbors[dir.ordinal()] = other;
    }

    /** return the Vertex in the speficied direction */
    public Vertex getNeighbor(Direction dir)
    {
        return neighbors[dir.ordinal()];
    }

    /** return all the object's neighbors */
    public ArrayList<Vertex> getNeighbors()
    {
        ArrayList<Vertex> neighbors = new ArrayList<>();
        for (Vertex vertex: this.neighbors) 
        {
            if (vertex != null)
                neighbors.add(vertex);
        }
        return neighbors;
    }

    public boolean visible()
    {
        return visible;
    }

    public void setVisible( boolean visible )
    {
        this.visible = visible;
    }

    public boolean marked()
    {
        return this.marked;
    }

    public void setMarked(boolean marked)
    {
        this.marked = marked;
    }

    public int getCost()
    {
        return this.cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int compareTo(Vertex v)
    {
        return cost - v.getCost();
    }

	public void draw(Graphics g, int scale) {
		if (!this.visible) return;
		
		int xpos =(int)getX();
		int ypos = (int)getY();
		int border = 2;
		int half = scale / 2;
		int eighth = scale / 8;
		int sixteenth = scale / 16;
		
		// draw rectangle for the walls of the cave
		if (this.cost <= 2)
			// wumpus is nearby
			g.setColor(Color.red);
		else
			// wumpus is not nearby
			g.setColor(Color.black);
			
		g.drawRect(xpos + border, ypos + border, scale - 2, scale - 2);
		
		// draw doorways as boxes
        g.setColor(Color.black);
        
		if (this.neighbors[0] != null)//north 
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.neighbors[1] != null)//east
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                eighth + sixteenth, eighth);
		if (this.neighbors[2] != null)//south
			g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
				  eighth, eighth + sixteenth);
		if (this.neighbors[3] != null)//west
			g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
		
	}


    /** return a String containing the number of neighbors, the cost, and the marked flag */
    public String toString()
    {
        return "Marked? " + this.marked() + "/n" + "Number of neighbors: " + neighbors.length + "/n" + "cost: " + this.getCost();
    }

}    


/** Comparator class that uses cost as the value to be compared */
class VertexComparator implements Comparator<Vertex>{
    public int compare( Vertex v1, Vertex v2 ) {
        if (v1.getCost() > v2.getCost())
            return 1;
        if (v1.getCost() < v2.getCost())
            return -1;
        else
            return 0;
    }
}

enum Direction{
    NORTH, EAST, SOUTH, WEST
}