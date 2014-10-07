/*
 *
 * MusicQuiz - Utilities.java
 * Desc...
 *
 * Jonatan H Sundqvist
 * October 7 2014
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


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


class Utilities {

	public static boolean DEBUG;
	private static Utilities utils;

	public static void debugMessage(String message, Object... args) {
		// Prints a (possibly formatted) debug message to stdout
		if (DEBUG) {
			System.out.println(String.format(message, args)); // Can't believe this compiled at the first try...
		}
	}


	public static int playSound(String fn) {
		String uri = new File(fn).toURI().toString();
		debugMessage("Playing file: \'%s\'", uri);
		Media media = new Media(uri);
		MediaPlayer mp = new MediaPlayer(media);
		mp.play();
		return 0;
	}


	public static Utilities get() {
		// TODO < Better name
		// Implements singleton pattern
		if (utils == null) {
			utils = new Utilities();
		}

		return utils;

	}


	private Utilities() {
		DEBUG = true;
	}

}