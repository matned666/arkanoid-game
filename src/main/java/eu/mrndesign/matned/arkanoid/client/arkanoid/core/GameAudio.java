package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Sounds.BALL_HIT_MP3;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Sounds.GAME_OVER_MP_3;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Sounds.GAME_WIN_MP_3;

import com.google.gwt.media.client.Audio;

public class GameAudio {

	public static void gameOverSound() {
		sound(GAME_OVER_MP_3);
	}

	public static void levelWinSound() {
		sound(GAME_WIN_MP_3);
	}

	public static void pingSound() {
		sound(BALL_HIT_MP3);
	}

	private static void sound(String sound) {
		Audio makeAudio;
		makeAudio = Audio.createIfSupported();
		makeAudio.setSrc("snd/" + sound);
		makeAudio.play();
	}

}
