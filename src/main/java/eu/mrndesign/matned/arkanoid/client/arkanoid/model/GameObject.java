package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.GameInterface;

public interface GameObject extends GameInterface {

    double getHPos();
    double getWPos();

    void setHPos(double pos);
    void setWPos(double pos);

    double getWidth();
    double getHeight();

    String getImage();
}
