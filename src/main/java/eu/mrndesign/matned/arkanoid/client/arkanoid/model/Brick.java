package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public class Brick {

    private int hitPts;
    private Coordinate coordinate;
    private boolean isVisible;

    public Brick(int hitPts, Coordinate coordinate) {
        this.hitPts = hitPts;
        this.coordinate = coordinate;
        isVisible = true;
    }

    public int getHitPts() {
        return hitPts;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setHitPts(int hitPts) {
        this.hitPts = hitPts;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
