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

import javafx.embed.swing.JFXPanel; // TODO: Fix this hack to make the MediaPlayer work


class Controller implements ActionListener {
	
	Chrome chrome;
	MusicQuiz quiz;

	JFXPanel p;
	
	// static final MediaPlayer ding = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/ding.wav");
	// static final MediaPlayer strangle = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/strangle.wav");

	public Controller() {
		p = new JFXPanel();
		System.out.println("Controller...");
		this.chrome = new Chrome(this);
		this.quiz = new MusicQuiz();
		this.loadQuestion();
	}


	public void actionPerformed(ActionEvent e) {
		// TODO: Refactor, clean up
		// System.out.format("You clicked %s!\n", e.getActionCommand());
		char answer = e.getActionCommand().charAt(0);
		boolean correct = this.quiz.submitAnswer(answer);
		System.out.format("%c is %scorrect! You now have %d point%s.\n", answer, !correct ? "not " : "", this.quiz.retrieveScore(), this.quiz.retrieveScore() == 1 ? "" : "s");
		// (correct ? ding : strangle).play();
		this.chrome.setFeedbackText(correct ? "Who wouldn't have known that?" : "I'm disappointed...");
		this.playEffect(correct ? "win" : "lose");
		this.loadQuestion();
	}


	public void playEffect(String effect) {
		// TODO: Refactor, remove switch (?)
		switch (effect) {
			case "win": Utilities.playSound("ding.wav"); break;
			case "lose": Utilities.playSound("strangled.wav"); break;
			default: break;
		}
	}


	public void loadQuestion() {
		// Loads the current question into the view (GUI)
		this.chrome.ask(this.quiz.currentQuestion(), this.quiz.currentIndex()+1);
		this.quiz.currentQuestion().play();
	}


	public void run() {

	}

	public static void main(String[] args) {
		System.out.println("Main...");
		Controller ctr = new Controller();
	}

}