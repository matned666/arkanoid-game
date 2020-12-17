package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public interface PlayerInterface extends AbstractObject {

    int getLives();
    void setLives(int lives);
    long getPoints();
    void setPoints(long points);

}
