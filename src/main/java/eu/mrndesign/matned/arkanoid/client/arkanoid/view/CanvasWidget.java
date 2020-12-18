package eu.mrndesign.matned.arkanoid.client.arkanoid.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
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

    private GameCore gameCore;
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
        ImageElement img = ImageElement.as(new Image("img/background1.jpg").getElement());
        context.drawImage(img, BORDER_MIN, BORDER_MIN, CANVAS_WIDTH - BORDER_MAX, CANVAS_HEIGHT);
        context.fillRect(POINTS_POSITION_W - 30, POINTS_POSITION_H-8, CANVAS_WIDTH-5, 10);
        context.strokeText(POINTS, POINTS_POSITION_W - 28, POINTS_POSITION_H);
        context.strokeText(String.valueOf(gameCore.getGame().getPoints()), POINTS_POSITION_W+20, POINTS_POSITION_H);
        context.fillRect(POINTS_POSITION_W - 30, POINTS_POSITION_H+2, CANVAS_WIDTH-5, 10);
        context.strokeText(getTime(), POINTS_POSITION_W - 28, POINTS_POSITION_H+10);
        gameCore.putBricks();
        launchBall();
        launchRacket();
        gameCore.listenToTheGame();
    }

    @Override
    public void putBricks(List<Brick> bricks) {
        for (Brick el : bricks) {
            ImageElement img = ImageElement.as(new Image("img/" + Levels.getImage(el)).getElement());
            context.drawImage(img, el.getCoordinate().getX(), el.getCoordinate().getY());
        }
    }

    @Override
    public void showLives(int lives) {
        context.fillRect(10, POINTS_POSITION_H-8, 50, 10);
        context.strokeText(Texts.LIVES + lives, 12 , POINTS_POSITION_H);
    }

    @Override
    public void launchBall() {
        ImageElement img = ImageElement.as(new Image("img/"+BALL_IMAGE).getElement());
        context.drawImage(img, gameCore.getBallWPos(), gameCore.getBallHPos());
        gameCore.launchBall();
    }

    @Override
    public void gameOver(String message) {
        ImageElement img = ImageElement.as(new Image("img/"+GAME_OVER_IMAGE).getElement());
        context.drawImage(img, 120, 120);
    }

    @Override
    public void levelWon() {
        ImageElement img = ImageElement.as(new Image("img/"+LEVEL_DONE_IMAGE).getElement());
        context.drawImage(img, 120, 120);
    }

    private String getTime() {

        return Texts.REMAINING_TIME +gameCore.getGame().getTimer().minutes() + ":" + gameCore.getGame().getTimer().seconds();
    }

    private void launchRacket() {
        ImageElement img = ImageElement.as(new Image("img/"+RACKET_IMAGE).getElement());
        context.drawImage(img, gameCore.getRacketWPos(), RACKET_H_POS);
        gameCore.racketMove();
    }

    private void addKeyListeners() {
        gameCore.onKeyHit(canvas);
    }

}
