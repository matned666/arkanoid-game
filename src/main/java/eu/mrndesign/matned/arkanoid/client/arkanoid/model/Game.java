package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Game {

    private int points;
    private Difficulty difficulty;
    private Level level;

    public Game(Difficulty difficulty, Level level) {
        this.difficulty = difficulty;
        this.level = level;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Game{" +
                "difficulty=" + difficulty +
                ", level=" + level +
                '}';
    }
}
