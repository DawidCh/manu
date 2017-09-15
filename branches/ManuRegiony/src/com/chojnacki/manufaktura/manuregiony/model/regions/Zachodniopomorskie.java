package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Container for lodzkie subregions
 */
public class Zachodniopomorskie {

    private static Zachodniopomorskie instance;

    private final Region SLAWNO = new Region("sławieński", 524, 306, false, "ZSL");
    private final Region KOSZALIN_MIASTO = new Region("koszaliński - miasto", 475, 344, false, "ZK");
    private final Region KOSZALIN_POWIAT = new Region("koszaliński", 511, 365, false, "ZKO");
    private final Region KOLOBRZEG = new Region("kołobrzeski", 378, 374, false, "ZKL");
    private final Region GRYFICE = new Region("gryficki", 308, 405, false, "ZGY");
    private final Region GRYFINO = new Region("gryfiński", 178, 640, false, "ZGR");
    private final Region KAMIEN_POM = new Region("kamieński", "ZKA", "pl", new Point(254, 416), new Point(235, 399));
    private final Region SWINOUJSCIE = new Region("świnoujście - miasto", 204, 406, false, "ZSW");
    private final Region POLICE = new Region("policki", 178, 501, false, "ZPL");
    private final Region SZCZECIN = new Region("szczeciński", "ZS", "pl", new Point(206, 542), new Point(180, 426), new Point(167, 426), new Point(155, 419));
    private final Region PYRZYCE = new Region("pyrzycki", 252, 617, false, "ZPY");
    private final Region MYSLIBOR = new Region("myśliborski", 240, 678, false, "ZMY");
    private final Region GOLENIOW = new Region("goleniowski", 257, 489, false, "ZGL");
    private final Region STARGARD = new Region("stargardzki", 302, 566, false, "ZST");
    private final Region CHOSZCZNO = new Region("choszczeński", 362, 621, false, "ZCH");
    private final Region WALCZ = new Region("wałecki", 471, 598, false, "ZWA");
    private final Region DRAWSKO_POM = new Region("drawski", 433, 533, false, "ZDR");
    private final Region LOBEZ = new Region("łobezki", 348, 485, false, "ZLO");
    private final Region SWIDWINSKI = new Region("świdwiński", 420, 452, false, "ZSD");
    private final Region BIALOGARD = new Region("białogardzki", 444, 399, false, "ZBI");
    private final Region SZCZECINEK = new Region("szczecinecki", 523, 458, false, "ZSZ");

    public final Collection<Region> ZACHODNIOPOMIRSKIE_POWIATY = Arrays.asList(
            SLAWNO, KOSZALIN_MIASTO, KOSZALIN_POWIAT, KOLOBRZEG, GRYFICE,
                    GRYFINO, SZCZECIN, POLICE, SWINOUJSCIE, KAMIEN_POM,
                    PYRZYCE, MYSLIBOR, GOLENIOW, STARGARD, CHOSZCZNO,
                    BIALOGARD, SWIDWINSKI, LOBEZ, DRAWSKO_POM, WALCZ,
                    SZCZECINEK);


    public static Zachodniopomorskie getInstance() {
        if (instance == null) {
            instance = new Zachodniopomorskie();
        }
        return instance;
    }
    public static Point[] getCoordinates() {
        return new Point[]{new Point(178, 501),//
                new Point(178, 640),//
                new Point(204, 406),//
                new Point(206, 542),//
                new Point(240, 678),//
                new Point(252, 617),//
                new Point(254, 416),//
                new Point(257, 489),//
                new Point(302, 566),//
                new Point(308, 405),//
                new Point(348, 485),//
                new Point(362, 621),//
                new Point(378, 374),//
                new Point(420, 452),//
                new Point(433, 533),//
                new Point(444, 399),//
                new Point(471, 598),//
                new Point(475, 344),//
                new Point(511, 365),//
                new Point(523, 458),//
                new Point(524, 306),//
                new Point(235, 399),//
                new Point(180, 426),//
                new Point(167, 426),
                new Point(155, 419)};
    }
}
