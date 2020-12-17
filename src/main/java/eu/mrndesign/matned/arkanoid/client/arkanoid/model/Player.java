package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Player implements PlayerInterface{

    private int lives;
    private long points;

    public Player(int lives) {
        this.lives = lives;
        this.points = 0L;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public long getPoints() {
        return points;
    }

    @Override
    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Player{" +
                "lives=" + lives +
                ", points=" + points +
                '}';
    }
}
