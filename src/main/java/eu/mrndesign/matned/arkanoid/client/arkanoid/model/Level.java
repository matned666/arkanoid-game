package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

public class Level {

    private long timeInMillis;
    private Levels level;

    public Level(Levels level) {
        this.level = level;
        this.timeInMillis = level.getTimeInMillis();
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
