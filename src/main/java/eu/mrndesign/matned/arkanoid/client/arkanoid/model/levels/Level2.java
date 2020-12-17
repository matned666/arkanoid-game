package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Level2 implements Levels {

    @Override
    public String getLayout() {
        return      ".##$%%$##." +
                    "$##$%%$##$" +
                    "$##$%%$##$" +
                    ".##$%%$##." +
                    ".........." +
                    ".........." +
                    "..........";
    }

    @Override
    public long getTimeInMillis() {
        return DEFAULT_TIME;
    }
}
