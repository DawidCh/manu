package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Slaskie region
 */
public class Slaskie {

    private final Region RACIBORSKI = new Region("raciborski", 776, 1456, false, "SRA");
    private final Region RYBNICKI = new Region("rybnicki", "SRY", "pl", new Point(812, 1457), new Point(838, 1458), new Point(862, 1451));
    private final Region GLIWICKI = new Region("gliwicki", 831, 1381, false, "SGL");
    private final Region WODZISLAWSKI = new Region("wodzisławski", 813, 1484, false, "SWO");
    private final Region JASTRZEBIE_ZDROJ = new Region("jastrzębie - zdrój", 849, 1502, false, "SJZ");
    private final Region MIKOLOWSKI = new Region("mikołowski", 888, 1445, false, "SMI");
    private final Region ZORY = new Region("żorski", 866, 1476, false, "SZO");
    private final Region LUBLINIECKI = new Region("lubliniecki", 871, 1306, false, "SLU");
    private final Region CIESZYNSKI = new Region("cieszyński", 872, 1545, false, "SCI");
    private final Region TARNOGORSKI = new Region("tarnogórski", 889, 1357, false, "STA");
    private final Region ZABRZE_BYTOM_PIEKARY_RUDA_SWIETOCHLOWICE_CHORZOW_SIEMIANOWICE = new Region("okolice Katowic", 892, 1399,
            false, "SZ", "SL", "SRS", "SY", "SPA", "SI", "SH", "SW");
    private final Region KLOBUCKI = new Region("kłobucki", 894, 1238, false, "SKL");
    private final Region PSZCZYNSKI = new Region("pszczyński", 903, 1491, false, "SPS");
    private final Region BIELSKI = new Region("bielski", "SBI", "pl", new Point(911, 1519), new Point(927, 1560), new Point(939, 1541));
    private final Region TYCHY = new Region("tyski - miasto", 919, 1455, false, "ST");
    private final Region KATOWICE = new Region("katowicki - miasto", 920, 1429, false, "SK");
    private final Region BIELSKO_BIALA = new Region("bielsko - bielski - miasto", 927, 1534, false, "SB");
    private final Region BEDZINSKI = new Region("będziński", 938, 1377, false, "SBE");
    private final Region CZESTOCHOWSKI_MIASTO = new Region("częstochowski - miasto", 944, 1269, false, "SC");
    private final Region MYSLOWICE = new Region("mysłowicki - miasto", 944, 1435, false, "SM");
    private final Region SOSNOWIEC = new Region("sosnowski - miasto", 950, 1412, false, "SO");
    private final Region ZYWIECKI = new Region("żywiecki", 952, 1587, false, "SZY");
    private final Region MYSZKOWSKI = new Region("myszkowski", 967, 1321, false, "SMY");
    private final Region DABROWSKI = new Region("dąbrowski", "SD", "pl", new Point(967, 1388), new Point(981, 1406));
    private final Region JAWORZNO = new Region("jaworzański", 972, 1428, false, "SJ");
    private final Region CZESTOCHOWSKI = new Region("częstochowski", 1000, 1270, false, "SCZ");
    private final Region ZAWIERCIANSKI = new Region("zawierciański", 1030, 1344, false, "SZA");
    private final Region BIERUNSKO_LEDZINSKI = new Region("bieruńsko - lędziński", 939, 1461, false, "SBL");

    public final Collection<Region> SLASKIE_POWIATY = Arrays.asList(RACIBORSKI, RYBNICKI, GLIWICKI, WODZISLAWSKI, JASTRZEBIE_ZDROJ, MIKOLOWSKI, ZORY, LUBLINIECKI,
            CIESZYNSKI, TARNOGORSKI, ZABRZE_BYTOM_PIEKARY_RUDA_SWIETOCHLOWICE_CHORZOW_SIEMIANOWICE, KLOBUCKI, PSZCZYNSKI, BIELSKI, TYCHY, KATOWICE, BIELSKO_BIALA, BEDZINSKI,
            CZESTOCHOWSKI_MIASTO, MYSLOWICE, SOSNOWIEC, ZYWIECKI, MYSZKOWSKI, DABROWSKI, JAWORZNO, CZESTOCHOWSKI, ZAWIERCIANSKI, BIERUNSKO_LEDZINSKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(776, 1456),
                new Point(812, 1457),
                new Point(813, 1484),
                new Point(831, 1381),
                new Point(838, 1458),
                new Point(849, 1502),
                new Point(862, 1451),
                new Point(866, 1476),
                new Point(871, 1306),
                new Point(872, 1545),
                new Point(888, 1445),
                new Point(889, 1357),
                new Point(892, 1399),
                new Point(894, 1238),
                new Point(903, 1491),
                new Point(911, 1519),
                new Point(919, 1455),
                new Point(920, 1429),
                new Point(927, 1560),
                new Point(927, 1534),
                new Point(938, 1377),
                new Point(944, 1269),
                new Point(944, 1435),
                new Point(950, 1412),
                new Point(952, 1587),
                new Point(967, 1321),
                new Point(967, 1388),
                new Point(972, 1428),
                new Point(981, 1406),
                new Point(1000, 1270),
                new Point(1030, 1344),
                new Point(939, 1541),
                new Point(939, 1461)
        };
    }

    private static Slaskie instance;

    public static Slaskie getInstance() {
        if (instance == null) {
            instance = new Slaskie();
        }
        return instance;
    }
}
