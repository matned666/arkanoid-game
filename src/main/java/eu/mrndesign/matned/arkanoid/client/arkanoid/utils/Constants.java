package eu.mrndesign.matned.arkanoid.client.arkanoid.utils;

public class Constants {

	// canva
	public static final int CANVAS_HEIGHT = 500;
	public static final int CANVAS_WIDTH = 1000;

	public static final int PERIOD_MILLIS = 30; // szybkość odświeżania canvas

//    prędkości
	public static final int RACKET_SPEED = 15;
	public static final int BALL_SPEED = 5;

	public static final int FIRST_BRICK_W_POSITION = 50;
	public static final int FIRST_BRICK_H_POSITION = 50;
	public static final int BRICK_WIDTH = 100;
	public static final int BRICK_HEIGHT = 50;

//    rakieta
	public static final double RACKET_WIDTH = 120;
	public static final double RACKET_HEIGHT = 30;
	public static final double RACKET_H_POS = CANVAS_HEIGHT - 30;

//    wielkość piłki
	public static final double BALL_RADIUS = 10;

//    ramka canvas
	public static final double BORDER_MIN = 0;
	public static final double BORDER_MAX = 0;

	public static final long DEFAULT_TIME = 400000;

//    granice w których porusza się rakieta
	public static final double RACKET_MAX_LEFT = BORDER_MIN;
	public static final double RACKET_MAX_RIGHT = CANVAS_WIDTH - BORDER_MAX - RACKET_WIDTH + 10;

//    granice w których porusza się piłka
	public static final double BALL_BORDER_HEIGHT_MIN = BORDER_MIN;
	public static final double BALL_BORDER_WIDTH_MIN = BORDER_MIN;
	public static final double BALL_BORDER_HEIGHT_MAX = CANVAS_HEIGHT + 50;
	public static final double BALL_BORDER_WIDTH_MAX = CANVAS_WIDTH - BORDER_MAX - BALL_RADIUS;

	public static final double BLL_START_W_POS = CANVAS_WIDTH / 2 - BALL_RADIUS;
	public static final double BLL_START_H_POS = RACKET_H_POS - BALL_RADIUS - 20;

	public static final double POINTS_POSITION_W = CANVAS_WIDTH / 10 * 9;
	public static final double POINTS_POSITION_H = 30;

}
