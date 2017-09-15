package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Podlaskie region
 */
public class Podlaskie {

    private static Podlaskie instance;
    private final Region BIALOSTOCKI = new Region("białostocki", 1612, 620, false, "BI");
    private final Region SUWALSKI = new Region("suwalski", 1533, 331, false, "BSU");
    private final Region AUGUSTOWSKI = new Region("augustowski", 1575, 431, false, "BAU");
    private final Region BIELSKI = new Region("bielski", 1590, 720, false, "BBI");
    private final Region GRAJEWSKI = new Region("grajewski", 1476, 510, false, "BGR");
    private final Region HAJNOWSKI = new Region("hajnowski", 1682, 716, false, "BHA");
    private final Region SUWALSKI_MIASTO = new Region("suwalski - miasto", 1546, 359, false, "BS");
    private final Region KOLNENSKI = new Region("kolneński", 1404, 558, false, "BKL");
    private final Region LOMZYNSKI_MIASTO = new Region("łomżyński - miasto", 1419, 619, false, "BL");
    private final Region LOMZYNSKI = new Region("łomżyński", 1456, 612, false, "BLM");
    private final Region MONIECKI = new Region("moniecki", 1540, 539, false, "BMN");
    private final Region SEJNENSKI = new Region("sejneński", 1604, 354, false, "BSE");
    private final Region SIEMIATYCKI = new Region("siemiatycki", 1564, 792, false, "BSI");
    private final Region SOKOLSKI = new Region("sokólski", 1632, 514, false, "BSK");
    private final Region WYSOKOMAZOWIECKI = new Region("wysokomazowiecki", 1504, 681, false, "BWM");
    private final Region ZAMBROWSKI = new Region("zambrowski", 1450, 670, false, "BZA");

    public final Collection<Region> PODLASKIE_POWIATY = Arrays.asList(BIALOSTOCKI, SUWALSKI, AUGUSTOWSKI, BIELSKI, GRAJEWSKI, HAJNOWSKI, SUWALSKI_MIASTO, KOLNENSKI, LOMZYNSKI_MIASTO,
            LOMZYNSKI, MONIECKI, SEJNENSKI, SIEMIATYCKI, SOKOLSKI, WYSOKOMAZOWIECKI, ZAMBROWSKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(1404, 558),
                new Point(1419, 619),
                new Point(1456, 612),
                new Point(1476, 510),
                new Point(1450, 670),
                new Point(1504, 681),
                new Point(1533, 331),
                new Point(1540, 539),
                new Point(1546, 359),
                new Point(1564, 792),
                new Point(1575, 431),
                new Point(1590, 720),
                new Point(1604, 354),
                new Point(1612, 620),
                new Point(1632, 514),
                new Point(1682, 716)};
    }

    public static Podlaskie getInstance() {
        if (instance == null) {
            instance = new Podlaskie();
        }
        return instance;
    }
}
