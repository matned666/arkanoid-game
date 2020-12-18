package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Game {

    private int points;
    private Difficulty difficulty;
    private Level level;
    private Timer timer;
    private int lives;

    public Game(Difficulty difficulty, Level level) {
        this.difficulty = difficulty;
        this.level = level;
        lives = difficulty.getLives();
        points = 0;
        timer = new Timer(Timer.getMinutes(DEFAULT_TIME), Timer.getSeconds(DEFAULT_TIME));
    }

    public void lostLife() {
        lives -= 1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Timer getTimer() {
        return timer;
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
