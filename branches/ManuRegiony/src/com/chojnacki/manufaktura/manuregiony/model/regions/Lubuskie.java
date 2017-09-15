package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Lubuskie region
 */
public class Lubuskie {

    private final Region SLUBICKI = new Region("słubicki", 208, 819, false, "FSL");
    private final Region ZARSKI = new Region("żarski", 233, 1007, false, "FZA");
    private final Region KROSNIENSKI = new Region("krośnieński", 248, 913, false, "FKR");
    private final Region GORZOWSKI = new Region("gorzowski", 265, 734, false, "FGW");
    private final Region SULECINSKI = new Region("sulęciński", 282, 808, false, "FSU");
    private final Region GORZOWSKI_MIASTO = new Region("gorzowski - miasto", 298, 734, false, "FG");
    private final Region ZAGANSKI = new Region("żagański", 318, 1031, false, "FZG");
    private final Region SWIEBODZINSKI = new Region("świebodziński", 330, 860, false, "FSW");
    private final Region ZIELONOGORSKI_MIASTO = new Region("zielnogórski - miasto", 333, 948, false, "FZ");
    private final Region MIEDZYRZECKI = new Region("międzyrzecki", 359, 796, false, "FMI");
    private final Region ZIELONOGORSKI = new Region("zielnogórski", 366, 921, false, "FZI");
    private final Region NOWOSOLSKI = new Region("nowosolski", 366, 990, false, "FNW");
    private final Region STRZELECKO_DREZDENECKI = new Region("strzelecko - drezdenecki", 373, 697, false, "FSD");
    private final Region WSCHOWSKI = new Region("wschowski", 445, 983, false, "FWS");


    public final Collection<Region> LUBUSKIE_POWIATY = Arrays.asList(SLUBICKI, ZARSKI, KROSNIENSKI, GORZOWSKI, SULECINSKI, GORZOWSKI_MIASTO, ZAGANSKI, SWIEBODZINSKI,
            ZIELONOGORSKI_MIASTO, MIEDZYRZECKI, ZIELONOGORSKI, NOWOSOLSKI, STRZELECKO_DREZDENECKI, WSCHOWSKI);

    public static Point[] getCoordinates() {
        return new Point[]{new Point(208, 819),
                new Point(233, 1007),
                new Point(248, 913),
                new Point(265, 734),
                new Point(282, 808),
                new Point(298, 734),
                new Point(318, 1031),
                new Point(330, 860),
                new Point(333, 948),
                new Point(359, 796),
                new Point(366, 921),
                new Point(366, 990),
                new Point(373, 697),
                new Point(445, 983)};
    }

    private static Lubuskie instance;

    public static Lubuskie getInstance() {
        if (instance == null) {
            instance = new Lubuskie();
        }
        return instance;
    }
}
