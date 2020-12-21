package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;


import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class Level1 extends BaseLevel {


    public Level1() {
        bricks = new LinkedList<>();
        generateBricks(bricks);

    }

    @Override
    public BaseLevel getNextLevel() {
        return new Level2();
    }

    @Override
    public String getLayout() {
        return  ".#$###$#.!" +
                "#$$%%%$$#!" +
                "###$$$###!" +
                "#########!";
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public long getTimeInMillis() {
        return DEFAULT_TIME;
    }
}
