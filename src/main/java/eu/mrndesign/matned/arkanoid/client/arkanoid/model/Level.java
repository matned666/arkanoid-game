package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

import java.util.Arrays;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.CANVAS_HEIGHT;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.CANVAS_WIDTH;


public class Level implements GameLayout{

    private double width;
    private double height;
    private Brick[][] bricks;
    private long timeInMillis;
    private Levels level;

    public Level(Levels level) {
        this.level = level;
        this.height = CANVAS_HEIGHT / 100;
        this.width = CANVAS_WIDTH / 100;
        this.timeInMillis = level.getTimeInMillis();
        generateLevel();
    }

    @Override
    public Brick[][] getBricks() {
        return bricks;
    }

    @Override
    public long getTimeInMillis() {
        return timeInMillis;
    }

    private void generateLevel() {
        bricks = new Brick[(int) height][(int) width];
        addBricks();
    }

    private void addBricks() {
        int elementCounter = 0;
        for (Brick[] el : bricks) {
            for (int i = 0; i < el.length; i++) {
                el[i] = getBrickFromLayout(elementCounter);
                elementCounter ++;
            }
        }
    }

    private Brick getBrickFromLayout(int elementCounter) {
        char matrixSign = level.getLayout().charAt(elementCounter);
        switch (matrixSign){
            case '#': return new Brick(1);
            case '$': return new Brick(2);
            case '%': return new Brick(3);
            default: return new Brick(0);
        }
    }

    @Override
    public String toString() {
        return "Level{" +
                "width=" + width +
                ", height=" + height +
                ", bricks=" + Arrays.toString(bricks) +
                ", timeInMillis=" + timeInMillis +
                ", level=" + level +
                '}';
    }
}
