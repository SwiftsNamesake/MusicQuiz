/*
 *
 * MusicQuiz - Controller.java
 * Model view controller (IO, delegates to logic and GUI)
 *
 * Jonatan H Sundqvist
 * October 13 2014
 *
 */


/*
 *	TODO | - 
 *	       - 
 *
 *	SPEC | - 
 *	       - 
 *
 */


package MusicQuiz;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javafx.scene.media.MediaPlayer;



class Controller implements ActionListener {
	
	Chrome chrome;
	MusicQuiz quiz;

	static final MediaPlayer ding = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/ding.wav");
	static final MediaPlayer ding = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/ding.wav");

	public Controller() {
		System.out.println("Controller...");
		this.chrome = new Chrome(this);
		this.quiz = new MusicQuiz();
		this.loadQuestion();
	}


	public void actionPerformed(ActionEvent e) {
		// System.out.format("You clicked %s!\n", e.getActionCommand());
		char answer = e.getActionCommand().charAt(0);
		boolean correct = this.quiz.submitAnswer(answer);
		System.out.format("%c is %scorrect! You now have %d points.\n", answer, !correct ? " not" : "", this.quiz.retrieveScore());
		this.loadQuestion();
	}


	public void loadQuestion() {
		// Loads the current question into the view (GUI)
		this.chrome.ask(this.quiz.currentQuestion(), this.quiz.currentIndex()+1);
	}


	public void run() {

	}

	public static void main(String[] args) {
		System.out.println("Main...");
		Controller ctr = new Controller();
	}

}