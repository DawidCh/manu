package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Mazowieckie region
 */
public class Mazowieckie {

    private final Region GOSTYNINSKI = new Region("gostyński", 993, 829, false, "WGO");
    private final Region SIERPECKI = new Region("sierpecki", 1030, 719, false, "WSE");
    private final Region ZUROMINSKI = new Region("żuromiński", 1063, 664, false, "WZU");
    private final Region SOCHACZEWSKI = new Region("sochaczewski", 1116, 870, false, "WSO");
    private final Region PLOCKI = new Region("płocki", new String [] {"WPL", "WP"}, false, "pl", new Point(1036,800), new Point(1078, 793));
    private final Region MLAWSKI = new Region("mławski", 1143, 658, false, "WML");
    private final Region ZYRARDOWSKI = new Region("żyrardowski", 1149, 934, false, "WZY");
    private final Region PLONSKI = new Region("płoński", 1155, 781, false, "WPN");
    private final Region GRODZISKI = new Region("grodziski", 1184, 915, false, "WGM");
    private final Region CIECHANOWSKI = new Region("ciechanowski", 1185, 713, false, "WCI");
    private final Region WARSZAWSKI_ZACHODNI = new Region("warszawski - zachodni", 1198, 867, false, "WZ");
    private final Region PRZYSUSKI = new Region("przysuski", 1201, 1096, false, "WPY");
    private final Region NOWODWORSKI = new Region("nowodworski", 1201, 815, false, "WND");
    private final Region PRUSZKOWSKI = new Region("pruszkowski", 1220, 909, false, "WPR");
    private final Region GROJECKI = new Region("grójecki", 1228, 987, false, "WGR");
    private final Region PRZASNYCKI = new Region("prasnycki", 1229, 634, false, "WPZ");
    private final Region LEGIONOWSKI = new Region("legionowski", 1249, 818, false, "WL");
    private final Region BIALOBRZESKI = new Region("bialobrzeski", 1251, 1040, false, "WBI");
    private final Region PULTUSKI = new Region("pułtuski", 1259, 757, false, "WPU");
    private final Region PIASECZYNSKI = new Region("piaseczyński", 1260, 935, false, "WPI");
    private final Region WARSZAWSKI = new Region("warszawski", 1264, 872, false, "WW", "WU", "WY", "WA", "WD", "WJ", "WB", "WH", "WI", "WF", "WT", "WE", "WK",
            "WN", "WX");
    private final Region MAKOWSKI = new Region("makowski", 1284, 685, false, "WMA");
    private final Region RADOMSKI_MIASTO = new Region("radomski - miasto", 1285, 1099, false, "WR");
    private final Region RADOMSKI = new Region("radomski", 1296, 1141, false, "WRA");
    private final Region WOLOMINSKI = new Region("wołomiński", 1310, 823, false, "WWL", "WV");
    private final Region OSTROLECKI = new Region("ostrołęcki", 1319, 610, false, "WOS");
    private final Region OTWOCKI = new Region("otwocki", 1320, 930, false, "WOT");
    private final Region KOZIENICKI = new Region("kozienicki", 1320, 1033, false, "WKO");
    private final Region OSTROLECKI_MIASTO = new Region("ostrołęcki - miasto", 1342, 646, false, "WO");
    private final Region ZWOLENSKI = new Region("zwoleński", 1361, 1108, false, "WZW");
    private final Region LIPSKI = new Region("lipski", 1364, 1164, false, "WLI");
    private final Region MINSKI = new Region("miński", 1365, 886, false, "WMI");
    private final Region GARWOLINSKI = new Region("garwoliński", 1368, 978, false, "WG");
    private final Region WEGROWSKI = new Region("węgrowski", 1402, 806, false, "WWE");
    private final Region OSTROWSKI = new Region("ostrowski", 1409, 723, false, "WOR");
    private final Region SIEDLECKI = new Region("siedlecki", 1438, 887, false, "WSI");
    private final Region SIEDLECKI_MIASTO = new Region("siedlecki - miasto", 1467, 885, false, "WS");
    private final Region SOKOLOWSKI = new Region("sokołowski", 1471, 800, false, "WSK");
    private final Region LOSICKI = new Region("łosicki", 1569, 862, false, "WLS");
    private final Region WYSZKOW = new Region("wyszkowski", 1330, 760, false, "WWY");


    public final Collection<Region> MAZOWIECKIE_POWIATY = Arrays.asList(GOSTYNINSKI, SIERPECKI, ZUROMINSKI, SOCHACZEWSKI, PLOCKI, MLAWSKI, ZYRARDOWSKI, PLONSKI, GRODZISKI,
            CIECHANOWSKI, WARSZAWSKI_ZACHODNI, PRZYSUSKI, NOWODWORSKI, PRUSZKOWSKI, GROJECKI, PRZASNYCKI, LEGIONOWSKI, BIALOBRZESKI, PULTUSKI, PIASECZYNSKI, WARSZAWSKI,
            MAKOWSKI, RADOMSKI_MIASTO, RADOMSKI, WOLOMINSKI, OSTROLECKI, OTWOCKI, KOZIENICKI, OSTROLECKI_MIASTO, ZWOLENSKI, LIPSKI, MINSKI, GARWOLINSKI, WEGROWSKI, OSTROWSKI,
            SIEDLECKI, SIEDLECKI_MIASTO, SOKOLOWSKI, LOSICKI, WYSZKOW);
    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(993, 829),
                new Point(1030, 719),
                new Point(1036, 798),
                new Point(1063, 664),
                new Point(1078, 793),
                new Point(1116, 870),
                new Point(1143, 658),
                new Point(1149, 934),
                new Point(1155, 781),
                new Point(1184, 915),
                new Point(1185, 713),
                new Point(1198, 867),
                new Point(1201, 1096),
                new Point(1201, 815),
                new Point(1220, 909),
                new Point(1228, 987),
                new Point(1229, 634),
                new Point(1239, 1142),
                new Point(1249, 818),
                new Point(1251, 1040),
                new Point(1259, 757),
                new Point(1260, 935),
                new Point(1264, 872),
                new Point(1284, 685),
                new Point(1285, 1099),
                new Point(1296, 1141),
                new Point(1310, 823),
                new Point(1319, 610),
                new Point(1320, 930),
                new Point(1320, 1033),
                new Point(1330, 758),
                new Point(1342, 646),
                new Point(1361, 1108),
                new Point(1364, 1164),
                new Point(1365, 886),
                new Point(1368, 978),
                new Point(1402, 806),
                new Point(1409, 723),
                new Point(1438, 887),
                new Point(1467, 885),
                new Point(1471, 800),
                new Point(1569, 862)};
    }

    private static Mazowieckie instance;

    public static Mazowieckie getInstance() {
        if (instance == null) {
            instance = new Mazowieckie();
        }
        return instance;
    }
}
