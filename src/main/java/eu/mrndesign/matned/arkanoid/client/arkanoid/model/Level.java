package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.BaseLevel;

public class Level {

    private long timeInMillis;
    private BaseLevel level;

    public Level(BaseLevel level) {
        this.level = level;
        this.timeInMillis = level.getTimeInMillis();
    }

    public BaseLevel getLevel() {
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
