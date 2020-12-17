package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Game {

    private Difficulty difficulty;
    private Level level;

    public Game(Difficulty difficulty, Level level) {
        this.difficulty = difficulty;
        this.level = level;
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
