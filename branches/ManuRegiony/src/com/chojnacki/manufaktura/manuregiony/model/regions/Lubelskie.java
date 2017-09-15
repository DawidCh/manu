package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Lubelskie region
 */
public class Lubelskie {

    private static Lubelskie instance;
    private final Region OPOLESKI = new Region("opoleski", 1428, 1173, false, "LOP");
    private final Region PULAWSKI = new Region("puławski", 1435, 1092, false, "LPU");
    private final Region LUKOWSKI = new Region("łukowski", 1446, 959, false, "LLU");
    private final Region KRASNICKI = new Region("kraśnicki", 1462, 1224, false, "LKR");
    private final Region LUBELSKI = new Region("lubelski", 1490, 1154, false, "LUB");
    private final Region JANOWSKI = new Region("janowski", 1512, 1275, false, "LJA");
    private final Region LUBARTOWSKI = new Region("lubartowski", 1526, 1056, false, "LLB");
    private final Region LUBELSKI_MIASTO = new Region("lubelski - miasto", 1526, 1134, false, "LU");
    private final Region RADZYNSKI = new Region("radzyński", 1528, 981, false, "LRA");
    private final Region SWIDNICKI = new Region("świdnicki", 1567, 1148, false, "LSW");
    private final Region BILGORAJSKI = new Region("biłgorajski", 1575, 1334, false, "LBL");
    private final Region PARCZEWSKI = new Region("parczewski", 1588, 1023, false, "LPA");
    private final Region ZAMOJSKI = new Region("zamojski", 1610, 1269, false, "LZA");
    private final Region BIALSKI_MIASTO = new Region("bialski - misto", 1613, 917, false, "LB");
    private final Region KRASNOSTAWSKI = new Region("krasnostawski", 1623, 1209, false, "LKS");
    private final Region BIALSKI = new Region("bialski", 1639, 946, false, "LBI");
    private final Region CHELMSKI = new Region("chełmski", 1645, 1147, false, "LCH");
    private final Region ZAMOJSKI_MIASTO = new Region("zamojski", 1653, 1269, false, "LZ");
    private final Region WLODAWSKI = new Region("zamojski - miasto", 1662, 1052, false, "LWL");
    private final Region CHELMSKI_MIASTO = new Region("chełmski - miasto", 1683, 1155, false, "LC");
    private final Region TOMASZOWSKI = new Region("tomaszowski", 1712, 1327, false, "LTM");
    private final Region HRUBIESZOWSKI = new Region("hrubieszowski", 1750, 1246, false, "LHR");
    private final Region RYCKI = new Region("rycki", 1427, 1022, false, "LRY");
    private final Region LECZYNSKI = new Region("łęczyński", 1593, 1118, false, "LLE");


    public final Collection<Region> LUBELSKIE_POWIATY = Arrays.asList(OPOLESKI, PULAWSKI, LUKOWSKI, KRASNICKI, LUBELSKI, JANOWSKI, LUBARTOWSKI, LUBELSKI_MIASTO, RADZYNSKI, SWIDNICKI,
            BILGORAJSKI, PARCZEWSKI, BIALSKI, BIALSKI_MIASTO, CHELMSKI, ZAMOJSKI, ZAMOJSKI_MIASTO, WLODAWSKI, CHELMSKI_MIASTO, TOMASZOWSKI, HRUBIESZOWSKI, RYCKI, LECZYNSKI,
            KRASNOSTAWSKI);

    public static Lubelskie getInstance() {
        if (instance == null) {
            instance = new Lubelskie();
        }
        return instance;
    }

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(1428, 1173),
                new Point(1435, 1092),
                new Point(1446, 959),
                new Point(1462, 1224),
                new Point(1462, 1224),
                new Point(1462, 1224),
                new Point(1462, 1224),
                new Point(1490, 1154),
                new Point(1512, 1275),
                new Point(1514, 1276),
                new Point(1526, 1056),
                new Point(1526, 1134),
                new Point(1528, 981),
                new Point(1567, 1148),
                new Point(1575, 1334),
                new Point(1588, 1023),
                new Point(1610, 1269),
                new Point(1610, 1271),
                new Point(1610, 1271),
                new Point(1610, 1272),
                new Point(1613, 917),
                new Point(1623, 1209),
                new Point(1639, 946),
                new Point(1645, 1147),
                new Point(1653, 1269),
                new Point(1654, 1271),
                new Point(1662, 1052),
                new Point(1683, 1155),
                new Point(1712, 1327),
                new Point(1750, 1246),
                new Point(1427, 1022),
                new Point(1593, 1118)
        };
    }
}
