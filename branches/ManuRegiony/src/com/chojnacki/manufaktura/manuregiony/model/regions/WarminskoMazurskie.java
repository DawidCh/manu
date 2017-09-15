package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Warminsko-mazurskie region
 */
public class WarminskoMazurskie {

    private static WarminskoMazurskie instance;
    private final Region ELBLAG_MIASTO = new Region("elbląski - miasto", 990, 353, false, "NE");
    private final Region ILAWSKI = new Region("iławski", 1002, 495, false, "NIL");
    private final Region NOWOMIEJSKI = new Region("nowomiejski", 1002, 555, false, "NNM");
    private final Region ELBLASKI = new Region("elbląski", 1015, 364, false, "NEB");
    private final Region OSTRODZKI = new Region("ostródzki", 1075, 477, false, "NOS");
    private final Region BRANIEWSKI = new Region("braniewski", 1077, 321, false, "NBR");
    private final Region DZIALDOWSKI = new Region("działdowski", 1086, 598, false, "NDZ");
    private final Region OLSZTYNSKI_MIASTO = new Region("olsztyński - miasto", 1157, 461, false, "NO");
    private final Region NIDZICKI = new Region("nidzicki", 1160, 572, false, "NID");
    private final Region BARTOSZYCKI = new Region("bartoszycki", 1186, 325, false, "NBA");
    private final Region OLSZTYNSKI = new Region("olsztyński", 1195, 438, false, "NOL");
    private final Region SZCZYCIENSKI = new Region("szczycieński", 1235, 524, false, "NSZ");
    private final Region KETRZYNSKI = new Region("kętrzyński", 1289, 354, false, "NKE");
    private final Region MRAGOWSKI = new Region("mrągowski", 1296, 444, false, "NMR");
    private final Region WEGORZEWSKI = new Region("węgorzewski", 1363, 336, false, "NWE");
    private final Region GIZYCKI = new Region("giżycki", 1380, 398, false, "NGI");
    private final Region PISKI = new Region("piski", 1387, 476, false, "NPI");
    private final Region GOLDAPSKI = new Region("gołdapski", 1429, 318, false, "NGO");
    private final Region OLECKI = new Region("olęcki", 1462, 374, false, "NOE", "NOG");
    private final Region ELCKI = new Region("ełcki", 1462, 438, false, "NEL");
    private final Region LIDZBARSKI = new Region("lidzbarski", 1141, 357, false, "NLI");


    public final Collection<Region> WARMINSKO_MAZURSKIE_POWIATY = Arrays.asList(ELBLAG_MIASTO, ILAWSKI, NOWOMIEJSKI, ELBLASKI, OSTRODZKI, DZIALDOWSKI, BRANIEWSKI, OLSZTYNSKI_MIASTO,
            NIDZICKI, BARTOSZYCKI, OLSZTYNSKI, SZCZYCIENSKI, KETRZYNSKI, MRAGOWSKI, WEGORZEWSKI, GIZYCKI, PISKI, GOLDAPSKI, OLECKI, ELCKI, LIDZBARSKI);

    public static Point[] getCoordinates() {
        return new Point[]{new Point(990, 353),
                new Point(1002, 495),
                new Point(1002, 555),
                new Point(1015, 364),
                new Point(1075, 477),
                new Point(1077, 321),
                new Point(1086, 598),
                new Point(1157, 461),
                new Point(1160, 572),
                new Point(1186, 325),
                new Point(1195, 438),
                new Point(1235, 524),
                new Point(1289, 354),
                new Point(1296, 444),
                new Point(1363, 336),
                new Point(1380, 398),
                new Point(1387, 476),
                new Point(1429, 318),
                new Point(1462, 374),
                new Point(1462, 438),
                new Point(1141, 357)};
    }

    public static WarminskoMazurskie getInstance() {
        if (instance == null) {
            instance = new WarminskoMazurskie();
        }
        return instance;
    }
}
