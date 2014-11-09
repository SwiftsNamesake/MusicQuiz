/*
 * MusicQuiz - Inquisitor.java
 * Manages question data
 *
 * Jonatan H Sundqvist
 * November 7 2014
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


// HashMap... categories
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


class Inquisitor {
	
	private HashMap<String, Question[]> categories;


	public Inquisitor(String directory) {
		this.categories = new HashMap<String, Question []>();
		this.loadCategories(directory);
	}


	public Question[] retrieveQuestions(String category) {
		return categories.get(category);
	}


	public Question[] loadQuestions(String filename) {
		// TODO: Use something
		ArrayList<Question> questions = new ArrayList<Question>();
		
		Utilities.readlines(filename).forEach(ln -> {
			String[] data = ln.split("\\s*;\\s*");
			if (data.length == 6 && new Scanner(data[1]).hasNextInt()) {
				questions.add(new Question(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]));
			} else if (data.length == 7 && new Scanner(data[1]).hasNextInt()) {
				questions.add(new Question(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], R.byName(data[6])));
			} else {
				// TODO: Error handling (non-facetious)
				System.out.println("Unable to parse question data.");
				questions.add(new Question("How did you fuck up such a simple task?", 1, "You're an idiot", "You can't be bothered", "Uhm. Huh?", "All of the above"));
			}
		});

		return questions.toArray(new Question[questions.size()]);
	}


	public void loadCategories(String directory) {
		for (File file : new File(directory).listFiles()) {
			if (file.getName().endsWith(".txt")) {
				try {
					this.addCategory(file.getName().replace(".txt", ""), loadQuestions(new File(file.getParent(), file.getName()).getCanonicalPath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public void addCategory(String category, Question[] questions) {
		System.out.println(questions[0]);
		this.categories.put(category, questions);
	}

}