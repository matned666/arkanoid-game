package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.user.client.Timer;

public class MouseListener {

    private static MouseListener instance;

    public static MouseListener getInstance() {
        if (instance == null) {
            synchronized (MouseListener.class) {
                if (instance == null)
                    instance = new MouseListener();
            }
        }
        return instance;
    }

    protected double mouseX;
    protected double mouseY;

    private MouseListener() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }
}
