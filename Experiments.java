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
import javax.swing.border.*;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Frame;  // Using Frame class in package java.awt
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;


// import MusicQuiz.Question;

// A GUI program is written as a subclass of Frame - the top-level container
// This subclass inherits all properties from Frame, e.g., title, icon, buttons, content-pane
public class Experiments extends JFrame implements ActionListener {

	public static final long serialVersionUID = 314159265;
	public static final String imgPath = "C:/Users/Jonatan/Desktop/Python/resources/images/";

	private JLabel question; // Holds question text
	private JPanel altPanel; // Holds alternatives
	private JLabel feedback; // 
	// private JRadioButton a, b, c, d; // Alternatives
	private JButton a, b, c, d; // Alternatives
	
	// Constructor to setup the GUI components
	public Experiments() {

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
		// this.setLayout(new FlowLayout());
		this.setLayout(new GridLayout(0,1));
		//this.setLayout(new GridLayout(0,3));
		// this.setResizable(false);
		// this.setVisible(true);
	}


	public void ask(Question q, int num) {
		this.question.setText(String.format("%d. %s", num, q.questionText()));
		this.a.setText("A. " + q.retrieveAlternatives()[0]);
		this.b.setText("B. " + q.retrieveAlternatives()[1]);
		this.c.setText("C. " + q.retrieveAlternatives()[2]);
		this.d.setText("D. " + q.retrieveAlternatives()[3]);
		this.pack();
	}


	public void createQuestionnaire() {
		
		this.question = new JLabel();
		this.altPanel = new JPanel(new GridLayout(2,2)); // Panel for alternatives
		this.feedback = new JLabel("Feedback goes here...");

		this.question.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.altPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.feedback.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.a = new JButton();
		this.b = new JButton();
		this.c = new JButton();
		this.d = new JButton();

		ButtonGroup alts = new ButtonGroup();
	
		// Event handling
		// TODO: Refactor this hideous mess
		int i = 0;
		for (JButton btn : new JButton[] { this.a, this.b, this.c, this.d }) {
			btn.addActionListener(this);							// Add alternatives to button group
			btn.setActionCommand("ABCD".substring(i, i+1));	// 
			alts.add(btn);												// 
			altPanel.add(btn);										// 
			i++;
		}
		
		this.add(question);
		this.add(altPanel);
		this.add(feedback);
		// this.add(alts); // No need to add ButtonGroup (?)
	}


	public void actionPerformed(ActionEvent e) {
		System.out.format("You clicked %s!\n", e.getActionCommand());
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
		fr.ask(new Question("What is the capital of Sweden?", 0, "Stockholm", "Copenhagen", "the Hague", "Ottawa"), 1);
	}
}