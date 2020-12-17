package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Game {

    private Difficulty difficulty;
    private Level level;
    private Racket racket;
    private Ball ball;

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

    public Racket getRacket() {
        return racket;
    }

    public void setRacket(Racket racket) {
        this.racket = racket;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        return "Game{" +
                "difficulty=" + difficulty +
                ", level=" + level +
                ", racket=" + racket +
                ", ball=" + ball +
                '}';
    }
}
