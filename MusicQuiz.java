/*
 *
 * MusicQuiz - MusicQuiz.java
 * My version of Jay's quiz
 *
 * Jonatan H Sundqvist
 * October 5 2014
 *
 */


/*
 *	TODO | - Come up with a better UI (GUI or click inside console, etc.)
 *	       - Rules and options such as difficulty, genres, (weighted) scores, etc.
 * 
 *	SPEC | - 
 *	       - 
 *
 */


package MusicQuiz;

import java.util.Scanner;
import java.util.Arrays;


class MusicQuiz {

	private Question[] questions;	//
	private int current;			// Index of the current question
	private int score;				// Current score


	public MusicQuiz() {
		final String prefix = "What is the capital of ";
		this.current = 0;
		this.score = 0;
		this.questions = new Question[] {
			// Geography
			new Question(prefix + "Sweden?", 	    0, 	"Stockholm", 	"Copenhagen", 	"the Hague", 	"Ottawa"),
			new Question(prefix + "Burma?",		    0, 	"Naypyidaw", 	"Laos", 		"Asmara", 		"Canberra"),
			new Question(prefix + "Peru?",	 	    2, 	"Moscow", 		"Ulan Bator", 	"Lima", 		"Santiago"),
			new Question(prefix + "Cote d'Ivoire?", 1, 	"Cairo", 		"Yamoussoukro", "Belgrade", 	"Dublin"),

			// Music
			// TODO: Research Unicode
			new Question("Who composed this masterpiece?", 1, "Paul Dirac", "Franz Liszt", "Auguste de la Fleur", "Frederic Chopin", R.RHAPSODY),
			new Question("Which ironic impediment did the composer of this piece suffer from when he wrote it?", 1,
						 "Blindness", "Deafness", "Being ginger", "Mutiny", R.DING)
		};
	}


	public MusicQuiz(Question[] questions) {
		this.current = 0;
		this.score = 0;
		this.questions = questions;
	}


	public boolean submitAnswer(char answer) {
		// Submits answer, evaluates it and advanced to the next question
		// TODO | Better to remove nextQuestion (?)
		if (this.currentQuestion().isCorrect(answer)) {
			this.score += 1; // Increase score
			this.nextQuestion();
			return true;
		} else {
			this.nextQuestion();
			return false;
		}
	}


	public Question[] retrieveQuestions() {
		return this.questions;
	}


	public int retrieveScore() {
		return this.score;
	}

	
	public int numQuestions() {
		return this.questions.length; // NOTE | String.length is a method, wheras Array.length is an attribute
	}


	public Question currentQuestion() {
		return this.questions[this.current];
	}


	public Question nextQuestion() {
		// Advances the quiz to the next question
		// TODO | Error handling (...)
		// TODO | Use another data-structure (popable or cyclic) instead (?)
		// TODO | Return boolean instead (indicating successful increment)
		this.current += this.current < (this.numQuestions()-1) ? 1 : 0; // Increment 
		return this.currentQuestion();
	}


	public boolean isComplete() {
		// True if question list is exhausted
		// TODO | Differentiate being on the last question and having answered it already
		return this.questionsRemaining() == 0;
	}


	public int questionsRemaining() {
		// Number of remaining questions
		return this.numQuestions() - this.currentIndex();
	}


	public boolean guess(char letter) {
		return this.currentQuestion().isCorrect(letter);
	}


	public int currentIndex() {
		return this.current;
	}


	public static void run() {
		
		MusicQuiz quiz = new MusicQuiz();
		int score = 0;

		String[] verdicts = new String[] {
			"abysmal",
			"terrible",
			"deplorable",
			"depressing",
			"unremarkable",
			"decent",
			"incredible",
			"excellent",
			"extraordinary",
			"prodigious",
			"jaw dropping",
			"surreal"
		};

		for (Question q : quiz.retrieveQuestions()) {
			// score += q.ask(true) ? 1 : 0; // Correct answers yield one point // TODO: Implement console IO in Question after all (or IO interface?)
		}

		 // TODO | Feedback based on performance (...)
		String verdict = verdicts[(verdicts.length-1)*score/quiz.numQuestions()]; // TODO | Fix index calculation (✓)
		System.out.format("You've finished the quiz! Your final score is %d/%d (%d%%). That's %s!", score, quiz.numQuestions(), 100*score/quiz.numQuestions(), verdict);

	}

	// public static void main(String[] args) { run(); }


}