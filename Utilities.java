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


package MusicQuiz;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.embed.swing.JFXPanel;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


class Utilities {

	public static boolean DEBUG;
	private static Utilities utils;

	private static JFXPanel p;

	static {
		// Static initializers
		debugMessage("Initializing static utilities");
		DEBUG = true;
		Utilities.p = new JFXPanel();
	}

	public static void debugMessage(String message, Object... args) {
		// Prints a (possibly formatted) debug message to stdout
		if (DEBUG) {
			System.out.println(String.format(message, args)); // Can't believe this compiled at the first try...
		}
	}


	public static int playSound(String fn) {
		// TODO: Reconsider return type
		Utilities.play(Utilities.loadSound(fn));
		return 0;
	}


	public static Media loadSound(String fn) {
		// Creates a media object from the specified file
		// TODO | Find out if MediaPlayers can't be reset
		String uri = new File(fn).toURI().toString();
		return new Media(uri); // TODO: Cache Media object (?)
	}


	public static MediaPlayer play(Media audio) {
		// Audio playback
		// TODO | Neater solution to initialization (?)
		// debugMessage("Playing audio");
		MediaPlayer player = new MediaPlayer(audio);
		player.play();
		return player;
	}


	public static MediaPlayer stop(MediaPlayer audio) {
		// Audio playback
		// TODO | Neater solution to initialization (?)
		// debugMessage("Stopping audio");
		audio.stop();
		return audio;
	}


	public static Utilities get() {
		// TODO | Better name
		// Implements singleton pattern
		if (utils == null) {
			utils = new Utilities();
		}

		return utils;

	}


	public static String choose(String[] strings) {
		return strings[(int)(Math.random()*(strings.length))];
	}



	//-------------------------------------------------------------------------
	// File utilities
	//-------------------------------------------------------------------------
	public static Stream<String> readlines(String fn) {
		return Files.lines(fn);
	}


	public static String readfile(String fn) {
		return null;
	}




	private Utilities() {

	}

}