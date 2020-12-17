package eu.mrndesign.matned.arkanoid.client.arkanoid.presenter;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.ui.Image;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Level1;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;

public class GamePresenter implements GameContract.Presenter {

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



    private static final double racketHPos = RACKET_DISTANCE_FROM_BOTTOM_BORDER;

    public GamePresenter(Difficulty difficulty) {
        ballPreviousWPos = BALL_BORDER_WIDTH_MAX / 2;
        ballPreviousHPos = BALL_BORDER_HEIGHT_MAX - 20;
        ballWPos = BALL_BORDER_WIDTH_MAX / 2;
        ballHPos = BALL_BORDER_HEIGHT_MAX - 20;

        racketWPos = (BALL_BORDER_WIDTH_MAX / 2) - (RACKET_WIDTH / 2);
        ballWSpeed = BALL_SPEED;
        ballHSpeed = BALL_SPEED;
        racketCurrentSpeed = 0;
        this.difficulty = difficulty;
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
    public void onStartNewGameKeyHit() {
        Levels lvl = new Level1();
        game = new Game(difficulty, new Level(lvl));
    }

    @Override
    public void ballReducesEnduranceOfBrick(DestroyableObject destroyableObject) {

    }

    @Override
    public void onBallBounce(BouncableObject bouncableObject) {

    }

    @Override
    public void onLifeLoose() {

    }

    @Override
    public void onLevelWin() {

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
    public void launchBall() {
        ballGo();
        ballBounceOfBorders();
        ballBounceOfRacket();
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

    @Override
    public void putBricks(Context2d context2d, int w, int h, int firstPosW, int firstPosH) {
    }

    private void ballGo() {
        ballPreviousWPos = ballWPos;
        ballPreviousHPos = ballHPos;
        ballWPos = ballWPos - ballWSpeed;
        ballHPos = ballHPos - ballHSpeed;
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

    private Audio mistakeAudio;

    {
        mistakeAudio = Audio.createIfSupported();
        mistakeAudio.setSrc("snd/ball_hit.mp3");
    }

    public void hitSound() {
        mistakeAudio.play();
    }


    private void ballBounceOfRacket() {
        if (isOnRacket(ballHPos, ballWPos)) {
            ballHitsRacketFromUpLeft();
            ballHitsRacketFromUpRight();
        }
    }


    private void ballHitsRacketFromUpRight() {
        if (ballWPos < ballPreviousWPos) {
            if (pointInLineWithRacket_X() == racketWPos) {
                ballHitTop(-1);
                hitSound();
            } else {
                hitSound();
                ballWSpeed = ballWSpeed * -1;
            }
        }
    }

    private void ballHitsRacketFromUpLeft() {
        if (ballWPos > ballPreviousWPos) {
            if (pointInLineWithRacket_X() == racketWPos) {
                ballHitTop(1);
                hitSound();
            } else {
                hitSound();
                ballWSpeed = ballWSpeed * -1;
            }
        }
    }

    private void ballHitTop(int i) {
        ballHSpeed = ballHSpeed * -1;

//        todo
        if (i == 1) {
            if (pointInLineWithRacket_X() == racketWPos || pointInLineWithRacket_X() == racketWPos + 120) {
                ballWSpeed = ballWSpeed * -1;
            } else if (pointInLineWithRacket_X() > racketWPos && pointInLineWithRacket_X() <= racketWPos + 10) {
                ballWSpeed = ballWSpeed / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 10 && pointInLineWithRacket_X() <= racketWPos + 20) {
                ballWSpeed = ballWSpeed / 2;
            } else if (pointInLineWithRacket_X() > racketWPos + 20 && pointInLineWithRacket_X() <= racketWPos + 40) {
                ballWSpeed = ballWSpeed * 3 / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 80 && pointInLineWithRacket_X() <= racketWPos + 100) {
                ballWSpeed = ballWSpeed * 5 / 4;
                ballHSpeed = ballHSpeed * 3 / 4;

            } else if (pointInLineWithRacket_X() > racketWPos + 100 && pointInLineWithRacket_X() <= racketWPos + 110) {
                ballWSpeed = ballWSpeed * 6 / 4;
                ballHSpeed = ballHSpeed * 2 / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 110 && pointInLineWithRacket_X() < racketWPos + 120) {
                ballWSpeed = ballWSpeed * 7 / 4;
                ballHSpeed = ballHSpeed / 4;
            }
        }
        if (i == -1) {
            if (pointInLineWithRacket_X() == racketWPos || pointInLineWithRacket_X() == racketWPos + 120) {
                ballWSpeed = ballWSpeed * -1;
            } else if (pointInLineWithRacket_X() > racketWPos && pointInLineWithRacket_X() <= racketWPos + 10) {
                ballWSpeed = ballWSpeed * 7 / 4;
                ballHSpeed = ballHSpeed / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 10 && pointInLineWithRacket_X() <= racketWPos + 20) {
                ballWSpeed = ballWSpeed * 6 / 4;
                ballHSpeed = ballHSpeed * 2 / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 20 && pointInLineWithRacket_X() <= racketWPos + 40) {
                ballWSpeed = ballWSpeed * 5 / 4;
                ballHSpeed = ballHSpeed * 3 / 4;
            } else if (pointInLineWithRacket_X() > racketWPos + 80 && pointInLineWithRacket_X() <= racketWPos + 100) {
                ballWSpeed = ballWSpeed * 3 / 4;


            } else if (pointInLineWithRacket_X() > racketWPos + 100 && pointInLineWithRacket_X() <= racketWPos + 110) {
                ballWSpeed = ballWSpeed / 2;

            } else if (pointInLineWithRacket_X() > racketWPos + 110 && pointInLineWithRacket_X() < racketWPos + 120) {
                ballWSpeed = ballWSpeed / 4;

            }
        }

    }


    private boolean isOnRacket(double hPos, double wPos) {
        return (hPos + (BALL_RADIUS * 2) >= racketHPos && hPos <= racketHPos - RACKET_HEIGHT) &&
                (wPos + (BALL_RADIUS * 2) >= racketHPos && wPos <= racketWPos - RACKET_WIDTH);
    }

    private int pointInLineWithRacket_X() {
        if (slopeFactor() != 0)
            return (int) (ballWPos - ((ballHPos - racketHPos) / slopeFactor()));
        else return 0;
    }


    private double slopeFactor() {
        if (ballWPos - ballPreviousWPos != 0)
            return (ballHPos - ballPreviousHPos) / (ballWPos - ballPreviousWPos);
        else return 1;
    }
}
