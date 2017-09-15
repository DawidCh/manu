package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Podkarpackie region
 */
public class Podkarpackie {

    private final Region DEBICKI = new Region("dębicki", 1327, 1477, false, "RDE");
    private final Region MIELECKI = new Region("mielecki", 1332, 1398, false, "RMI");
    private final Region JASIELSKI = new Region("jasielski", 1351, 1572, false, "RJS");
    private final Region ROBCZYCKO_SEDZISZEWSKI = new Region("robczycko - sędziszewski", 1381, 1459, false, "RRS");
    private final Region TARNOBRZESKI_MIASTO = new Region("tarnobrzeski - miasto", 1389, 1318, false, "RT");
    private final Region KOLBUSZOWSKI = new Region("kolbuszowski", 1400, 1398, false, "RKO");
    private final Region KROSNIENSKI = new Region("krośnieński", 1401, 1592, false, "RKR");
    private final Region KROSNIENSKI_MIASTO = new Region("krośnieński - miasto", 1401, 1592, false, "RK");
    private final Region TARNOBRZESKI = new Region("tarnobrzeski", 1407, 1333, false, "RTA");
    private final Region RZESZOWSKI_MIASTO = new Region("rzeszowski - miasto", 1443, 1467, false, "RZ");
    private final Region STALOWOLSKI = new Region("stalowolski", 1444, 1296, false, "RST");
    private final Region RZESZOWSKI = new Region("rzeszowski", 1444, 1433, false, "RZE");
    private final Region STRZYZOWSKO_BRZOZOWSKI = new Region("strzyżowsko - brzozowski", 1451, 1546, false, "RSR", "RBR");
    private final Region SANOCKI = new Region("sanocki", 1468, 1610, false, "RSA");
    private final Region LANCUCKI = new Region("łańcucki", 1490, 1444, false, "RLA");
    private final Region NIZANSKI = new Region("niżański", 1491, 1341, false, "RNI");
    private final Region LESKI = new Region("leski", 1517, 1651, false, "RLS");
    private final Region LEZAJSKI = new Region("leżajski", 1517, 1394, false, "RLE");
    private final Region PRZEWORSKI = new Region("przeworski", 1524, 1461, false, "RPZ");
    private final Region PRZEMYSKI = new Region("przemyski", 1539, 1540, false, "RPR");
    private final Region BIESZCZADZKI = new Region("bieszczadzki", 1561, 1641, false, "RBI");
    private final Region PRZEMYSKI_MIASTO = new Region("przemyski - miasto", 1584, 1530, false, "RP");
    private final Region JAROSLAWSKI = new Region("jarosławski", 1585, 1465, false, "RJA");
    private final Region LUBACZOWSKI = new Region("lubaczowski", 1654, 1414, false, "RLU");


    public final Collection<Region> PODKARPACKIE_POWIATY = Arrays.asList(DEBICKI, MIELECKI, JASIELSKI, ROBCZYCKO_SEDZISZEWSKI, TARNOBRZESKI_MIASTO, KOLBUSZOWSKI, KROSNIENSKI,
            KROSNIENSKI_MIASTO, TARNOBRZESKI, RZESZOWSKI_MIASTO, STALOWOLSKI, RZESZOWSKI, STRZYZOWSKO_BRZOZOWSKI, SANOCKI, LANCUCKI, NIZANSKI, LESKI, LEZAJSKI, PRZEWORSKI,
            PRZEMYSKI, BIESZCZADZKI, PRZEMYSKI_MIASTO, JAROSLAWSKI, LUBACZOWSKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(1327, 1477),
                new Point(1332, 1398),
                new Point(1351, 1572),
                new Point(1381, 1459),
                new Point(1389, 1318),
                new Point(1400, 1398),
                new Point(1401, 1592),
                new Point(1405, 1560),
                new Point(1407, 1333),
                new Point(1443, 1467),
                new Point(1444, 1296),
                new Point(1444, 1433),
                new Point(1451, 1546),
                new Point(1468, 1610),
                new Point(1490, 1444),
                new Point(1491, 1341),
                new Point(1517, 1651),
                new Point(1517, 1394),
                new Point(1524, 1461),
                new Point(1539, 1540),
                new Point(1561, 1641),
                new Point(1584, 1530),
                new Point(1585, 1465),
                new Point(1654, 1414)
        };
    }

    private static Podkarpackie instance;

    public static Podkarpackie getInstance() {
        if (instance == null) {
            instance = new Podkarpackie();
        }
        return instance;
    }
}
