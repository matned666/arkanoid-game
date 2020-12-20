package eu.mrndesign.matned.arkanoid.client.arkanoid.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
import eu.mrndesign.matned.arkanoid.client.arkanoid.core.MouseListener;
import eu.mrndesign.matned.arkanoid.client.arkanoid.core.TimeWrapper;
import eu.mrndesign.matned.arkanoid.client.arkanoid.core.GameAudio;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Difficulty;
import eu.mrndesign.matned.arkanoid.client.arkanoid.core.GameCore;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;
import eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Texts;

import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Images.*;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Messages.POINTS;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class CanvasWidget extends Composite implements GameContract.View {

    private GameContract.Presenter gameCore;
    private Canvas canvas;
    private Context2d context;

    public CanvasWidget(Difficulty difficulty) {
        gameCore = new GameCore(difficulty, this);
        canvas = createCanvas();
        initWidget(canvas);
    }

    @Override
    public Canvas createCanvas() {
        canvas = Canvas.createIfSupported();

        if (canvas == null) {
            FlowPanel flowPanel = new FlowPanel();
            flowPanel.add(new HTML(Texts.HTML_5_CANVAS_ELEMENT));
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

    /**
     * odświerzanie ekranu
     */
    @Override
    public void refreshCanvas() {
        CssColor color = CssColor.make("rgba(" + 255 + ", "
                + 255 + "," + 255 + ", " + 1 + ")");

        context.setFillStyle(color);
        context.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        ImageElement img = ImageElement.as(new Image("img/" + BACKGROUND_IMAGE).getElement());
        context.drawImage(img, BORDER_MIN, BORDER_MIN, CANVAS_WIDTH - BORDER_MAX, CANVAS_HEIGHT);
        getGameLiveInformations();
        gameCore.putBricksToMemory();
        launchBall();
        launchRacket();
        gameCore.listenToTheGame();
        TimeWrapper.getInstance().nextFrame();
    }

    private void getGameLiveInformations() {
        // Prostokąt z punktami
        context.fillRect(POINTS_POSITION_W - 30, POINTS_POSITION_H - 8, CANVAS_WIDTH - 5, 10);
        context.strokeText(POINTS, POINTS_POSITION_W - 28, POINTS_POSITION_H);
        context.strokeText(String.valueOf(gameCore.getGame().getPoints()), POINTS_POSITION_W + 20, POINTS_POSITION_H);

        // Prostokąt z czasem
        context.fillRect(POINTS_POSITION_W - 30, POINTS_POSITION_H + 2, CANVAS_WIDTH - 5, 10);
        context.strokeText(getTime(), POINTS_POSITION_W - 28, POINTS_POSITION_H + 10);

        context.fillRect(10, POINTS_POSITION_H - 8, 50, 10);
        context.strokeText(Texts.LIVES + gameCore.getGame().getLives(), 12, POINTS_POSITION_H);

        // pozycja myszy
        context.strokeText("X: "+ MouseListener.getInstance().getMouseX(), 12, POINTS_POSITION_H +10);
        context.strokeText("Y: "+ MouseListener.getInstance().getMouseY(), 12, POINTS_POSITION_H +20);
    }

    @Override
    public void showBricks(List<Brick> bricks) {
        for (Brick el : bricks) {
            ImageElement img = ImageElement.as(new Image("img/" + Levels.getImage(el)).getElement());
            context.drawImage(img, el.getCoordinate().getX(), el.getCoordinate().getY());
        }
    }

    @Override
    public void launchBall() {
        ImageElement img = ImageElement.as(new Image("img/" + BALL_IMAGE).getElement());
        context.drawImage(img, gameCore.getBallWPos(), gameCore.getBallHPos());
        gameCore.launchBall();
    }

    @Override
    public void gameOver() {
        levelEndsWithResult(GAME_OVER_IMAGE, 164, 40);
        if (gameCore.getGame().isHoldMoment())GameAudio.gameOverSound();
        gameCore.getGame().setHoldMoment(false);
        stopTimer();
    }

    @Override
    public void levelWon() {
        levelEndsWithResult(LEVEL_DONE_IMAGE, 420, 120);
        if (gameCore.getGame().isHoldMoment())GameAudio.levelWinSound();
        gameCore.getGame().setHoldMoment(false);
        stopTimer();
    }

    private void levelEndsWithResult(String gameOverImage, int x, int y) {
        ImageElement img = ImageElement.as(new Image("img/" + gameOverImage).getElement());
        context.drawImage(img, x, y);
    }

    private void stopTimer() {
        if (TimeWrapper.getInstance().getFrameNo() == 4) {
            TimeWrapper.getInstance().getTimer().cancel();
        }
    }

    private String getTime() {
        return Texts.REMAINING_TIME + gameCore.getGame().getTimer().minutes() + ":" + gameCore.getGame().getTimer().seconds();
    }

    private void launchRacket() {
        ImageElement img = ImageElement.as(new Image("img/" + RACKET_IMAGE).getElement());
        context.drawImage(img, gameCore.getRacketWPos(), RACKET_H_POS);
        gameCore.racketMove();
    }

    private void addKeyListeners() {
        gameCore.onKeyHit(canvas);
    }

}
