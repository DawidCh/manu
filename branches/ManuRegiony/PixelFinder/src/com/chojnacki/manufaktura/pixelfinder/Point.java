package com.chojnacki.manufaktura.pixelfinder;

/**
 * Coordinate holder
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "new Point(" + x + "," + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
