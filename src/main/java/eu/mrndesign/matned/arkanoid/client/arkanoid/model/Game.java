package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.core.GameState;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

public class Game {

    private int points;
    private Difficulty difficulty;
    private Level level;
    private int lives;

    private Timer timer;
    private GameState gameState;
    private boolean holdMoment;

    private long time;

    private Game(GameBuilder builder) {
        this.difficulty = builder.difficulty;
        this.level = builder.level;
        this.lives = builder.lives;
        this.points = builder.points;
        this.time = (long) (level.getTimeInMillis() / difficulty.multiplicand());
        init();
    }

    private void init() {
        timer = new Timer(Timer.getMinutes(time), Timer.getSeconds(time));
        gameState = GameState.PLAYING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isHoldMoment() {
        return holdMoment;
    }

    public void setHoldMoment(boolean holdMoment) {
        this.holdMoment = holdMoment;
    }

    public void lostLife() {
        lives -= 1;
    }

    public int getLives() {
        return lives;
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

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Game{" +
                "difficulty=" + difficulty +
                ", level=" + level +
                '}';
    }

    public static class GameBuilder{

        private Level level;
        private int points;
        private Difficulty difficulty;
        private int lives;

        public GameBuilder(Level level) {
            this.level = level;
        }

        public GameBuilder points(int points){
            this.points = points;
            return this;
        }

        public GameBuilder difficulty(Difficulty difficulty){
            this.difficulty = difficulty;
            return this;
        }

        public GameBuilder lives(int lives){
            this.lives = lives;
            return this;
        }

        public Game build(){
            return new Game(this);
        }

    }
}
