package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Coordinate {

	private double x;
	private double y;
	private CoordinateType coordinateType;

	public Coordinate() {
	}

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate(double x, double y, CoordinateType coordinateType) {
		this.x = x;
		this.y = y;
		this.coordinateType = coordinateType;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public CoordinateType getCoordinateType() {
		return coordinateType;
	}

	@Override
	public String toString() {
		return "Coordinate{" + "x=" + x + ", y=" + y + '}';
	}

	public enum CoordinateType {
		TOP, RIGHT, LEFT, BOTTOM
	}
}
