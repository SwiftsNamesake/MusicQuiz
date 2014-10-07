/*
 *
 * Question - MusicQuiz.java
 * Desc...
 *
 * Jonatan H Sundqvist
 * October 7 2014
 *
 */


/*
 *	TODO | - More than four alternatives
 *	       - 
 *
 *	SPEC | - 
 *	       - 
 *
 */



class Question {

	private final String question;	// Question text
	private final int correct;		// Index of correct alternative
	private /* final */ String[] alternatives;// = new String[4]; // TODO | Choosing from a set of possible alternatives

	public Question(String q, int correct, String a, String b, String c, String d) {
		this.question = q;
		this.correct = correct;
		//this.alternatives = new String[] {a, b, c, d}; // TODO | Simplify initialization (...)
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


	public boolean isCorrect(String answer) {
		// Determines if a given answer string matches the correct answer
		return this.answer().equals(answer);
	}


	public boolean isCorrect(int index) {
		// Determines if a given answer index matches the correct answer
		return this.correct == index;
	}


	public boolean isCorrect(char answer) {
		// Determines if a given answer character matches the correct answer
		return this.correct == charToIndex(answer);
	}


	public static char indexToChar(int index) {
		// Converts alternative index to the corresponding character
		return (char)(index + (int)'A');
	}


	public static int charToIndex(char alt) {
		// Converts alternative index to the corresponding character
		return  (int)alt - (int)'A';
		
	}


	public int prompt() {
		// Prompts the user for an answer (A, B, C, D), validates the input, and converts to an index
		
		int answer = -1;	// Index of the final answer
		String reply;		// User input
		
		while (answer == -1) {
			reply = MusicQuiz.in.nextLine().toUpperCase();
			if ("ABCD".contains(reply) && reply.length() == 1) {
				answer = (int)reply.charAt(0) - (int)'A'; // Convert to index (A is 0, B is 1, etc.)
			}
		}

		return answer;

	}

}