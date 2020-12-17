package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

import java.util.Arrays;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.CANVAS_HEIGHT;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.CANVAS_WIDTH;


public class Level {

    private long timeInMillis;
    private Levels level;

    public Level(Levels level) {
        this.level = level;
        this.timeInMillis = level.getTimeInMillis();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public Levels getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Level{" +
                ", timeInMillis=" + timeInMillis +
                ", level=" + level +
                '}';
    }
}
