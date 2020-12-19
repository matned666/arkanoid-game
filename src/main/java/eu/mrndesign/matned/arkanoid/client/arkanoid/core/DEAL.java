package eu.mrndesign.matned.arkanoid.client.arkanoid.core;

import com.google.gwt.user.client.Timer;

/**
 * DPL - Data Position Listener
 * Prosty singleton kolekcjonujący ważne dane dla całego programu.
 * Dzięki temu mam do nich bezpośredni dostęp z każdej klasy.
 */

public class DEAL {

    private static DEAL instance;

    public static DEAL getInstance() {
        if (instance == null) {
            synchronized (DEAL.class) {
                if (instance == null)
                    instance = new DEAL();
                instance.resetFrame();
            }
        }
        return instance;
    }

    private long frameNo;
    private Timer timer;
    private Timer clock;

    private DEAL() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
    }

    public void runTimer(){
        timer.run();
        clock.run();
    }

    public void stopTime(){
        timer.cancel();
        clock.run();
    }

    public void setClock(Timer clock) {
        this.clock = clock;
    }

    public void resetFrame(){
        frameNo = 0;
    }

    public void nextFrame(){
        frameNo++;
    }

    public long getFrameNo() {
        return frameNo;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        if (frameNo == 0) this.timer = timer;  //ustawiamy timer jedynie raz przy klatce zerowej w MyFirstGWTApplication
    }
}

