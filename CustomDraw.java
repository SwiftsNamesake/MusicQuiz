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
 *	       - A little cartoon Beethoven conducting the orchestra
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.Point;

import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Vector;



class CustomDraw extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = 10203L;
	private static final Color[] colors = new Color[] { Color.red, Color.blue, Color.green, Color.green, Color.magenta, Color.green, Color.yellow };

	private int mouseX = 0, mouseY = 0;

	// private Draw[] drawables; // Drawing operations to be performed at each refresh
	private Vector<Draw> drawables; // Drawing operations to be performed at each refresh
	// private Vector<Animated> animatables;

	private int x, y, width, height;

	// Animation
	private Timer timer;
	private int FPS;
	private boolean RUNNING = true;

	// Methods
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
		default public void animate(int delta) {

		}
	}


	// private interface Animated extends Draw {
		// public void animate(int delta);
	// }

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
		// this.animatables = new Vector<Animated>();

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
		
		this.setFocusable(true);
		this.requestFocusInWindow();

		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {  }

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					RUNNING = !RUNNING;
				}
			}

			public void keyReleased(KeyEvent e) { }


		});

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

		// Animation
		this.FPS = 30;
		// int posX = 0, posY = 0;
		// Draw ball = g -> g.fillOval(posX, posY, 20, 20);
		// this.drawables.add(ball);
		
		Draw mover = new Draw() {
			private int x = 0, y = 0;
			private int w = 20, h = 20;
			
			// private int mouseX = 0, mouseY = 0;
			// private Color eyeColour = 0;
			// private Color pupilColour = 0;

			// private 

			private int eyeRadius = 20;
			private int pupilRadius = 6;
			private int irisRadius = pupilRadius + 6;
			private int eyeDist = 80;

			private int noseRadius = 0;

			private void drawCircle(Graphics g, int xc, int yc, int radius, Color colour) {
				g.setColor(colour);
				g.fillOval(xc-radius, yc-radius, radius*2, radius*2);
			}

			private void drawOval(Graphics g, int xc, int yc, int wr, int hr, Color colour) {
				g.setColor(colour);
				g.fillOval(xc-wr, yc-hr, wr*2, hr*2);
			}


			private Point lookAt(int targetX, int targetY, int irisX, int irisY, int irisRadius, int pupilRadius) {
				// Calculates the offset between the pupil and iris centre points, based on a target point
				// TODO | Solve with similar triangles instead (?)
				// TODO | 
				int dx = targetX-irisX, dy = targetY-irisY;
				double hypotenuse = Math.sqrt(dx*dx + dy*dy);

				int rx = (int)((dx/hypotenuse)*(irisRadius-pupilRadius));
				int ry = (int)((dy/hypotenuse)*(irisRadius-pupilRadius));

				return new Point(rx, ry);

			}


			private void eye(int scleraR, int scleraX, int scleraY, int irisR, int pupilR, int irisColour, int targetX, int targetY) {
				Point offset = lookAt(targetX, targetY, scleraX, scleraY, irisR, pupilR);	// Pupil offset
				drawCircle(g, scleraX, scleraY, scleraR, Color.white);						// Sclera
				drawCircle(g, scleraX, scleraY, pupilRadius+6, irisColour);					// Iris
				drawCircle(g, scleraX+offset.x, scleraY+offset.y, pupilR, Color.black); 	// Pupil
			}


			public void draw(Graphics g) {

				int screenX = (x%900+50);
				int screenY = (int)(120*Math.sin(x/40.0f)+200);

				// Head
				drawOval(g, eyeDist/2+screenX, screenY+50, 105, 124, Color.pink);

				// Left eye
				Point leftPupil = lookAt(mouseX, mouseY, screenX, screenY, irisRadius, pupilRadius);	// Left pupil offset
				drawCircle(g, screenX, screenY, eyeRadius, Color.white);								// Sclera
				drawCircle(g, screenX, screenY, pupilRadius+6, colors[2]);								// Iris
				drawCircle(g, screenX+leftPupil.x, screenY+leftPupil.y, pupilRadius, Color.black); 		// Pupil

				// Right eye
				Point rightPupil = lookAt(mouseX, mouseY, eyeDist+screenX, screenY, irisRadius, pupilRadius);	// Right pupil offset
				drawCircle(g, eyeDist+screenX, screenY, eyeRadius, Color.white); 								// Sclera
				drawCircle(g, eyeDist+screenX, screenY, irisRadius, colors[2]);									// Iris
				drawCircle(g, eyeDist+screenX+rightPupil.x, screenY+rightPupil.y, pupilRadius, Color.black); 	// Pupil

				// Nose
				drawCircle(g, eyeDist/2+screenX, eyeDist/2+screenY, (int)(1.2*w), Color.red);
				drawCircle(g, eyeDist/2+screenX+12, eyeDist/2+screenY, (int)(0.35*w), Color.black);
				drawCircle(g, eyeDist/2+screenX-12, eyeDist/2+screenY, (int)(0.35*w), Color.black);

				// Mouth
				drawOval(g, eyeDist/2+screenX, screenY+120, 40, (int)(20+20*Math.abs(Math.sin(x/40.0f))), Color.black);

				// Tear
				Color tearColour = new Color(0, 100+(int)(155*Math.abs(Math.sin(x/60.0f))), 100+(int)(155*Math.abs(Math.sin(x/60.0f))), 200);
				drawOval(g, screenX, (screenY+eyeRadius)+8+(x/6)%80, 5, 8, tearColour);
				drawOval(g, screenX, (screenY+eyeRadius)+8+(x/6+23)%80, 5, 8, Color.cyan);
				drawOval(g, screenX, (screenY+eyeRadius)+8+(x/6+42)%80, 5, 8, Color.cyan);


			}

			public void animate(int delta) {
				x += 5;
			}
		};

		// this.animatables.add(mover);
		

		this.drawables.add(mover);

		this.timer = new Timer(1000/this.FPS, new ActionListener() {
			// float x = 0, y = 0;
			public synchronized void actionPerformed(ActionEvent e) {
				// x += 1;
				// y += 5;
				// drawables.add(g -> g.fillOval((int)x, (int)y, 20, 20));
				// repaint();
				if (RUNNING) {

					for (Draw drawable : drawables) {
						drawable.animate(1000/FPS);
					}

					repaint();

				}

			}
		});

		this.timer.start();

	}


	public void mouseMoved(MouseEvent e) {
		// System.out.println("Moving...");
		// if (true) {
			// System.out.println("Dragging...");
		// }
		mouseX = e.getX();
		mouseY = e.getY();
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