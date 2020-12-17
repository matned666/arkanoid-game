package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.ui.Image;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Level1;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class GameCore implements GameContract.Presenter {

    public static final int BALL_MAX_SPEED = 9;
    private Game game;
    private Difficulty difficulty;

    private int speedSlowdown = 0;
    private int ballWSpeed;
    private int ballHSpeed;

    private int racketCurrentSpeed;


    private double ballPreviousWPos;
    private double ballPreviousHPos;
    private double ballWPos;
    private double ballHPos;
    private double racketWPos;

    private GameContract.View view;
    private Context2d context;
    private List<Coordinate> ballCoordinates;

    private static final double racketHPos = RACKET_H_POS;

    public GameCore(Difficulty difficulty, GameContract.View view) {
        this.view = view;
        this.difficulty = difficulty;
        context = view.getContext();
        init();
    }

    private void init() {
        ballPreviousWPos = BLL_START_W_POS;
        ballPreviousHPos = BLL_START_H_POS;
        ballWPos = BLL_START_W_POS;
        ballHPos = BLL_START_H_POS;
        racketWPos = (BALL_BORDER_WIDTH_MAX / 2) - (RACKET_WIDTH / 2);
        ballWSpeed = BALL_SPEED;
        ballHSpeed = BALL_SPEED;
        racketCurrentSpeed = 0;
        Levels lvl = new Level1();
        game = new Game(difficulty, new Level(lvl));
        ballCoordinates = new LinkedList<>();
    }

    @Override
    public void onKeyHit(Canvas canvas) {
        canvas.addKeyDownHandler(keyDownEvent -> {
            if (keyDownEvent.isLeftArrow() && racketWPos > RACKET_MAX_LEFT) {
                racketCurrentSpeed = (int) (RACKET_SPEED * -1);
                speedSlowdown = 0;

            }
            if (keyDownEvent.isRightArrow() && racketWPos < RACKET_MAX_RIGHT) {
                racketCurrentSpeed = (int) RACKET_SPEED;
                speedSlowdown = 0;

            }
        });

        canvas.addKeyUpHandler(keyUpEvent -> {
            if (keyUpEvent.isLeftArrow() && racketWPos > RACKET_MAX_LEFT) {
                speedSlowdown = 5;
            }
            if (keyUpEvent.isRightArrow() && racketWPos < RACKET_MAX_RIGHT) {
                speedSlowdown = 5;
            }
        });
    }

    @Override
    public void launchBall() {
        ballGo();
        ballBounceOfBorders();
        ballBounceOfRacket();
    }


    @Override
    public void racketMove() {
        if (racketWPos < RACKET_MAX_RIGHT & racketCurrentSpeed > 0)
            racketWPos = racketWPos + racketCurrentSpeed;
        if (racketWPos > RACKET_MAX_LEFT & racketCurrentSpeed < 0)
            racketWPos = racketWPos + racketCurrentSpeed;


        if (racketCurrentSpeed > 0) racketCurrentSpeed = racketCurrentSpeed - speedSlowdown;
        if (racketCurrentSpeed < 0) racketCurrentSpeed = racketCurrentSpeed + speedSlowdown;
    }

    @Override
    public void putBricks(Context2d context) {
        List<Brick> bricks = game.getLevel().getLevel().getBricks();
        for (Brick el : bricks) {
            ImageElement img = ImageElement.as(new Image("img/" + Levels.getImage(el)).getElement());
            context.drawImage(img, el.getCoordinate().getX(), el.getCoordinate().getY());
        }

    }

    public double getRacketWPos() {
        return racketWPos;
    }

    public double getBallWPos() {
        return ballWPos;
    }

    public double getBallHPos() {
        return ballHPos;
    }

    private void ballGo() {
        ballPreviousWPos = ballWPos;
        ballPreviousHPos = ballHPos;
        ballWPos = ballWPos - ballWSpeed;
        ballHPos = ballHPos - ballHSpeed;
    }

    private void ballBounceOfBricks(){

    }

    private void ballBounceOfBorders() {
        if (ballHPos <= BALL_BORDER_HEIGHT_MIN || ballHPos >= BALL_BORDER_HEIGHT_MAX) {
            ballHSpeed = ballHSpeed * -1;
            hitSound();
        }
        if (ballWPos <= BALL_BORDER_WIDTH_MIN || ballWPos >= BALL_BORDER_WIDTH_MAX) {
            ballWSpeed = ballWSpeed * -1;
            hitSound();
        }
    }

    private Audio makeAudio;

    {
        makeAudio = Audio.createIfSupported();
        makeAudio.setSrc("snd/ball_hit.mp3");
    }

    public void hitSound() {
        makeAudio.play();
    }


    private void ballBounceOfRacket() {
        if (isOnRacket())
            if (ballWPos > ballPreviousWPos) {
                ballBounceOfRacketInner(1);
            } else ballBounceOfRacketInner(-1);
    }

    private void ballBounceOfRacketInner(int i) {
//        TODO
        if (ballWPos + BALL_RADIUS >= racketWPos && ballWPos +BALL_RADIUS <= racketWPos + 10 ) {
            ballWSpeed = i > 0 ?1:15;
            ballHSpeed = (i > 0 ?15:1) * -1;
        } else if (ballWPos + BALL_RADIUS > racketWPos + 10 && ballWPos+BALL_RADIUS <= racketWPos + 30) {
            ballWSpeed = i > 0 ?3:7;
            ballHSpeed = (i > 0 ?7:3) * -1;
        } else if (ballWPos + BALL_RADIUS > racketWPos + 30 && ballWPos +BALL_RADIUS<= racketWPos + 70) {
            ballWSpeed = i > 0 ?7:3;
            ballHSpeed = (i > 0 ?3:7) * -1;
        } else if (ballWPos + BALL_RADIUS > racketWPos + 70 && ballWPos +BALL_RADIUS<= racketWPos + 110) {
            ballWSpeed = i > 0 ?7:3;
            ballHSpeed = (i > 0 ?3:7) * -1;
        } else if (ballWPos + BALL_RADIUS > racketWPos + 110 && ballWPos+BALL_RADIUS <= racketWPos + 120) {
            ballWSpeed = i > 0 ?15:1;
            ballHSpeed = (i > 0 ?1:15) * -1;
        } else {
//            if (ballWPos + BALL_RADIUS > racketWPos + 40 && ballWPos+BALL_RADIUS <= racketWPos + 80) {
            ballHSpeed *= -1;
        }
        hitSound();
        ballHPos = RACKET_H_POS - 20;
    }

    private List<Coordinate> ballCoordinates(){
        ballCoordinates.clear();
        ballCoordinates.add(new Coordinate(ballWPos, ballHPos + BALL_RADIUS));
        ballCoordinates.add(new Coordinate(ballWPos + BALL_RADIUS, ballHPos + 2*BALL_RADIUS));
        ballCoordinates.add(new Coordinate(ballWPos + BALL_RADIUS*2, ballHPos + BALL_RADIUS));
        return ballCoordinates;
    }


    private boolean isOnRacket() {
        return ballCoordinates().stream().anyMatch(this::coordinateInRacket);
    }

    private boolean coordinateInRacket(Coordinate x) {
        return x.getX() >= racketWPos && x.getX() <= racketWPos + RACKET_WIDTH
                && x.getY() >= racketHPos && x.getY() <= racketHPos + RACKET_HEIGHT;
    }

}
