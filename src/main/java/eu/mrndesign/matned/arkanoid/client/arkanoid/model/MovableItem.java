package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public interface MovableItem extends GameObject{

    double getPreviousHPos();
    void setPreviousHPos(double ballPreviousHPos);
    double getPreviousWPos();
    void setPreviousWPos(double ballPreviousWPos);
    int getWSpeed();
    void setWSpeed(int wSpeed);
    int getHSpeed();
    void setHSpeed(int hSpeed);

}
