package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.core.GameState;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Game {

    private int points;
    private Difficulty difficulty;
    private Level level;
    private Timer timer;
    private int lives;
    private GameState gameState;

    public Game(Difficulty difficulty, Level level) {
        this.difficulty = difficulty;
        this.level = level;
        init(difficulty);
    }

    public Game(Difficulty difficulty, Level level, int points) {
        this.difficulty = difficulty;
        this.level = level;
        this.points = points;
        init(difficulty);
    }

    private void init(Difficulty difficulty) {
        lives = difficulty.getLives();
        points = 0;
        timer = new Timer(Timer.getMinutes(DEFAULT_TIME), Timer.getSeconds(DEFAULT_TIME));
        gameState = GameState.PLAYING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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
