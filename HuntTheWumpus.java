/**
 * CS231 Final Project 10
 * Jiahao Ye
 * Prof. Layton
 * with the help of Alice Zhang
 */

import java.util.Random;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

 public class HuntTheWumpus
 {
   // fields
   private Landscape scape;
   private LandscapeDisplay display;
   private Graph graph;
   private Hunter hunter;
   private Wumpus wumpus;
   Vertex[][] grid;
   enum GameState { NORMAL, SHOOT, QUIT, GAMEOVER }
   private GameState state;

   /** initialization of the game  */
   public HuntTheWumpus()
   {
      scape = new Landscape(512, 512);
      display = new LandscapeDisplay(scape, 128);
      graph = new Graph();
      grid = new Vertex[4][4];

      // set the vertices (room)
      for (int i = 0; i < 4; i++)
      {
        for (int j = 0; j < 4; j++)
        {
          grid[i][j] = new Vertex(i * 128, j * 128);
          scape.addBackgroundAgent(grid[i][j]);
        }
      }

      // debug code
      // System.out.println("connecting");
      // // grid[0][0].connect(grid[0][1], Direction.EAST);
      // // grid[0][1].connect(grid[0][0], Direction.WEST);
      // graph.addEdge(grid[0][0], Direction.EAST, grid[0][1]);

      // System.out.println("grid[0][1].getNeighbors()");
      // System.out.println(grid[0][1].getNeighbors());
      // System.out.println("grid[0][0].getNeighbors()");
      // System.out.println(grid[0][0].getNeighbors());



      // this part is very tricky and took me for a while to figure out what should be done
      // I drew a graph to help me figure out how to set index in the loop

      // create horizontal edges paths
      for (int i = 0; i < 4; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          // graph.addEdge(grid[j-1][i], Direction.SOUTH, grid[j][i]);
          graph.addEdge(grid[j][i], Direction.EAST, grid[j+1][i]);
        }
      }

      // create vertical edges paths
      for (int i = 0; i < 4; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          // graph.addEdge(grid[i][j-1], Direction.EAST, grid[i][j]);
          graph.addEdge(grid[i][j], Direction.SOUTH, grid[i][j+1]);
        }
      }

      Random rnd = new Random();
      state = GameState.NORMAL;

      // generate hunter and wumpus's locations, avoiding the same place
      int hunterX = rnd.nextInt(4);
      int hunterY = rnd.nextInt(4);
      int wumpusX = rnd.nextInt(4);
      int wumpusY = rnd.nextInt(4);
      while ((wumpusX == hunterY) && (wumpusY == hunterY))
      {
        wumpusX = rnd.nextInt(4);
        wumpusY = rnd.nextInt(4);
      }

      // allocate hunter and wumpus locations and add them on the display
      hunter = new Hunter(grid[hunterX][hunterY]);
      wumpus = new Wumpus(grid[wumpusX][wumpusY]);
      scape.addForegroundAgent(hunter);
      scape.addForegroundAgent(wumpus);
      // update the cost
      graph.shortestPath(wumpus.getLocation());
      
      Control control = new Control();
      display.win.addKeyListener(control);
      display.win.setFocusable(true);
      display.win.requestFocus();

      display.quit.addActionListener(control);
      display.reset.addActionListener(control);
   }

   /** reset the game 
    * basically I just copied the the initialization part above
    * I tried to only reset the changed state to avoid copying the previous code but
    * I was not able to remove the existing humpus and wumpus, which could result in more
    * than 1 wumpus and hunter in the graph
    */
   public void reset()
   {
     // clear
    display.win.dispose();
    // rebuild
    scape = new Landscape(512, 512);
    display = new LandscapeDisplay(scape, 128);
    graph = new Graph();
    grid = new Vertex[4][4];

    // set the vertices (room)
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        grid[i][j] = new Vertex(i * 128, j * 128);
        scape.addBackgroundAgent(grid[i][j]);
      }
    }

    // debug code
    // System.out.println("connecting");
    // // grid[0][0].connect(grid[0][1], Direction.EAST);
    // // grid[0][1].connect(grid[0][0], Direction.WEST);
    // graph.addEdge(grid[0][0], Direction.EAST, grid[0][1]);

    // System.out.println("grid[0][1].getNeighbors()");
    // System.out.println(grid[0][1].getNeighbors());
    // System.out.println("grid[0][0].getNeighbors()");
    // System.out.println(grid[0][0].getNeighbors());



    // this part is very tricky and took me for a while to figure out what should be done
    // I drew a graph to help me figure out how to set index in the loop

    // create horizontal edges paths
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 3; j++)
      {
        // graph.addEdge(grid[j-1][i], Direction.SOUTH, grid[j][i]);
        graph.addEdge(grid[j][i], Direction.EAST, grid[j+1][i]);
      }
    }

    // create vertical edges paths
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 3; j++)
      {
        // graph.addEdge(grid[i][j-1], Direction.EAST, grid[i][j]);
        graph.addEdge(grid[i][j], Direction.SOUTH, grid[i][j+1]);
      }
    }

    Random rnd = new Random();
    state = GameState.NORMAL;

    // generate hunter and wumpus's locations, avoiding the same place
    int hunterX = rnd.nextInt(4);
    int hunterY = rnd.nextInt(4);
    int wumpusX = rnd.nextInt(4);
    int wumpusY = rnd.nextInt(4);
    while ((wumpusX == hunterY) && (wumpusY == hunterY))
    {
      wumpusX = rnd.nextInt(4);
      wumpusY = rnd.nextInt(4);
    }

    // allocate hunter and wumpus locations and add them on the display
    hunter = new Hunter(grid[hunterX][hunterY]);
    wumpus = new Wumpus(grid[wumpusX][wumpusY]);
    scape.addForegroundAgent(hunter);
    scape.addForegroundAgent(wumpus);
    // update the cost
    graph.shortestPath(wumpus.getLocation());
    
    Control control = new Control();
    display.win.addKeyListener(control);
    display.win.setFocusable(true);
    display.win.requestFocus();

    display.quit.addActionListener(control);
    display.reset.addActionListener(control);
   }

   private class Control extends KeyAdapter implements ActionListener
   {
     public void keyTyped(KeyEvent e)
     {
       if (state == GameState.NORMAL)
       {
          // "w" --> hunter up
          if(("" + e.getKeyChar()).equalsIgnoreCase("w"))
          {
            System.out.println("moved 1 step up");
            Vertex northNeighbor = hunter.getLocation().getNeighbor(Direction.NORTH);
            // avoid moving outside of the grid
            if (northNeighbor != null)
            {
              northNeighbor.setVisible(true);
              hunter.changeLocation(northNeighbor);
            }
          }

          // "s" --> hunter down
          else if (("" + e.getKeyChar()).equalsIgnoreCase("s"))
          {
            System.out.println("moved 1 step down");
            Vertex southNeighbor = hunter.getLocation().getNeighbor(Direction.SOUTH);
            if (southNeighbor != null)
            {
              southNeighbor.setVisible(true);
              hunter.changeLocation(southNeighbor);
            }
          }

          // "a" --> hunter left
          else if (("" + e.getKeyChar()).equalsIgnoreCase("a"))
          {
            System.out.println("moved 1 step left");
            Vertex westNeighbor = hunter.getLocation().getNeighbor(Direction.WEST);
            if (westNeighbor != null)
            {
              westNeighbor.setVisible(true);
              hunter.changeLocation(westNeighbor);
            }
          }

          // "d" --> hunter right
          else if (("" + e.getKeyChar()).equalsIgnoreCase("d"))
          {
            System.out.println("moved 1 step right");
            Vertex eastNeighbor = hunter.getLocation().getNeighbor(Direction.EAST);
            if (eastNeighbor != null)
            {
              eastNeighbor.setVisible(true);
              hunter.changeLocation(eastNeighbor);
            }
          }

          //hunter loses
          if (hunter.getLocation().equals(wumpus.getLocation()))
          {
            {
              // show wumpus
              wumpus.setVisible(true);
              // kill the hunter
              hunter.setAlive(false);
            }
            // update the game state
            state = GameState.GAMEOVER;
          }

          // prepare to shoot
          else if (("" + e.getKeyChar()).equalsIgnoreCase(" "))
          {
              System.out.println("prepared to shoot... press the space bar to shoot!");
              hunter.setArmed(true);
              state = GameState.SHOOT;
          }
       }

       else if (state == GameState.SHOOT)
       {
          // press space bar again to unarm the hunter
          if (("" + e.getKeyChar()).equalsIgnoreCase(" "))
          {
            System.out.println("Unarmed.");
            hunter.setArmed(false);
            state = GameState.NORMAL;
          }

          // "w" shoot up
          if (("" + e.getKeyChar()).equalsIgnoreCase("w"))
          {
            // hunter wins: if the wumpus is in the block one step sway upward
            if (hunter.getLocation().getNeighbor(Direction.NORTH).equals(wumpus.getLocation()))
              {
                // kill the wumpus
                wumpus.setAlive(false);
                // update wumpus's state to show the dead wumpus
                wumpus.setVisible(true);
              }
            else
            {
                System.out.print("You missed the shot.. It found you and killed you!");
                //hunter loses
                wumpus.setVisible(true);
                hunter.setAlive(false);
                wumpus.getLocation().setVisible(true);
            }        
            state = GameState.GAMEOVER;    
          }
          // "s" shoot down
          if (("" + e.getKeyChar()).equalsIgnoreCase("s"))
          {
            if (hunter.getLocation().getNeighbor(Direction.SOUTH).equals(wumpus.getLocation()))
              { // hunter wins
                wumpus.setAlive(false);
                wumpus.setVisible(true);
              }
            else
            {
                System.out.print("You missed the shot.. It found you and killed you!");
                //hunter loses
                wumpus.setVisible(true);
                hunter.setAlive(false);
                wumpus.getLocation().setVisible(true);
            }         
            state = GameState.GAMEOVER;   
          }

          // "a" shoot left
          if (("" + e.getKeyChar()).equalsIgnoreCase("a"))
          {
            if (hunter.getLocation().getNeighbor(Direction.WEST).equals(wumpus.getLocation()))
              {//hunter wins
                wumpus.setAlive(false);
                wumpus.setVisible(true);
              }
            else
            {
              System.out.print("You missed the shot.. It found you and killed you!");
              //hunter loses
              wumpus.setVisible(true);
              hunter.setAlive(false);
              wumpus.getLocation().setVisible(true);
            }         
            state = GameState.GAMEOVER;   
          }
          
          // "d" shoot right
          if (("" + e.getKeyChar()).equalsIgnoreCase("d"))
          {
            if (hunter.getLocation().getNeighbor(Direction.EAST).equals(wumpus.getLocation()))
              {// hunter wins
                wumpus.setAlive(false);
                wumpus.setVisible(true);
              }
            else
            {
              System.out.print("You missed the shot.. It found you and killed you!");
              //hunter loses
              wumpus.setVisible(true);
              hunter.setAlive(false);
              wumpus.getLocation().setVisible(true);
            }         
            state = GameState.GAMEOVER;   
          }
       }

       else if (state == GameState.GAMEOVER)
        {
          System.out.println("Game over");
          if (hunter.getAlive())
            System.out.println("YOU WIN!");
          return;
        }
     }

     public void actionPerformed(ActionEvent event)
     {
        if (event.getActionCommand().equalsIgnoreCase("Quit"))
          state = GameState.QUIT;
        if (event.getActionCommand().equalsIgnoreCase("Restart"))
          reset();
     }
   }
  
   public static void main(String[] argv) throws InterruptedException
   {
      HuntTheWumpus game = new HuntTheWumpus();
      // while (game.state != GameState.QUIT)
      // {
      //   game.display.repaint();
      //   try 
      //   {
      //     Thread.sleep(1500);                 //1500 milliseconds is one second.
      //   } 
      //   catch(InterruptedException ex) 
      //   {
      //     Thread.currentThread().interrupt();
      //   }
      // }
      while (game.state != GameState.QUIT)
      {
        game.display.repaint();
        // Thread.sleep(1500);  
      }
      // clear
      game.display.win.dispose();
   }

 }