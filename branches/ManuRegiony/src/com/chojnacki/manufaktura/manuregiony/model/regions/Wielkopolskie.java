package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Wielkopolskie region
 */
public class Wielkopolskie {

    private final Region CZARNKOWSKO_TRZCIANECKI = new Region("czarnkowsko - trzcianecki", 500, 680, false, "PCT");
    private final Region WOLSZTYNSKI = new Region("wolsztyński", 440, 917, false, "PWO");
    private final Region NOWOTOMYSKI = new Region("nowotomyski", 444, 838, false, "PNT");
    private final Region GRODZISKI = new Region("grodziski", 482, 886, false, "PGR");
    private final Region SZAMOTULSKI = new Region("szamotulski", 500, 768, false, "PSZ");
    private final Region LESZCZYNSKI_MIASTO = new Region("leszczyński - miasto", 511, 982, false, "PL");
    private final Region KOSCIANSKI = new Region("kościański", 530, 921, false, "PKS");
    private final Region LESZCZYNSKI = new Region("leszczyński", 537, 966, false, "PLE");
    private final Region OBORNICKI = new Region("obornicki", 563, 747, false, "POB");
    private final Region RAWICKI = new Region("rawicki", 571, 1039, false, "PRA");
    private final Region POZNANSKI = new Region("poznański", 584, 831, false, "PO", "POZ", "PZ");
    private final Region CHODZIESKI = new Region("chodzieski", 585, 682, false, "PCH");
    private final Region ZLOTOWSKI = new Region("zotowski", 589, 558, false, "PZL");
    private final Region SREMSKI = new Region("śremski", 590, 920, false, "PSE");
    private final Region PILSKI = new Region("pilski", 595, 630, false, "PP");
    private final Region GOSTYNSKI = new Region("gostyński", 597, 976, false, "PGS");
    private final Region WAGROWIECKI = new Region("wągrowiecki", 636, 720, false, "PWA");
    private final Region SREDZKI = new Region("średzki", 640, 893, false, "PSR");
    private final Region KROTOSZYNSKI = new Region("krotoszyński", 659, 1008, false, "PKR");
    private final Region JAROCINSKI = new Region("jarociński", 679, 943, false, "PJA");
    private final Region WRZESINSKI = new Region("wrzesiński", 689, 877, false, "PWR");
    private final Region GNIEZNIENSKI = new Region("gnieźnieński", 692, 799, false, "PGN");
    private final Region OSTROWSKI = new Region("ostrowski", 712, 1048, false, "POS");
    private final Region PLESZEWSKI = new Region("pleszewski", 722, 958, false, "PPL");
    private final Region SLUPECKI = new Region("słupecki", 734, 872, false, "PSL");
    private final Region KEPINSKI = new Region("kępiński", 745, 1153, false, "PKE");
    private final Region OSTRZESZOWSKI = new Region("ostrzeszowski", 760, 1092, false, "POT");
    private final Region KALISKI = new Region("kaliski", 786, 982, false, "PKA", "PK");
    private final Region KONINSKI = new Region("koniński", 805, 862, false, "PKO", "PKN", "PN");
    private final Region TURECKI = new Region("turecki", 845, 938, false, "PTU");
    private final Region KOLSKI = new Region("kolski", 884, 870, false, "PTL");
    private final Region MIEDZYCHOD = new Region("miedzychodzkie", 430, 780, false, "PMI");


    public final Collection<Region> WIELKOPOLSKIE_POWIATY = Arrays.asList(CZARNKOWSKO_TRZCIANECKI, WOLSZTYNSKI, NOWOTOMYSKI, GRODZISKI, SZAMOTULSKI, LESZCZYNSKI_MIASTO,
            KOSCIANSKI, LESZCZYNSKI, OBORNICKI, RAWICKI, POZNANSKI, CHODZIESKI, ZLOTOWSKI, SREMSKI, PILSKI, GOSTYNSKI, WAGROWIECKI, SREDZKI, KROTOSZYNSKI, JAROCINSKI,
            WRZESINSKI, GNIEZNIENSKI, OSTROWSKI, PLESZEWSKI, SLUPECKI, KEPINSKI, OSTRZESZOWSKI, KALISKI, KONINSKI, TURECKI, KOLSKI, MIEDZYCHOD);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(433, 770),
                new Point(440, 917),
                new Point(444, 838),
                new Point(482, 886),
                new Point(500, 768),
                new Point(511, 982),
                new Point(530, 921),
                new Point(537, 966),
                new Point(563, 747),
                new Point(571, 1039),
                new Point(584, 831),
                new Point(585, 682),
                new Point(589, 558),
                new Point(590, 920),
                new Point(595, 630),
                new Point(597, 976),
                new Point(636, 720),
                new Point(640, 893),
                new Point(659, 1008),
                new Point(679, 943),
                new Point(689, 877),
                new Point(692, 799),
                new Point(712, 1048),
                new Point(722, 958),
                new Point(734, 872),
                new Point(745, 1153),
                new Point(760, 1092),
                new Point(786, 982),
                new Point(805, 862),
                new Point(845, 938),
                new Point(500, 680),
                new Point(884, 870),};
    }

    private static Wielkopolskie instance;

    public static Wielkopolskie getInstance() {
        if (instance == null) {
            instance = new Wielkopolskie();
        }
        return instance;
    }
}
