package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Swietokrzyskie region
 */
public class Swietokrzyskie {

    private final Region WLOSZCZOWSKI = new Region("włoszczowski", 1085, 1256, false, "TWL");
    private final Region JEDRZEJOWSKI = new Region("jędrzejowski", 1137, 1308, false, "TJE");
    private final Region KONECKI = new Region("konecki", 1146, 1176, false, "TKO");
    private final Region PINCZOWSKI = new Region("pińczowski", 1179, 1355, false, "TPI");
    private final Region KAZIMIERSKI = new Region("kazimierski", 1181, 1409, false, "TKA");
    private final Region KIELECKI = new Region("kielecki", 1213, 1257, false, "TKI");
    private final Region SKARZYSKI = new Region("skarżyski", "TSK", "pl", new Point(1242, 1190), new Point(1242, 1140));
    private final Region BUSKI = new Region("buski", 1240, 1360, false, "TBU");
    private final Region STARACHOWICKI = new Region("starachowicki", 1278, 1201, false, "TST");
    private final Region STASZOWSKI = new Region("staszowski", 1310, 1330, false, "TSZ");
    private final Region OPATOWSKI = new Region("opatowski", 1322, 1267, false, "TOP");
    private final Region OSTROWIECKI = new Region("ostrowiecki", 1328, 1223, false, "TOS");
    private final Region SANDOMIERSKI = new Region("sandomierski", 1381, 1287, false, "TSA");


    public final Collection<Region> SWIETOKRZYSKIE_POWIATY = Arrays.asList(WLOSZCZOWSKI, JEDRZEJOWSKI, KONECKI, PINCZOWSKI, KAZIMIERSKI, KIELECKI, BUSKI, STARACHOWICKI,
            STASZOWSKI, OPATOWSKI, OSTROWIECKI, SANDOMIERSKI, SKARZYSKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(1085, 1256),//
                new Point(1137, 1308),//
                new Point(1146, 1176),//
                new Point(1179, 1355),//
                new Point(1181, 1409),//
                new Point(1213, 1257),//
                new Point(1222, 1190),//
                new Point(1240, 1360),//
                new Point(1278, 1201),//
                new Point(1310, 1330),//
                new Point(1322, 1267),//
                new Point(1328, 1223),//
                new Point(1381, 1287)};
    }

    private static Swietokrzyskie instance;

    public static Swietokrzyskie getInstance() {
        if (instance == null) {
            instance = new Swietokrzyskie();
        }
        return instance;
    }
}
