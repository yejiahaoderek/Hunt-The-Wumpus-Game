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

public class Hunter extends Agent
{
    Vertex currLocation;
    boolean visible;
    boolean alive;
    boolean armed;
    private BufferedImage Hunter;
    private BufferedImage ArmedHunter;
    private BufferedImage DeadHunter;

    public Hunter(Vertex v)
    {
        super(v.getX(), v.getY());
        this.currLocation = v;
        this.currLocation.setVisible(true);
        visible = true;
        alive = true;
        armed = false;

        // read the pictures for different states of Wumpus
        try
        {
            Hunter = ImageIO.read(new File("Hunter.png"));
            ArmedHunter = ImageIO.read(new File("ArmedHunter.png"));
            DeadHunter = ImageIO.read(new File("DeadHunter.png"));
        } 
        catch (IOException e) 
        {
            System.out.print("Image not found.");
        }
    }

    /** set new location */
    public void changeLocation(Vertex v)
    {
        this.currLocation = v;
    }

    /** returen Hunter's current location */
    public Vertex getLocation()
    {
        return this.currLocation;
    }

    public void setAlive( boolean alive )
    {
        this.alive = alive;
    }

    public boolean getAlive()
    {
        return this.alive;
    }

    public void setVisible( boolean visible )
    {
        this.visible = visible;
    }

    public void setArmed( boolean armed)
    {
        this.armed = armed;
    }

    /** draw the hunter based on its state (alive and armed, alive and unarmed, dead)  */
    public void draw(Graphics g, int scale)
    {
        // draw the room
        currLocation.draw(g, scale);
        if (alive)
        {
            if (armed)
                g.drawImage(ArmedHunter, (int)currLocation.getX()+13, (int)currLocation.getY()+13, scale - 25, scale - 25, null);
            else // unarmed
                g.drawImage(Hunter, (int)currLocation.getX()+13, (int)currLocation.getY()+13, scale - 25, scale - 25, null);
        }
        else // killed 
        {
            g.drawImage(DeadHunter, (int)currLocation.getX()+13, (int)currLocation.getY()+13, scale - 25, scale - 25, null);
        }
    }

}