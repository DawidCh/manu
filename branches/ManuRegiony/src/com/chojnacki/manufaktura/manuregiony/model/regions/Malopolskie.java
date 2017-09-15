package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Malopolskie region
 */
public class Malopolskie {


    private final Region OSWIECIMSKI = new Region("oświęcimski", 964, 1487, false, "KOS");
    private final Region CHRZANOWSKI = new Region("chrzanowski", 996, 1454, false, "KCH");
    private final Region WADOWICKI = new Region("wadowicki", 1013, 1514, false, "KWA");
    private final Region OLKUSKI = new Region("olkuski", 1028, 1394, false, "KOL");
    private final Region SUSKI = new Region("suski", 1035, 1566, false, "KSU");
    private final Region KRAKOWSKI = new Region("krakowski", "KRA", "pl", new Point(1137, 1457), new Point(1067, 1434));
    private final Region MYSLENICKI = new Region("myślenicki", 1086, 1534, false, "KMY");
    private final Region NOWOTARSKI = new Region("nowotarski", 1088, 1621, false, "KNT");
    private final Region KRAKOWSKI_MIASTO = new Region("krakowski - miasto", 1095, 1467, false, "KR");
    private final Region TATRZANSKI = new Region("tatrzański", 1102, 1667, false, "KTT");
    private final Region MIECHOWSKI = new Region("miechowski", 1108, 1374, false, "KMI");
    private final Region WIELICKI = new Region("wielicki", 1128, 1490, false, "KWI");
    private final Region LIMANOWSKI = new Region("limanowski", 1144, 1565, false, "KLI");
    private final Region PROSZOWICKI = new Region("proszowicki", 1144, 1424, false, "KPR");
    private final Region BOCHENSKI = new Region("bocheński", 1175, 1493, false, "KBO");
    private final Region BRZESKI = new Region("brzeski", 1211, 1495, false, "KBR");
    private final Region NOWOSADECKI_MIASTO = new Region("nowosądecki - miasto", 1221, 1589, false, "KN");
    private final Region NOWOSADECKI = new Region("nowosądecki", 1250, 1590, false, "KNS");
    private final Region TARNOWSKI = new Region("tarnowski", 1252, 1516, false, "KTA");
    private final Region TARNOWSKI_MIASTO = new Region("tarnowski - miasto", 1267, 1474, false, "KT");
    private final Region DABROWSKI = new Region("dąbrowski", 1276, 1422, false, "KDA");
    private final Region GORLICKI = new Region("gorlicki", 1310, 1587, false, "KGR");


    public final Collection<Region> MALOPOLSKIE_POWIATY = Arrays.asList(OSWIECIMSKI, CHRZANOWSKI, WADOWICKI, OLKUSKI, SUSKI, KRAKOWSKI, MYSLENICKI, NOWOSADECKI, NOWOSADECKI_MIASTO,
            TATRZANSKI, NOWOTARSKI, KRAKOWSKI_MIASTO, MIECHOWSKI, WIELICKI, LIMANOWSKI, PROSZOWICKI, BOCHENSKI, BRZESKI, TARNOWSKI, TARNOWSKI_MIASTO, DABROWSKI, GORLICKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(964, 1487),
                new Point(996, 1454),
                new Point(1013, 1514),
                new Point(1028, 1394),
                new Point(1035, 1566),
                new Point(1067, 1434),
                new Point(1086, 1534),
                new Point(1088, 1621),
                new Point(1095, 1467),
                new Point(1102, 1667),
                new Point(1108, 1374),
                new Point(1128, 1490),
                new Point(1137, 1457),
                new Point(1144, 1565),
                new Point(1144, 1424),
                new Point(1175, 1493),
                new Point(1211, 1495),
                new Point(1221, 1589),
                new Point(1250, 1590),
                new Point(1252, 1516),
                new Point(1267, 1474),
                new Point(1276, 1422),
                new Point(1310, 1587)
        };
    }

    private static Malopolskie instance;

    public static Malopolskie getInstance() {
        if (instance == null) {
            instance = new Malopolskie();
        }
        return instance;
    }
}
