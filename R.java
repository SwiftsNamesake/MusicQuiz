/*
 *
 * MusicQuiz - R.java
 * Repository for resources such as UI strings, audio and image files
 *
 * Jonatan H Sundqvist
 * October 17 2014
 *
 */


/*
 *	TODO | - 
 *	       - 
 *	       - 
 *
 *	SPEC | - 
 *	       - 
 *
 */


package MusicQuiz;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


class R {
	
	// Effects
	public static final Media ding 		= Utilities.loadSound("resources/ding.wav");
	public static final Media strangle 	= Utilities.loadSound("resources/strangled.wav");
	
	// Music
	public static final Media rhapsody 	= Utilities.loadSound("resources/HungarianRhapsody.mp3");
	public static final Media fifth 	= Utilities.loadSound("resources/ding.wav"); // TODO | Find proper file

	// Effect IDs
	public static final int DING 		= 0;
	public static final int STRANGLE 	= 1;
	
	// Music IDs
	public static final int RHAPSODY 	= 2;
	public static final int FIFTH 		= 3;


	public static Media byID(int id) {
		switch (id) {
			case DING: 		return ding;
			case STRANGLE: 	return strangle;

			case RHAPSODY: 	return rhapsody;
			case FIFTH: 	return fifth;
			default: return  null;
		}
	}


	public static boolean play(int id) {
		Media audio = byID(id);
		if (audio != null) {
			Utilities.play(audio);
			return true;
		} else {
			return false;
		}
	}


}