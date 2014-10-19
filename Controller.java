/*
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
 *	       - Timer, speed bonus
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

// import javafx.embed.swing.JFXPanel; // TODO: Fix this hack to make the MediaPlayer work


class Controller implements ActionListener {
	

	Chrome chrome;
	MusicQuiz quiz;

	private static final String[] positive =  new String[] { "Who wouldn't have known that",
															 "I'm a tiny bit impressed.",
															 "Feeling proud of yourself?" };

	private static final String[] negative =  new String[] { "Your ignorance sickens me.",
															 "I'm disappointed.",
															 "You accidentally hit the wrong button, right?",
															 "If it gets it wrong we eatsss it, no?" };

	
	public Controller() {
		// p = new JFXPanel();
		Utilities.debugMessage("Controller...");
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

		Utilities.debugMessage("%c is %scorrect! You now have %d point%s.", answer, !correct ? "not " : "", this.quiz.retrieveScore(), this.quiz.retrieveScore() == 1 ? "" : "s");

		// Load next question
		this.chrome.setFeedbackText(Utilities.choose(correct ? positive : negative)); // Choose and display random feedback message (positive for right answers, negative otherwise)
		R.play(correct ? R.DING : R.STRANGLE);
		this.loadQuestion();
	}


	public void loadQuestion() {
		// Loads the current question into the view (GUI)
		// this.quiz.currentQuestion().play();
		R.play(this.quiz.currentQuestion().mediaID, true);
		this.chrome.ask(this.quiz.currentQuestion(), this.quiz.currentIndex()+1);
	}


	public static void main(String[] args) {
		Utilities.debugMessage("Main...");
		Controller ctr = new Controller();
	}

}