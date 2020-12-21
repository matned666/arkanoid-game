package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Level5 extends BaseLevel {

    private List<Brick> bricks;

    public Level5() {
        bricks = new LinkedList<>();
        generateBricks(bricks);
    }

    @Override
    public BaseLevel getNextLevel() {
        return new Level1();
    }

    @Override
    public String getLayout() {
        return      ".##$.$##.!" +
                    "$#%%%%%#$!" +
                    "..#%%%%..!" +
                    "%...%%..%!";
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public long getTimeInMillis() {
        return DEFAULT_TIME;
    }
}
