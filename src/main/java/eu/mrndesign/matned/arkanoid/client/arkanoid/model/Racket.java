package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Images.RACKET_IMAGE;

public class Racket implements MovableItem{

    private double racketWPos;
    private double racketHPos;
    private double racketPreviousWPos;
    private double racketPreviousHPos;
    private int wSpeed;
    private int hSpeed;
    private double width;
    private double height;
    private Game game;

    public Racket(Game game) {
        this.game = game;
        width =  RACKET_WIDTH;
        height = RACKET_HEIGHT;
        wSpeed = 0;
        hSpeed = 0;
    }

    @Override
    public double getHPos() {
        return racketHPos;
    }

    @Override
    public double getWPos() {
        return racketWPos;
    }

    @Override
    public void setHPos(double pos) {
        racketHPos = pos;
    }

    @Override
    public void setWPos(double pos) {
        racketWPos = pos;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public String getImage() {
        return RACKET_IMAGE;
    }

    @Override
    public double getPreviousHPos() {
        return racketPreviousHPos;
    }

    @Override
    public void setPreviousHPos(double pos) {
        racketPreviousHPos = pos;
    }

    @Override
    public double getPreviousWPos() {
        return racketPreviousWPos;
    }

    @Override
    public void setPreviousWPos(double pos) {
        racketPreviousWPos = pos;
    }

    @Override
    public int getWSpeed() {
        return wSpeed;
    }

    @Override
    public void setWSpeed(int wSpeed) {
        this.wSpeed = wSpeed;
    }

    @Override
    public int getHSpeed() {
        return hSpeed;
    }

    @Override
    public void setHSpeed(int hSpeed) {
//        ruch możliwy tylko na jednej osi - mógłbym zrobić jeszcze osobny interface
    }
}
