/**
 * CS231 Final Project 10
 * Jiahao Ye
 * Prof. Layton
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Wumpus extends Agent
{
    Vertex location;
    boolean visible;
    boolean alive;
    private BufferedImage Wumpus;
    private BufferedImage DeadWumpus;

    public Wumpus(Vertex v)
    {
        super(v.getX(), v.getY());
        location = v;
        visible = false;
        alive = true;

        // read the pictures for different states of Wumpus
        try 
        {
        Wumpus = ImageIO.read(new File("Wumpus.png"));
        DeadWumpus = ImageIO.read(new File("DeadWumpus.png"));
        } 
    
        catch (IOException e) 
        {
            System.out.print("Image not found.");
        }
    }

    /** return Wumpus's location */
    public Vertex getLocation()
    {
        return location;
    }

    // /** set Wumpus's location */
    // public void setLocation(Vertex v)
    // {
    //     location = v;
    // }

    
    public boolean getVisible()
    {
        return this.visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    /** when wumpus is set to be visible
    *  draw the wumpus based on its state( alive, dead )  
    */
    public void draw(Graphics g, int scale)
    {
        // draw the room
        location.draw(g, scale);
        if (visible)
        {
            if (alive)// play victorious pose
            {
                g.drawImage(Wumpus, (int)location.getX()+13, (int)location.getY()+13, scale - 25, scale - 25, null);
            }
            else // dead
            {
                g.drawImage(DeadWumpus, (int)location.getX()+13, (int)location.getY()+13, scale - 25, scale - 25, null);
            }
        }
    }
}