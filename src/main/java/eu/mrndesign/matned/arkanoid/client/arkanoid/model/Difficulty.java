package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

public enum Difficulty {
    WENKA,
    REGULAR,
    WARRIOR,
    HELLS_ANGEL;

//    multiplicand zmienia iloraz poziomu trudności
    public double multiplicand() {
        switch (this) {
            case HELLS_ANGEL:
                return 3;
            case WARRIOR:
                return 2;
            case WENKA:
                return 0.5;
            default:
                return 1;
        }
    }

}
