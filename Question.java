/*
 *
 * MusicQuiz - Question.java
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


package MusicQuiz;


class Question {


	private final String question;			// Question text
	private final int correct;				// Index of correct alternative
	private final String[] alternatives;	// TODO | Choosing from a set of possible alternatives
	
	private String audio; // Optional URI to associated media file

	public Question(String q, int correct, String a, String b, String c, String d) {
		this.alternatives = new String[] {a, b, c, d};
		this.question = q;
		this.correct = correct;
		this.audio = null;
	}


	public Question(String q, int correct, String a, String b, String c, String d, String audio) {
		this(q, correct, a, b, c, d);
		this.audio = audio;
	}


	public void play() {
		// Plays the associated media file, if one exists
		if (this.audio != null) {
			Utilities.playSound(this.audio);
		}
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


	public String questionText() {
		return this.question;
	}


	public String[] retrieveAlternatives() {
		return this.alternatives;
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


	//public static void main(String[] args) {
	//	String header =
	//	"▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬" +
	//	"ＤＡＭＮ ＴＨＩＳ ＣＯＭＭＥＮＴ ＩＳ ＦＡＮＣY" +
	//	"▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬﻿";
	//	System.out.println(header);
	//	new Question("What is the capital of Sweden?", 0, "Stockholm", "Copenhagen", "the Hague", "Ottawa").display();
	//}


}