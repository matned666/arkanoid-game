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


    private Game game;
    private Difficulty difficulty;

    private int speedSlowdown = 0;
    private int ballWSpeed;
    private int ballHSpeed;

    private int racketCurrentSpeed;

    private boolean hasStarted;
    private double ballPreviousWPos;

    private double ballWPos;
    private double ballHPos;
    private double racketWPos;

    private GameContract.View view;
    private Context2d context;
    private List<Coordinate> ballCoordinates;

    private List<Brick> bricks;
    private Brick brickToRemove;
    private Coordinate racketHitCoordinate;
    private Coordinate brickHitCoordinate;

    private static final double racketHPos = RACKET_H_POS;

    public GameCore(Difficulty difficulty, GameContract.View view) {
        this.view = view;
        this.difficulty = difficulty;
        context = view.getContext();
        init();
    }

    private void init() {
        hasStarted = false;
        ballPreviousWPos = BLL_START_W_POS;
        ballWPos = BLL_START_W_POS;
        ballHPos = BLL_START_H_POS;
        racketWPos = (BALL_BORDER_WIDTH_MAX / 2) - (RACKET_WIDTH / 2);
        ballWSpeed = 0;
        ballHSpeed = 0;
        racketCurrentSpeed = 0;
        Levels lvl = new Level1();
        game = new Game(difficulty, new Level(lvl));
        ballCoordinates = new LinkedList<>();
        racketHitCoordinate = new Coordinate();
        brickHitCoordinate = new Coordinate();
    }

    @Override
    public void onKeyHit(Canvas canvas) {
        canvas.addClickHandler(clickEvent -> {
            if (!hasStarted) {
                ballWSpeed = (int) (BALL_SPEED * difficulty.multiplicand());
                ballHSpeed = (int) (BALL_SPEED * difficulty.multiplicand());
                hasStarted = true;
            }
        });
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
        ballBounceFromBrick();
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
        bricks = game.getLevel().getLevel().getBricks();
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
        ballWPos = ballWPos - ballWSpeed;
        ballHPos = ballHPos - ballHSpeed;
    }

    /**
     * Opcje odbicia od ściany - koniec końcem powinien być zrobiony generycznie wraz z paletką i cegłami
     */
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

    /**
     * Efekt dźwiękowy zderzenia
     */
    private Audio makeAudio;

    {
        makeAudio = Audio.createIfSupported();
        makeAudio.setSrc("snd/ball_hit.mp3");
    }

    public void hitSound() {
        makeAudio.play();
    }


    /**
     * Sprawdzam czy piłka zderzyła się z paletką
     */
    private void ballBounceOfRacket() {
        if (isOnRacket())
                ballBounceOfRacketInner();
    }

    /**
     * Ciąg dalszy.
     * Ponższe zmienne dają optymalny wynik dla wygodnego funkcjonowania gry
     * equalizer >> dzieli długość paletki na odcinki - odliczając ich ilość przy długości paletki 120 - eq. wynosi 10
     * compressor >> dzieli długość paletki na odcinki - obliczając ich długości względem paletki paletki 120 - eq. wynosi 12
     * variable 1 i 2 >> (variable1 - Math.abs(-variable2 + j / equalizer) - tworzy odwróconą parabolę, dzięki której
     *                                                                       wartości najwyższe są po środku.
     * difficulty.multiplicand() >> metoda Difficulty >> iloczyn prętkości danego poziomu trudności
     */
    private void ballBounceOfRacketInner() {
        int equalizer = (int) (RACKET_WIDTH / 12);
        int compressor = (int) (RACKET_WIDTH / equalizer);
        int variable1 = (int) (RACKET_WIDTH / 17);
        int variable2 = (int) (RACKET_WIDTH / 20);

        for (int j = 0; j < RACKET_WIDTH + equalizer; j = j + equalizer) {
            if (ballWPos >= racketWPos - RACKET_WIDTH / compressor + j && ballWPos < racketWPos + equalizer + j) {
                ballWSpeed = (int) ((-1 * RACKET_WIDTH / 2 + j) / equalizer * difficulty.multiplicand()) * -1;
                ballHSpeed = (int) ((variable1 - Math.abs(-variable2 + j / equalizer)) * difficulty.multiplicand());
            }
        }
        hitSound();
        ballHPos = RACKET_H_POS - BALL_RADIUS * 2;
    }

    /**
     * Tutaj sprawdzam dla każdej cegły została ona w danej klatce dotknięta piłką
     * Jeśli tak, to zmieniam kolor , albo jeśli jest już czerwona ,
     * to ją oznaczam jako do usunięcia z listy
     */
    private void ballBounceFromBrick() {
        bricks.forEach(x -> {
            if (isOnBrick(x)) {
                x.setHitPts(x.getHitPts() - 1);
                game.setPoints((int) (game.getPoints()+difficulty.multiplicand()));
                if (x.getHitPts() <= 0)
                    brickToRemove = x;
                ballBounceOfBrick();

            }
        });
        bricks.remove(brickToRemove);
    }

    /**
     * W tej metodzie piłka odbija się od cegiełki w zależności od jej punktu zderzenia
     */
    private void ballBounceOfBrick() {
        switch (brickHitCoordinate.getCoordinateType()) {
            case TOP:
            case BOTTOM: {
                ballHSpeed = ballHSpeed * -1;
                break;
            }
            case LEFT:
            case RIGHT: {
                ballWSpeed *= -1;
                break;
            }
        }
        hitSound();
    }


    /**
     * obliczam punkty graniczne piłki, obecnie cztery
     */
    private List<Coordinate> ballCoordinates() {
        ballCoordinates.clear();
        ballCoordinates.add(new Coordinate(ballWPos + BALL_RADIUS, ballHPos, Coordinate.CoordinateType.LEFT));
        ballCoordinates.add(new Coordinate(ballWPos, ballHPos + BALL_RADIUS, Coordinate.CoordinateType.TOP));
        ballCoordinates.add(new Coordinate(ballWPos + BALL_RADIUS, ballHPos + 2 * BALL_RADIUS, Coordinate.CoordinateType.BOTTOM));
        ballCoordinates.add(new Coordinate(ballWPos + BALL_RADIUS * 2, ballHPos + BALL_RADIUS, Coordinate.CoordinateType.RIGHT));
        return ballCoordinates;
    }

    /**
     * Sprawdzam, czy piłka zderzyła się z paletką
     */
    private boolean isOnRacket() {
        return bounceCheck(racketWPos, racketHPos,
                racketWPos + RACKET_WIDTH,
                racketHPos + RACKET_HEIGHT);
    }

    /**
     * Sprawdzam czy piłka zderzyła sie z cegłą
     */
    private boolean isOnBrick(Brick brick) {
        double bStartX = brick.getCoordinate().getX();
        double bStartY = brick.getCoordinate().getY();
        double bEndX = brick.getCoordinate().getX() + BRICK_WIDTH;
        double bEndY = brick.getCoordinate().getY() + BRICK_HEIGHT;
        return bounceCheck(bStartX, bStartY, bEndX, bEndY);
    }

    /**
     * ...
     * ciąg dalszy
     */
    private boolean bounceCheck(double bStartX, double bStartY, double bEndX, double bEndY) {
        return ballCoordinates().stream().anyMatch(x -> {
            double _x = x.getX();
            double _y = x.getY();
            if (_x >= bStartX && _x <= bEndX && _y >= bStartY && _y <= bEndY) {
                setCoordinate(x);
                return true;
            }
            return false;
        });
    }

    /**
     * zmieniam aktualny koordynat zderzenia
     */
    private void setCoordinate(Coordinate data) {
        brickHitCoordinate = new Coordinate(data.getX(), data.getY(), data.getCoordinateType());
    }

    public Game getGame() {
        return game;
    }
}
