package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.media.client.Audio;

public class GameAudio {

    public static void sound(String sound) {
        Audio makeAudio;
        makeAudio = Audio.createIfSupported();
        makeAudio.setSrc("snd/"+sound);
        makeAudio.play();
    }

}
