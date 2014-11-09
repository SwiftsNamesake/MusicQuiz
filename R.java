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
 *	TODO | - Find alternative to switch or generate file automatically (cf. ClassBuilder.py) (...)
 *	       - Refactor (make handling of possibly cached MediaPlayers less convoluted)
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

	static {
		// R.cacheID = -1;
		// Utilities.debugMessage("Initialzed R.java at %f hours", (double)(System.nanoTime())/(1000000000*60L*60L));
		ding 		= Utilities.loadSound("resources/ding.wav");
		strangle 	= Utilities.loadSound("resources/strangled.wav");
		rhapsody 	= Utilities.loadSound("resources/HungarianRhapsody.mp3");
		// fifth 		= Utilities.loadSound("resources/Symphony no 5.mp3"); // TODO | Find proper file
		ninth 		= Utilities.loadSound("resources/Symphony no 9.mp3"); // TODO | Find proper file
	}


	//
	private static MediaPlayer cache; 	// Cached media player
	private static int cacheID;			// ID of the related resource
	
	// Effects
	public static final Media ding;
	public static final Media strangle;
	
	// Music
	public static final Media rhapsody;
	// public static final Media fifth;
	public static final Media ninth;


	// Effect IDs
	public static final int DING 		= 0;
	public static final int STRANGLE 	= 1;
	
	// Music IDs
	public static final int RHAPSODY 	= 2;
	public static final int FIFTH 		= 3;
	public static final int NINTH 		= 4;


	public static Media byID(int id) {
		switch (id) {
			case DING: 		return ding;
			case STRANGLE: 	return strangle;

			case RHAPSODY: 	return rhapsody;
			// case FIFTH: 	return fifth;
			case NINTH: 	return ninth;
			default: return  null;
		}
	}


	public static MediaPlayer play(int id) {
		// Plays the Media object with the specified ID
		// TODO: Return MediaPlayer instead (would negate the need for a cache) (✓)
		Media audio = byID(id);
		if (audio != null) {
			return Utilities.play(audio);
		} else {
			return null;
		}
	}


	public static boolean play(int id, boolean writeCache) {
		// Caches the MediaPlayer for future reference
		// TODO | Use separate cache for each resource (tied to ID or Media)
		Media audio = byID(id);
		if (audio != null) {
			R.cache = writeCache ? Utilities.play(audio) : R.cache;
			R.cacheID = writeCache ? id : R.cacheID;
			return true;
		} else {
			return false;
		}
	}


	public static int cache() {
		return R.cacheID;
	}


	public static boolean stop(int id) {
		if (R.cacheID == id && id >= 0) {
			// Utilities.debugMessage("ID is %d and cacheID is %d", id, R.cacheID);
			Utilities.stop(R.cache);
			return true;
		} else {
			return false;
		}
	}

/*
	public static boolean stop(int id) {
		// NOTE: Useless method, creates MediaPlayer and stops it...
		Media audio = byID(id);
		if (audio != null) {
			Utilities.debugMessage("Stopping media resource");
			Utilities.stop(audio);
			return true;
		} else {
			return false;
		}
	}
*/


}