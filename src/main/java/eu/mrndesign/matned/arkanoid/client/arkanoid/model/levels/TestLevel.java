package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;


import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

/**
 *   Aby sprawdzić jak działa wygranie levelu , żeby nie musieć grać
 *   wystarczy w klacie GameCore zmienić w meodzie init()
 *   linijkę
 *   if (lvl == null) lvl = new Level1();
 *   na if (lvl == null) lvl = new TestLevel();
 */

public class TestLevel extends BaseLevel {


    public TestLevel() {
        bricks = new LinkedList<>();
        generateBricks(bricks);

    }

    @Override
    public BaseLevel getNextLevel() {
        return new Level1();
    }

    @Override
    public String getLayout() {
        return ".#!";
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public long getTimeInMillis() {
        return DEFAULT_TIME;
    }
}
