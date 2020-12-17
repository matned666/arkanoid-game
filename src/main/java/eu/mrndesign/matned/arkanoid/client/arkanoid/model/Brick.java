package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_BRICK_HEIGHT;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_BRICK_WIDTH;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Images.*;

public class Brick implements DestroyableObject {

    private double brickWPos;
    private double brickHPos;
    private double width;
    private double height;
    private int hitsToDestroy;
    private String brickImage;

    public Brick(int hitsToDestroy) {
        this.hitsToDestroy = hitsToDestroy;
        width = DEFAULT_BRICK_WIDTH;
        height = DEFAULT_BRICK_HEIGHT;
        brickImage = getImage();
    }

    public String getImage() {
        switch (this.hitsToDestroy){
            case 1: return BRICK0;
            case 2: return BRICK1;
            case 3: return BRICK2;
            default: return EMPTY_BRICK;
        }
    }


    @Override
    public double getHPos() {
        return brickHPos;
    }

    @Override
    public double getWPos() {
        return brickWPos;
    }

    @Override
    public void setHPos(double pos) {
        brickHPos = pos;
    }

    @Override
    public void setWPos(double pos) {
        brickWPos = pos;
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
    public int getHitsToDestroy() {
        return hitsToDestroy;
    }

    @Override
    public void setHitsToDestroy(int hitsToDestroy) {
        this.hitsToDestroy = hitsToDestroy;
    }


    @Override
    public String toString() {
        return "Brick{" +
                ", width=" + width +
                ", height=" + height +
                ", hitsToDestroy=" + hitsToDestroy +
                ", brickImage='" + brickImage + '\'' +
                '}';
    }
}
