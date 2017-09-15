package com.chojnacki.manufaktura.manuregiony.processing;

import java.util.Calendar;

/**
 * Timer utility class;
 */
public class Timer {
    private long time;

    public Timer() {
    }

    public void start() {
        time = Calendar.getInstance().getTimeInMillis();
    }

    public String stopAndReturn() {
        long now = Calendar.getInstance().getTimeInMillis();
        return Double.toString(((double)(now - time))/1000D);
    }

    public void stop() {
        long now = Calendar.getInstance().getTimeInMillis();
        time -= now;
    }

    public String getTime() {
        return Long.toString(time/1000L);
    }
}
