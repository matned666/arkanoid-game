package eu.mrndesign.matned.arkanoid.client.arkanoid.model;

import java.util.concurrent.TimeUnit;

public class Timer {

    private int minutes;
    private int seconds;

    public Timer(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int minutes(){
        return minutes;
    }

    public int seconds(){
        return seconds;
    }

    public static int getMinutes(long timeInMillis) {
        return (int) (timeInMillis / 60000);
    }

    public static int getSeconds(long timeInMillis) {
        return (int) ((timeInMillis % 60000) / 1000);
    }

    public void timeElapse() {
        if (seconds > 0 || minutes > 0) {
            if (seconds > 0)
                seconds -= 1;
            else {
                if (minutes > 0)
                    minutes -= 1;
                seconds = 59;
            }
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
