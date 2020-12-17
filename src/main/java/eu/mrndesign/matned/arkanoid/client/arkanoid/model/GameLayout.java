package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.GameInterface;

public interface GameLayout extends GameInterface {

    Brick[][] getBricks();
    long getTimeInMillis();
}
