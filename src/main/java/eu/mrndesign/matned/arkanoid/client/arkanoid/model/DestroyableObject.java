package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public interface DestroyableObject extends BouncableObject {

    int getHitsToDestroy();
    void setHitsToDestroy(int hitsToDestroy);
}
