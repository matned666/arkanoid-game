package eu.mrndesign.matned.arkanoid.client.arkanoid.contract;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Difficulty;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

public class GameContract {

    public interface View{


        void canvasNotSupportedByBrowserError();
        Canvas createCanvas();
        void refreshCanvas();
        Context2d getContext();
        void launchBall();


    }

    public interface Presenter{
        void onKeyHit(Canvas canvas);
        void racketMove();
        void launchBall();
        double getBallWPos();
        double getBallHPos();
        void putBricks(Context2d context);
    }
}
