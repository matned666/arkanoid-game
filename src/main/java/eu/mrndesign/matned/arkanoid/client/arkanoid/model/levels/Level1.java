package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;


import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Coordinate;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class Level1 extends Levels {


    public Level1() {
        bricks = new LinkedList<>();
        generateBricks(bricks);
    }

    @Override
    public String getLayout() {
        return      ".#$###$#.!#$$%%%$$#!###$$$###!#########!";
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public long getTimeInMillis() {
        return DEFAULT_TIME;
    }
}
