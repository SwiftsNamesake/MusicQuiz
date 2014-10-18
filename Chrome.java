/*
 *
 * MusicQuiz - Chrome.java
 * Descr...
 *
 * Jonatan H Sundqvist
 * October 13 2014
 *
 */


/*
 *	TODO | - Review nomenclature (eg. buttonText vs button)
 *	       - Scoreboard
 *
 *	SPEC | - 
 *	       - 
 *
 */


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



public class Chrome extends JFrame {

	public static final long serialVersionUID = 314159265;

	private JLabel question; // Holds question text
	private JPanel altPanel; // Holds alternatives
	private JLabel feedback; // 
	
	private JButton a, b, c, d; // Alternatives
	

	public Chrome(ActionListener listener) {
		System.out.println("Chrome");
		this.initialize();
		this.createQuestionnaire(listener);
		this.pack();
		this.setVisible(true);
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
		// Loads the question into the GUI
		this.setQuestionText(q.questionText(), num);
		this.setAlternatives(q.retrieveAlternatives());
		this.pack();
	}


	public void setAlternatives(String[] alternatives) {
		// TODO: Clear up name confusion (cf. related TODO)
		int i = 0;
		for (JButton alt : new JButton[] {this.a, this.b, this.c, this.d}) {
			// alt.setText(String.format("<html><b>%c.</b> %s</html>", "ABCD".charAt(i), alternatives[i]));
			alt.setText(String.format("<html><font color=#FFCC22>%c.</font> %s</html>", "ABCD".charAt(i), alternatives[i]));
			i++;
		}
	}


	public void setQuestionText(String q, int num) {
		this.question.setText(String.format("%d. %s", num, q));
	}


	public void setFeedbackText(String feedback) {
		this.feedback.setText(feedback);
	}


	public void createQuestionnaire(ActionListener listener) {
		
		this.question = new JLabel();
		this.altPanel = new JPanel(new GridLayout(2,2)); // Panel for alternatives
		this.feedback = new JLabel("Feedback goes here...");

		int padding = 5;

		this.question.setBorder(new EmptyBorder(padding, padding, padding, padding));
		this.altPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		this.feedback.setBorder(new EmptyBorder(padding, padding, padding, padding));

		this.a = new JButton();
		this.b = new JButton();
		this.c = new JButton();
		this.d = new JButton();

		ButtonGroup alts = new ButtonGroup();
	
		// Event handling
		// TODO: Refactor this hideous mess
		int i = 0;
		for (JButton btn : new JButton[] { this.a, this.b, this.c, this.d }) {
			btn.addActionListener(listener);				// Add alternatives to button group
			btn.setActionCommand("ABCD".substring(i, i+1));	// 
			alts.add(btn);									// 
			altPanel.add(btn);								// 
			i++;
		}
		
		this.add(question);
		this.add(altPanel);
		this.add(feedback);
		// this.add(alts); // No need to add ButtonGroup (?)
	}



	public JLabel imageLabel(String path) {
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


}