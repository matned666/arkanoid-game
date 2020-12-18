package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public enum Difficulty {
    WENKA,
    REGULAR,
    WARRIOR,
    HELLS_ANGEL,
    WILD_SHARK;

//    multiplicand zmienia iloraz poziomu trudności
    public double multiplicand() {
        switch (this) {
            case HELLS_ANGEL:
                return 3;
            case WARRIOR:
                return 2;
            case WENKA:
                return 0.5;
            case WILD_SHARK:
                return 3.5;
            default:
                return 1;
        }
    }

    public int getLives() {
        switch (this) {
            case HELLS_ANGEL:
                return 2;
            case WARRIOR:
                return 3;
            case WENKA:
                return 5;
            case WILD_SHARK:
                return 1;
            default:
                return 4;
        }    }
}
