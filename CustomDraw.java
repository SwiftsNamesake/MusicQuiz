/*
 * MusicQuiz - CustomDraw.java
 * Experimenting with custom GUI drawing routines
 *
 * Jonatan H Sundqvist
 * October 20 2014
 *
 */


/*
 *	TODO | - Record words (including numbers) and build a simple voice synthesizer
 *	       - Pronouncing numbers (15 203 -> "fifteen thousand two-hundred and three")
 *
 *	SPEC | - 
 *	       - 
 */


package MusicQuiz;


import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// Look at this ugly mess!
// I hate Java so much...
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.Vector;



class CustomDraw extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = 10203L;
	private static final Color[] colors = new Color[] { Color.red, Color.blue, Color.green, Color.green, Color.magenta, Color.green, Color.yellow };

	// private Draw[] drawables; // Drawing operations to be performed at each refresh
	private Vector<Draw> drawables; // Drawing operations to be performed at each refresh

	private int x, y, width, height;


	static <From, To> ArrayList<To> map(From[] list, Mapper<From, To> mapper) {
		ArrayList<To> result = new ArrayList<To>();
		for (int i = 0; i < list.length; i++) result.add(mapper.f(list[i]));
			return result;
	}
	

	private interface Mapper<From, To> {
		public To f(From arg);
	}


	private static Color chooseFromPalette() {
		// Chosoe a random colour from a pre-defined palette
		return colors[(int)(Math.random()*colors.length)];
	}


	private static Color randomColour() {
		return new Color((int)Math.random(), (int)Math.random(), (int)Math.random(), 1.0f);
	}


	private interface Draw {
		public void draw(Graphics g);
	}


	private interface Function {
		// NOTE: Due to numerous complaints regarding the excessively
		// long-winded 'IMissYouSomeMuchHaskellPleaseComeBackToMe', this
		// interface has been renamed to the more <em>pragmatic</em> (ugh!)
		// and prosaic 'Function'
		public double f(double x);
	}


	private interface Grapher {
		public void draw(Graphics g, double x, double y);
	}


	public static void plot(Graphics g, Function function, Grapher grapher, double intervalMin, double intervalMax, double step, double scaleX, double scaleY) {
		for (int i = 0; i < (intervalMax-intervalMin)/step; i++) {
			grapher.draw(g, (intervalMin+i*step)*scaleX, function.f(intervalMin+i*step)*scaleY);
		}
	}


	public CustomDraw() {
		
		// Properties
		width  = 21;
		height = 21;
		
		// Graphics
		this.drawables = new Vector<Draw>();

		this.drawables.add( g -> g.drawString("This is your dead granny. How are you, dear?", 26, 65) );
		this.drawables.add( g -> g.setColor(chooseFromPalette()));
		this.drawables.add( g -> g.fillRect(x, y, width, height));
		this.drawables.add( g -> plot(g, x -> Math.exp(x), (gr, x, y) -> {
			// Plot point callback
			// Utilities.debugMessage("Green: %f", (float)(1.0f+Math.sin(x*0.1)/2));
			// gr.setColor(new Color(Math.abs((float)(x-(int)x)), (float)Math.min(1.0f+(Math.sin(x*0.3)/2.0f), 1.0f), 0.0f, (float)(Math.abs(x)/(1.8*200))));
			gr.setColor(randomColour());
			int size = 10; // + (int)(20*Math.pow(1.01, x));
			gr.fillOval((int)x+20, (int)y+20, size, size);
		}, 0, 20.0, 0.02, 150, 150)); // Modify these values dynamically (function for creating variables bound to GUI elements)

		CustomDraw cd = this;

		// Events
		this.addMouseMotionListener(this);
		this.addMouseListener(new MouseAdapter() {
			boolean pressed = false;
			public void mousePressed(MouseEvent e) {

				this.pressed = !this.pressed; 

				width = 20 + (int)(Math.random()*150);
				height = width;

				x = e.getX()-width/2;
				y = e.getY()-height/2;

				cd.drawables.add(new Draw() {
					// TODO | Figure out how closures and namespaces work in Java (by reference, by value, etc),
					// particulary w.r.t. lambdas and anonymous classes.
					int posX = x, posY = y;
					public void draw(Graphics g) {
						g.drawString(String.format("X %d | Y %d", posX, posY), posX, posY-5);
					}
				});

				System.out.format("Number of drawables: %d\n", cd.drawables.size());
				repaint();
			}

			public void mouseReleased(MouseEvent e) { this.pressed = !this.pressed; System.out.println("Releasing..."); }


		});
	}


	public void mouseMoved(MouseEvent e) {
		// System.out.println("Moving...");
		// if (true) {
			// System.out.println("Dragging...");
		// }
	}


	public void mouseDragged(MouseEvent e) {
		this.drawables.add(new Draw() {
			int x = e.getX(), y = e.getY();
			int w = 20, h = 20;
			public void draw(Graphics g) {
				g.fillOval(x-w/2, y-h/2, w, h);
			}
		});
		repaint();
		// System.out.println("Dragging...");
	}


	public Dimension getPreferredSize() {
		return new Dimension(320, 320);
	}


	public void paintComponent(Graphics g) {
		//
		super.paintComponent(g);

		for (Draw d : this.drawables) {
			d.draw(g);
		}

	}


	public static void main(String[] args) {
		JFrame window = new JFrame("Graphics");
		window.add(new CustomDraw());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}