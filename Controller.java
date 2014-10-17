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
 *	TODO | - Win/lose
 *	       - Improved UI (eg. menus, animation, colours)
 *	       - Typographical improvements (fonts, styling, etc.)
 *
 *	SPEC | - 
 *	       - 
 *
 */


package MusicQuiz;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// import javafx.scene.media.MediaPlayer;

import javafx.embed.swing.JFXPanel; // TODO: Fix this hack to make the MediaPlayer work


class Controller implements ActionListener {
	
	Chrome chrome;
	MusicQuiz quiz;

	
	// static final MediaPlayer ding = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/ding.wav");
	// static final MediaPlayer strangle = Utilities.loadSound("C:/Users/Jonatan/Desktop/Java/MusicQuiz/strangle.wav");

	public Controller() {
		// p = new JFXPanel();
		System.out.println("Controller...");
		this.chrome = new Chrome(this);
		this.quiz = new MusicQuiz();
		this.loadQuestion();
	}


	public void actionPerformed(ActionEvent e) {
		// TODO: Refactor, clean up
		
		// Handle guess
		char answer = e.getActionCommand().charAt(0);
		R.stop(this.quiz.currentQuestion().mediaID); // Stop previous sound
		boolean correct = this.quiz.submitAnswer(answer); // NOTE: submitAnswer also changes the question index

		Utilities.debugMessage("%c is %scorrect! You now have %d point%s.\n", answer, !correct ? "not " : "", this.quiz.retrieveScore(), this.quiz.retrieveScore() == 1 ? "" : "s");

		// Load next question
		this.chrome.setFeedbackText(correct ? "Who wouldn't have known that?" : "I'm disappointed...");
		R.play(correct ? R.DING : R.STRANGLE);
		this.loadQuestion();
	}


	public void playEffect(String effect) {
		// TODO: Refactor, remove switch (?)
		switch (effect) {
			case "win": Utilities.play(R.ding); break;
			case "lose": Utilities.play(R.strangle); break;
			default: break;
		}
	}


	public void loadQuestion() {
		// Loads the current question into the view (GUI)
		// this.quiz.currentQuestion().play();
		R.play(this.quiz.currentQuestion().mediaID, true);
		this.chrome.ask(this.quiz.currentQuestion(), this.quiz.currentIndex()+1);
	}


	public void run() {

	}

	public static void main(String[] args) {
		System.out.println("Main...");
		Controller ctr = new Controller();
	}

}