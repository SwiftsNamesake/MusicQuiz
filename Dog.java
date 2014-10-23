/*
 * MusicQuiz - Dog.java
 * Showing Jay what classes are
 *
 * Jonatan H Sundqvist
 * Jayant Shivarajan
 * October 22 2014
 *
 */


/*
 *	TODO | -
 *	       -
 *
 *	SPEC | - 
 *	       - 
 */


// Where we left off:
// Tendency to use static attributes, repository, dumping ground
// Discuss the value of keeping closely related data together (a cohesive unit)
// Emphasize similarities between static Question class and the previous solution (same algorithms due to nearly identical data structure)
//
// Fragility and rigidity of static implementation
// Defeats the purpose of classes



package MusicQuiz;


class Dog {

	private static final String[] dogNames = new String[] {
		"Frances", "Fido"
	};


	static final int MALE = 0, FEMALE = 1;

	private String name;
	private float obedience;
	private int lifespan;
	private int weight;
	private int age;

	private boolean alive;

	private final int sex;

	// No need to have a separate string object for each dog, since all dogs have the same
	public static final String species = "Canis lupus familiaris";

	// A constructor does not have an explicit return type
	// You may overload (create several) any number of constructors
	// It's just like any other method, except you use it with the 'new' keyword
	private Dog(String name, float obedience, int lifespan, int weight, int age) {
		this.name 		= name;
		this.obedience 	= obedience;
		this.lifespan 	= lifespan;
		this.weight 	= weight;
		this.age 		= age;

		this.alive = true;
		this.sex = Math.random() > 0.5 ? MALE : FEMALE;
	}


	public void bark() {
		if (this.alive)
			System.out.format("%s says woof!\n", this.name);
	}


	public void greet() {
		if (this.alive)
			System.out.format("Greetings human. I'm %s, a humble dog. Our kind doesn't live very long; I only have %d years to live.\n", this.name, this.lifespan-this.age);
	}
	

	public static void main(String[] args) {
		// Create two Dog objects
		Dog jay = new Dog("Jayant", 0.01f, 5, 256, 2);
		Dog jon = new Dog("Jonatan", 0.03f, 5+(int)(Math.random()*12), 120, 4);

		jay.bark();
		jon.greet();


		for (Dog dog : new Dog[] { jay, jon }) {
			dog.bark();
		}

	}

}