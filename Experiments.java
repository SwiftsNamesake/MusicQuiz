//
// Experiments
// Sandbox for testing out ideas and concepts
//
// Jonatan Sundqvist
//


package MusicQuiz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Frame;  // Using Frame class in package java.awt
import java.awt.Label;
import java.awt.*;


// import Question;

// A GUI program is written as a subclass of Frame - the top-level container
// This subclass inherits all properties from Frame, e.g., title, icon, buttons, content-pane
public class Experiments extends JFrame {

	public static final long serialVersionUID = 314159265;
	public static final String imgPath = "C:/Users/Jonatan/Desktop/Python/resources/images/";

	private JLabel question; // Holds question text
	private JPanel altPanel; // Holds alternatives

	// Constructor to setup the GUI components
	public Experiments() {
		/*......*/
		// Allocate an anonymous Label instance. "this" container adds the instance into itself.
		// You CANNOT reference an anonymous instance to carry out further operations.
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
		// this.createImageGrid();
		this.createQuestionnaire();
		this.pack();
		// this.setResizable(false);
		this.setVisible(true);
		// this.setSize(500, 500);
	}


	public void createImageGrid() {
		for (String url : new String[] { "cobblestone.png", "grass_side.png", "dirt.png",
													"planks_birch.png", "log_spruce.png", "sandstone_smooth.png",
													"stonebrick.png", "crafting_table_side.png", "hardened_clay_stained_magenta.png" }) {
			this.add(imageLabel(imgPath + "minecraft/" + url));
		}

	}


	public void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		//this.setLayout(new GridLayout(0,3));
		// this.setResizable(false);
		// this.setVisible(true);
	}


	public boolean ask(Question q, int num) {

		return false;
	}


	public void createQuestionnaire() {
		
		this.question = new JLabel();
		this.altsPanel = new JPanel();
		
		JRadioButton a = new JRadioButton();
		JRadioButton b = new JRadioButton();
		JRadioButton c = new JRadioButton();
		JRadioButton d = new JRadioButton();
		ButtonGroup alts = new ButtonGroup(); // Panel for alternatives
	
		alts.add(a); // TODO: Use normal buttons instead (?)
		alts.add(b);
		alts.add(c);
		alts.add(d);
		
		altsPanel.add(a);
		altsPanel.add(b);
		altsPanel.add(c);
		altsPanel.add(d);
		
		this.add(question);
		this.add(altsPanel);
		// this.add(alts); // No need to add ButtonGroup (?)
	}


	public JLabel imageLabel(String path) {
		System.out.println("ImageLabel");
		try {
			BufferedImage img = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(img);
			JLabel label = new JLabel(icon);
			return label;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void simpleApp() {
		System.out.println("simpleApp");
		this.add(new Label("Enter Name: "));
	}


	// The entry main() method
	public static void main(String[] args) {
		// Invoke the constructor (to setup the GUI) by allocating an instance
		Experiments fr = new Experiments();
	}
}