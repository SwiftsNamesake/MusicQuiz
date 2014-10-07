/*
 *
 * MusicQuiz.java
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


import java.util.Scanner;
import java.util.Arrays;


class MusicQuiz {

	private Question[] questions;
	public static final Scanner in = new Scanner(System.in);


	class Question {

		private final String question;	// Question text
		private final int correct;		// Index of correct alternative
		private /* final */ String[] alternatives = new String[4]; // TODO | Choosing from a set of possible alternatives

		public Question(String q, int correct, String a, String b, String c, String d) {
			this.question = q;
			this.correct = correct;
			this.alternatives = new String[] {a, b, c, d}; // TODO | Simplify initialization (...)
		}


		public String toString() {
			// TODO | Fix alignment
			// TODO | Cache string (final variables)
			return String.format("%s\n" +
								 "A. %s\tB. %s\n" +
								 "C. %s\tD. %s\n", this.question, this.alternatives[0], this.alternatives[1], this.alternatives[2], this.alternatives[3]);
		}


		public void display() {
			// Prints the question and alternatives to standard out, in a readable format
			System.out.println(this.toString());
		}


		public String answer() {
			// Retrieves the correct answer
			return this.alternatives[this.correct];
		}


		public int prompt() {
			// Prompts the user for an answer (A, B, C, D), validates the input, and converts to an index
			
			int answer = -1;	// Index of the final answer
			String reply;		// User input
			String[] valid = new String[] { "A", "B", "C", "D" }; // Valid answers
			
			while (answer == -1) {
				reply = MusicQuiz.in.nextLine().toUpperCase();
				if (Arrays.asList(valid).contains(reply)) {
					answer = (int)reply.charAt(0) - (int)'A'; // Convert to index (A is 0, B is 1, etc.)
				}
			}

			return answer;

		}

		public boolean ask(boolean reveal) {
			// Asks a question, receives the answer, gives feedback and returns success flag.
			// Optionally reveals the correct answer.
			// TODO | More interesting feedback (GUI and audio eventually, various encouragement for now)

			this.display();
			boolean right = prompt() == this.correct;
			System.out.println(right ? "Hurrah. You got it!\n" : "I'm afraid not." + (reveal ? " The right answer is " + this.answer() + "." : "") + "\n");
			return right;

		}

	}


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


	public static void main(String[] args) {
		
		MusicQuiz quiz = new MusicQuiz();
		int score = 0;

		String[] verdicts = new String[] {
			"abysmal",
			"terrible",
			"deplorable",
			"depressing",
			"unremarkable",
			"decent",
			"incredible",k
			"excellent",
			"extraordinary",
			"prodigious",
			"jaw dropping",
			"surreal"
		};

		for (Question q : quiz.retrieveQuestions()) {
			score += q.ask(true) ? 1 : 0; // Correct answers yield one point
		}

		 // TODO | Feedback based on performance (...)
		String verdict = verdicts[verdicts.length*score/quiz.numQuestions() - 1]; // TODO | Fix index calculation
		System.out.format("You've finished the quiz! Your final score is %d/%d (%d%%). That's %s!", score, quiz.numQuestions(), 100*score/quiz.numQuestions(), verdict);

	}


}