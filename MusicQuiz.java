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

	private Question[] questions;
	public static final Scanner in = new Scanner(System.in);


	public Question[] retrieveQuestions() {
		return this.questions;
	}


	public int numQuestions() {
		return this.questions.length; // NOTE | String.length is a method, wheras Array.length is an attribute
	}


	public MusicQuiz() {
		final String prefix = "What is the capital of ";
		this.questions = new Question[] {
			new Question(prefix + "Sweden?", 	    0, 	"Stockholm", 	"Copenhagen", 	"the Hague", 	"Ottawa"),
			new Question(prefix + "Burma?",		    0, 	"Naypyidaw", 	"Laos", 		"Asmara", 		"Canberra"),
			new Question(prefix + "Peru?",	 	    2, 	"Moscow", 		"Ulan Bator", 	"Lima", 		"Santiago"),
			new Question(prefix + "Cote d'Ivoire?", 1, 	"Cairo", 		"Yamoussoukro", "Belgrade", 	"Dublin")
		};
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
		String verdict = verdicts[verdicts.length*score/quiz.numQuestions() - 1]; // TODO | Fix index calculation
		System.out.format("You've finished the quiz! Your final score is %d/%d (%d%%). That's %s!", score, quiz.numQuestions(), 100*score/quiz.numQuestions(), verdict);

	}

	// public static void main(String[] args) { run(); }


}