package eu.mrndesign.matned.arkanoid.client.arkanoid.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.BouncableObject;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.DestroyableObject;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Difficulty;
import eu.mrndesign.matned.arkanoid.client.arkanoid.presenter.GamePresenter;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class CanvasWidget extends Composite implements GameContract.View {

    private GamePresenter gamePresenter;

    private Canvas canvas;
    private Context2d context;
    private Difficulty difficulty;



    public CanvasWidget(Difficulty difficulty) {
            start(difficulty);
        canvas = createCanvas();
        initWidget(canvas);
    }

    private void start(Difficulty difficulty) {
        gamePresenter = new GamePresenter(difficulty);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void canvasNotSupportedByBrowserError() {
        Window.alert("Canvas widget is not supported by your browser. Sorry.");
    }

    @Override
    public void start() {

    }

    //    Przy każdym odświerzeniu ekranu pojawia się ta metoda
    @Override
    public void refreshCanvas() {
        CssColor color = CssColor.make("rgba(" + 0 + ", "
                + 0 + "," + 0 + ", " + 1 + ")");

        context.setFillStyle(color);
        context.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        ImageElement img = ImageElement.as(new Image("img/background1.jpg").getElement());
        context.drawImage(img, BORDER_MIN, BORDER_MIN, CANVAS_WIDTH - BORDER_MAX, CANVAS_HEIGHT);

        launchBall();
        launchRacket();
    }

    @Override
    public void getGameOverMessage() {
//        TODO
    }

    @Override
    public void getGameOverVoice() {
//        TODO
    }

    @Override
    public void moveRacket(eu.mrndesign.matned.arkanoid.client.arkanoid.presenter.Side side) {
//        TODO
    }

    @Override
    public void ballBounce(BouncableObject bouncableObject) {
//        TODO
    }

    @Override
    public void ballHitBrickWithoutDestroyingIt(DestroyableObject destroyableObject) {
//        TODO
    }

    @Override
    public void ballDestroyBrick(DestroyableObject destroyableObject) {
//        TODO
    }

    @Override
    public void launchBall() {
        ImageElement img = ImageElement.as(new Image("img/ball.png").getElement());
        context.drawImage(img, gamePresenter.getBallWPos(), gamePresenter.getBallHPos());
        gamePresenter.launchBall();
    }

    @Override
    public Canvas createCanvas() {
        canvas = Canvas.createIfSupported();

        if (canvas == null) {
            FlowPanel flowPanel = new FlowPanel();
            flowPanel.add(new HTML("Sorry, your browser doesn't support the HTML5 Canvas element"));
            initWidget(flowPanel);
        }
        canvas.setStyleName("canvasExample");     // *** must match the div tag in CanvasExample.html ***
        canvas.setWidth(CANVAS_WIDTH + "px");
        canvas.setCoordinateSpaceWidth(CANVAS_WIDTH);

        canvas.setHeight(CANVAS_HEIGHT + "px");
        canvas.setCoordinateSpaceHeight(CANVAS_HEIGHT);


        addKeyListeners();
        context = canvas.getContext2d();

        return canvas;
    }

    private void addKeyListeners() {
        gamePresenter.onKeyHit(canvas);
    }

    private void launchRacket() {
        ImageElement img = ImageElement.as(new Image("img/racket.png").getElement());
        context.drawImage(img, gamePresenter.getRacketWPos(), RACKET_DISTANCE_FROM_BOTTOM_BORDER);
        gamePresenter.racketMove();
    }






}
