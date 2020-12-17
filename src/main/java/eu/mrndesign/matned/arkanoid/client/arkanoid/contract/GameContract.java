package eu.mrndesign.matned.arkanoid.client.arkanoid.contract;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.BouncableObject;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.DestroyableObject;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Difficulty;
import eu.mrndesign.matned.arkanoid.client.arkanoid.presenter.Side;

public class GameContract {

    public interface View{


        void canvasNotSupportedByBrowserError();
        void start();
        Canvas createCanvas();
        void refreshCanvas();
        void getGameOverMessage();
        void getGameOverVoice();
        void launchBall();
        void moveRacket(Side side);
        void ballBounce(BouncableObject bouncableObject);
        void ballHitBrickWithoutDestroyingIt(DestroyableObject destroyableObject);
        void ballDestroyBrick(DestroyableObject destroyableObject);

    }

    public interface Presenter{
        void onKeyHit(Canvas canvas);
        void onStartNewGameKeyHit();
        void ballReducesEnduranceOfBrick(DestroyableObject destroyableObject);
        void onBallBounce(BouncableObject bouncableObject);
        void onLifeLoose();
        void onLevelWin();
        void racketMove();
        void launchBall();
        double getBallWPos();
        double getBallHPos();

        void putBricks(Context2d context2d, int w, int h, int firstPosW, int firstPosH);
    }
}
