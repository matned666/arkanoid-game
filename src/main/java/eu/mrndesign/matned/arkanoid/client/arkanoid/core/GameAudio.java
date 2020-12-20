package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.media.client.Audio;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Sounds.*;

public class GameAudio {

    public static void gameOverSound(){
        sound(GAME_OVER_MP_3);
    }

    public static void levelWinSound(){
        sound(GAME_WIN_MP_3);
    }

    public static void pingSound(){
        sound(BALL_HIT_MP3);
    }

    private static void sound(String sound) {
        Audio makeAudio;
        makeAudio = Audio.createIfSupported();
        makeAudio.setSrc("snd/"+sound);
        makeAudio.play();
    }

}
