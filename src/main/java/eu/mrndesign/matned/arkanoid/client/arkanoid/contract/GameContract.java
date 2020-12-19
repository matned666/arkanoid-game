package eu.mrndesign.matned.arkanoid.client.arkanoid.contract;

import com.google.gwt.canvas.client.Canvas;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Game;

import java.util.List;

public class GameContract {

    public interface View{
        Canvas createCanvas();
        void refreshCanvas();
        void launchBall();
        void gameOver(String message);
        void levelWon();
        void showBricks(List<Brick> bricks);
        void showLives(int lives);
    }

    public interface Presenter{
        void listenToTheGame();
        void onKeyHit(Canvas canvas);
        void racketMove();
        void launchBall();
        double getBallWPos();
        double getBallHPos();
        void putBricksToMemory();
        double getRacketWPos();
        Game getGame();

    }
}
