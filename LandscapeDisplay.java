/*
  Template originally created by Bruce A. Maxwell

  Jiahao Ye
  with the help of Alice Zhang
 */

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

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;


public class LandscapeDisplay 
{
    protected JFrame win;
	/** holds the drawing canvas **/
	private Landscape scape;
    private LandscapePanel canvas;
	private int scale;
	private JPanel bottom;
	protected JButton quit;
	protected JButton reset;

	 
	public LandscapeDisplay( Landscape scape, int scale ) 
	{
		this.scale = scale;
		this.scape = scape;

		// set the title of the window
		this.win = new JFrame("Hunt The Wumpus Game");

		win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

		// create a panel to display the landscape
		this.canvas = new LandscapePanel(this.scape.getWidth(), this.scape.getHeight());

		quit = new JButton("Quit");
		reset = new JButton("Restart");
		bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.add( quit );
		bottom.add( reset );
			
		this.win.add( bottom, BorderLayout.SOUTH);
		this.win.pack();

		win.add(this.canvas, BorderLayout.CENTER);
		win.add(bottom, BorderLayout.SOUTH);
		win.pack();
		win.setVisible(true);
	}


	private class LandscapePanel extends JPanel 
	{
		
	/**
	 * Creates the panel
	 * @param height the height of the panel in pixels
	 * @param width the width of the panel in pixels
	 **/
		public LandscapePanel(int height, int width) 
		{
			super();
			this.setPreferredSize( new Dimension( width, height ) );
			this.setBackground(Color.white);
		}

		/**
		 * Method overridden from JComponent that is responsible for
		 * drawing components on the screen.  The supplied Graphics
		 * object is used to draw.
		 * 
		 * @param g		the Graphics object used for drawing
		 */
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			scape.draw( g, scale );
		}
    } // end class

	public void repaint()
	{
		this.win.repaint();
	}

	    // // test codes
		// public static void main(String[] args) throws InterruptedException {
        
		// 	Landscape scape = new Landscape(512, 512);
		// 	Vertex v = new Vertex(0, 0);
		// 	v.setVisible(true);
		// 	Hunter hunter = new Hunter(v);
			
		// 	scape.addBackgroundAgent(v);
		// 	scape.addForegroundAgent(hunter);
			
		// 	LandscapeDisplay display = new LandscapeDisplay(scape, 64);
		// 	display.repaint();
		// }
	
} // end class Basic