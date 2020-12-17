package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Level2 extends Levels {

    private List<Brick> bricks;

    public Level2() {
        bricks = new LinkedList<>();
        generateBricks(bricks);
    }

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
