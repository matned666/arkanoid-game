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

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public CoordinateType getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(CoordinateType coordinateType) {
        this.coordinateType = coordinateType;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public enum CoordinateType{
        TOP,
        RIGHT,
        LEFT,
        BOTTOM
    }
}
