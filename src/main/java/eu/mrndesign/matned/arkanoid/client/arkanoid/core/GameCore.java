package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.Timer;
import eu.mrndesign.matned.arkanoid.client.arkanoid.contract.GameContract;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.*;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Level1;
import eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels.Levels;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.*;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Texts.DEAD;
import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Texts.TIME_HAS_PASSED;

public class GameCore implements GameContract.Presenter {

    private Game game;
    private Difficulty difficulty;

    private int ballWSpeed;
    private int ballHSpeed;

    private static final double racketHPos = RACKET_H_POS;
    protected double racketWPos;
    protected int racketCurrentSpeed;

    protected int speedSlowdown = 0;
    private boolean hasStarted;


    private double ballWPos;
    private double ballHPos;

    private Timer countDownTimer;

    private GameContract.View view;
    private List<Coordinate> ballCoordinates;

    private List<Brick> bricks;
    private Brick brickToRemove;
    private Coordinate brickHitCoordinate;


    private Levels lvl;

    public GameCore(Difficulty difficulty, GameContract.View view) {
        this.view = view;
        this.difficulty = difficulty;
        init();
    }

    /**
     * inicjuję nowy etap gry
     */
    private void init() {
        if (lvl == null) lvl = new Level1();
        else lvl = lvl.getNextLevel();

        hasStarted = false;
        ballWPos = BLL_START_W_POS;
        ballHPos = BLL_START_H_POS;
        racketWPos = (BALL_BORDER_WIDTH_MAX / 2) - (RACKET_WIDTH / 2);
        racketCurrentSpeed = 0;
        game = game != null ?
                new Game(difficulty, new Level(lvl), game.getPoints() ):
                new Game(difficulty, new Level(lvl) );
        ballCoordinates = new LinkedList<>();
        brickHitCoordinate = new Coordinate();
    }

    @Override
    public void initializeNewLevel() {
        init();
        TimeWrapper.getInstance().runTimer();
    }

    /**
     * Różne akcje gry
     */
    @Override
    public void listenToTheGame() {
        lifeLost();
        timeUp();
        levelDone();
        deadYouAre();
    }


    /**
     * Key handlers -
     * - strzałka do góry
     */

    @Override
    public void onKeyHit(Canvas canvas) {
        canvas.addMouseMoveHandler(mouseDownEvent -> {
            MouseListener.getInstance().setMouseX(mouseDownEvent.getRelativeX(canvas.getElement()));
            MouseListener.getInstance().setMouseY(mouseDownEvent.getRelativeY(canvas.getElement()));
            mouseMovementCore();
        });
        canvas.addClickHandler(clickEvent -> {
            if (game.getGameState() == GameState.LEVEL_DONE) {
                initializeNewLevel();
                game.setGameState(GameState.PLAYING);
            }
            if (game.getGameState() == GameState.PLAYING) {
                startTheBall();
            }
        });
        canvas.addKeyDownHandler(keyDownEvent -> {
            if (keyDownEvent.isLeftArrow() && racketWPos > RACKET_MAX_LEFT) {
                racketCurrentSpeed = (RACKET_SPEED * -1);
                speedSlowdown = 0;
            }
            if (keyDownEvent.isUpArrow() && !hasStarted) {
                startTheBall();
            }
            if (keyDownEvent.isRightArrow() && racketWPos < RACKET_MAX_RIGHT) {
                racketCurrentSpeed = RACKET_SPEED;
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
    public void putBricksToMemory() {
        bricks = game.getLevel().getLevel().getBricks();
        view.showBricks(bricks);
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

    public Game getGame() {
        return game;
    }

    /**
     * Piłka startuje
     */
    private void ballGo() {
        ballWPos = ballWPos - ballWSpeed;
        ballHPos = ballHPos - ballHSpeed;
    }

    private void mouseMovementCore() {
        racketWPos = MouseListener.getInstance().mouseX - RACKET_WIDTH /2;
        if (racketWPos + RACKET_WIDTH >= CANVAS_WIDTH)
            racketWPos = CANVAS_WIDTH - RACKET_WIDTH;
        if (racketWPos <= 0)
            racketWPos = 0;
    }

    private void startTheBall() {
        ballWSpeed = (int) (BALL_SPEED * difficulty.multiplicand());
        ballHSpeed = (int) (BALL_SPEED * difficulty.multiplicand());
        hasStarted = true;
        startTheCountDown();
    }

    private void startTheCountDown() {
        countDownTimer = new Timer() {
            @Override
            public void run() {
                game.getTimer().timeElapse();
            }
        };
        TimeWrapper.getInstance().setClock(countDownTimer);
        countDownTimer.scheduleRepeating(1000); // odświerza co 1 sekundę
    }

    private void lifeLost() {
        if (ballHPos > CANVAS_HEIGHT) {
            ballHSpeed = 0;
            ballWSpeed = 0;
            ballWPos = racketWPos;
            ballHPos = BLL_START_H_POS;
            hasStarted = false;
            game.lostLife();
        }
    }

    private void deadYouAre() {
        if (game.getLives() <= 0) {
            game.setGameState(GameState.GAME_OVER);
            stopTheBall();
            view.gameOver(DEAD);
            countDownTimer.cancel();
        }
    }

    private void levelDone() {
        if (bricks.size() == 0) {
            game.setGameState(GameState.LEVEL_DONE);
            stopTheBall();
            view.levelWon();
            countDownTimer.cancel();
        }
    }

    private void timeUp() {
        if (game.getTimer().minutes() == 0 && game.getTimer().seconds() == 0) {
            game.setGameState(GameState.GAME_OVER);
            stopTheBall();
            view.gameOver(TIME_HAS_PASSED);
            countDownTimer.cancel();
        }
    }

    private void stopTheBall() {
        ballWSpeed = 0;
        ballHSpeed = 0;
    }

    /**
     * Opcje odbicia od ściany - koniec końcem powinien być zrobiony generycznie wraz z paletką i cegłami
     */
    private void ballBounceOfBorders() {
        if (ballHPos <= BALL_BORDER_HEIGHT_MIN || ballHPos >= BALL_BORDER_HEIGHT_MAX) {
            ballHSpeed = ballHSpeed * -1;
            GameAudio.pingSound();
        }
        if (ballWPos <= BALL_BORDER_WIDTH_MIN || ballWPos >= BALL_BORDER_WIDTH_MAX) {
            ballWSpeed = ballWSpeed * -1;
            GameAudio.pingSound();
        }
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
     * wartości najwyższe są po środku.
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
        GameAudio.pingSound();
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
                game.setPoints((int) (game.getPoints() + difficulty.multiplicand()));
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
        GameAudio.pingSound();
        if (brickHitCoordinate.getCoordinateType().equals(Coordinate.CoordinateType.BOTTOM)) {
            ballHSpeed = ballHSpeed * -1;
        } else if (brickHitCoordinate.getCoordinateType().equals(Coordinate.CoordinateType.LEFT)) {
            ballHSpeed = ballHSpeed * -1;
        } else if (brickHitCoordinate.getCoordinateType().equals(Coordinate.CoordinateType.TOP)) {
            ballWSpeed = ballWSpeed * -1;
        } else if (brickHitCoordinate.getCoordinateType().equals(Coordinate.CoordinateType.RIGHT)) {
            ballWSpeed = ballWSpeed * -1;
        }
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


}
