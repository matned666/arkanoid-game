package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Images.BALL_IMAGE;

public class Ball implements MovableItem {
    private double ballWPos;
    private double ballHPos;
    private double ballPreviousWPos;
    private double ballPreviousHPos;
    private double radius;
    private int wSpeed;
    private int hSpeed;

    public Ball() {
        radius = BALL_RADIUS;
        wSpeed = 0;
        hSpeed = 0;
        ballWPos = BALL_BORDER_WIDTH_MAX / 2;
        ballHPos = BALL_BORDER_HEIGHT_MAX - 20;
    }

    @Override
    public String getImage() {
        return BALL_IMAGE;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getWPos() {
        return ballWPos;
    }

    @Override
    public void setWPos(double ballWPos) {
        this.ballWPos = ballWPos;
    }

    @Override
    public double getHPos() {
        return ballHPos;
    }

    @Override
    public void setHPos(double ballHPos) {
        this.ballHPos = ballHPos;
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
        this.hSpeed = hSpeed;
    }

    @Override
    public double getPreviousWPos() {
        return ballPreviousWPos;
    }

    @Override
    public void setPreviousWPos(double ballPreviousWPos) {
        this.ballPreviousWPos = ballPreviousWPos;
    }

    @Override
    public double getPreviousHPos() {
        return ballPreviousHPos;
    }

    @Override
    public void setPreviousHPos(double ballPreviousHPos) {
        this.ballPreviousHPos = ballPreviousHPos;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "ballWPos=" + ballWPos +
                ", ballHPos=" + ballHPos +
                ", ballPreviousWPos=" + ballPreviousWPos +
                ", ballPreviousHPos=" + ballPreviousHPos +
                ", radius=" + radius +
                ", wSpeed=" + wSpeed +
                ", hSpeed=" + hSpeed +
                '}';
    }
}
