package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Coordinate;

import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.BRICK_WIDTH;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Images.*;

public abstract class BaseLevel {

    public static String getImage(Brick brick){
        switch (brick.getHitPts()){
            case 1: return BRICK0;
            case 2: return BRICK1;
            case 3: return BRICK2;
            default: return EMPTY_BRICK;
        }
    }

    protected List<Brick> bricks;

    public abstract BaseLevel getNextLevel();

    public abstract String getLayout();

    public abstract long getTimeInMillis();

    public List<Brick> getBricks() {
        return bricks;
    }

    protected void  generateBricks(List<Brick> bricks){
        double x = FIRST_BRICK_W_POSITION;
        double y = FIRST_BRICK_H_POSITION;
        for (int i = 0; i < getLayout().length(); i++) {
            switch (getLayout().charAt(i)){
                case '!': {
                    x = FIRST_BRICK_W_POSITION;
                    y += BRICK_HEIGHT;
                    break;
                }
                case '#': {
                    bricks.add(new Brick(1, new Coordinate(x, y)));
                    x += BRICK_WIDTH;
                    break;
                }
                case '$': {
                    bricks.add(new Brick(2, new Coordinate(x, y)));
                    x += BRICK_WIDTH;
                    break;
                }
                case '%': {
                    bricks.add(new Brick(3, new Coordinate(x, y)));
                    x += BRICK_WIDTH;
                    break;
                }
                default: {
                    x += BRICK_WIDTH;
                }
            }
        }
    }
}
