package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Dolnoslaskie region
 */
public class Dolnoslaskie {


    private final Region ZGORELECKI = new Region("zgorzelecki", 260, 1129, false, "DZG");
    private final Region BOLESLAWSKI = new Region("bolesławiecki", 327, 1113, false, "DBL");
    private final Region POLKOWICKI = new Region("polkowicki", 413, 1075, false, "DPL");
    private final Region GLOGOWSKI = new Region("głogowski", 421, 1015, false, "DGL");
    private final Region GOROWSKI = new Region("górowski", 504, 1040, false, "DGR");
    private final Region MILICKI = new Region("milicki", 636, 1079, false, "DMI");
    private final Region LUBINSI = new Region("lubiński", 456, 1101, false, "DLU");
    private final Region WOLOWSKI = new Region("wołowski", 510, 1127, false, "DWL");
    private final Region TRZEBNICKI = new Region("trzebnicki", 566, 1123, false, "DTR");
    private final Region OLESNICKI = new Region("oleśnicki", 664, 1143, false, "DOL");
    private final Region LUBANSKI = new Region("lubański", 281, 1173, false, "DLB");
    private final Region LWOWECKI = new Region("lwówecki", 330, 1192, false, "DLW");
    private final Region ZLOTORYJSKI = new Region("złotoryjski", 383, 1174, false, "DZL");
    private final Region LEGNICKI = new Region("legnicki", 457, 1155, false, "DLA");
    private final Region SREDZKI = new Region("średzki", 504, 1182, false, "DSR");
    private final Region WROCLAWSKI_MIASTO = new Region("wrocławski - miasto", 569, 1178, false, "DW");
    private final Region WROCLAWSKI = new Region("wrocławski", 560, 1221, false, "DWR");
    private final Region OLAWSKI = new Region("oławski", 628, 1230, false, "DOA");
    private final Region JELENIOGORSKI = new Region("jeleniogórski", "DJE", "pl", new Point(330, 1234), new Point(373, 1252));
    private final Region JELENIOGORSKI_MIASTO = new Region("jeleniogórski - miasto", 353, 1237, false, "DJ");
    private final Region JAWORSKI = new Region("jaworski", 421, 1199, false, "DJA");
    private final Region KAMIENNOGORSKI = new Region("kamiennogórski", 398, 1272, false, "DKA");
    private final Region WALBRZYSKI = new Region("wałbrzyski", 447, 1271, false, "DBA");
    private final Region SWIDNICKI = new Region("świdnicki", 473, 1235, false, "DSW");
    private final Region DZIERZONIOWSKI = new Region("dzierżoniowski", 525, 1287, false, "DDZ");
    private final Region STRZELINSKI = new Region("strzeliński", 584, 1273, false, "DST");
    private final Region ZABKOWSKI = new Region("ząbkowski", 552, 1326, false, "DZA");
    private final Region KLODZKI = new Region("kłodzki", 500, 1375, false, "DKL");


    public final Collection<Region> DOLNOSLASKIE_POWIATY = Arrays.asList(ZGORELECKI, BOLESLAWSKI, POLKOWICKI, GLOGOWSKI, GOROWSKI, MILICKI, LUBINSI, WOLOWSKI, TRZEBNICKI,
            OLESNICKI, LUBANSKI, LWOWECKI, ZLOTORYJSKI, LEGNICKI, SREDZKI, WROCLAWSKI, WROCLAWSKI_MIASTO, OLAWSKI, JELENIOGORSKI, JELENIOGORSKI_MIASTO, JAWORSKI, KAMIENNOGORSKI,
            WALBRZYSKI, SWIDNICKI, DZIERZONIOWSKI, STRZELINSKI, ZABKOWSKI, KLODZKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(260, 1129),
                new Point(281, 1173),
                new Point(327, 1113),
                new Point(330, 1192),
                new Point(330, 1234),
                new Point(353, 1237),
                new Point(373, 1252),
                new Point(383, 1174),
                new Point(398, 1272),
                new Point(413, 1075),
                new Point(421, 1199),
                new Point(447, 1271),
                new Point(456, 1101),
                new Point(457, 1155),
                new Point(473, 1235),
                new Point(500, 1375),
                new Point(504, 1040),
                new Point(504, 1182),
                new Point(510, 1127),
                new Point(525, 1287),
                new Point(552, 1326),
                new Point(560, 1221),
                new Point(566, 1123),
                new Point(569, 1178),
                new Point(584, 1273),
                new Point(628, 1230),
                new Point(636, 1079),
                new Point(664, 1143),
                new Point(421, 1015)};
    }

    private static Dolnoslaskie instance;

    public static Dolnoslaskie getInstance() {
        if (instance == null) {
            instance = new Dolnoslaskie();
        }
        return instance;
    }
}
