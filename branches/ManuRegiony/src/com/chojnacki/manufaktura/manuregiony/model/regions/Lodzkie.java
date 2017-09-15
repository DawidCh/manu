package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

import static com.chojnacki.manufaktura.manuregiony.model.Region.*;

/**
 * Container for lodzkie subregions
 */
public class Lodzkie {

    private final Region KUTNO = new Region("kutnowski", 4100, 3700, "EKU", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region LECZYCA = new Region("łęczycki", 3952, 3890, "ELE", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region LOWICZ = new Region("łowicki", 4420, 3870, "ELC", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region ZGIERZ = new Region("zgierski", 4120, 4050, "EZG", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region BRZEZINY = new Region("brzeziński", 4380, 4145, "EBR", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region PODDEBICE = new Region("poddębicki", 3734, 4090, "EPD", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region PABIANICE = new Region("pabianicki", 4050, 4400, "EPA", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region LASK = new Region("łaski", 3890, 4540, "ELA", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region ZDUNSKA = new Region("zduńskowolski", 3770, 4400, "EZD", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region SIERADZ = new Region("sieradzki", 3550, 4440, "ESI", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region WIERUSZOW = new Region("wieruszowski", 3300, 4800, "EWE", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region WIELUN = new Region("wieluński", 3550, 4880, "EWI", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region PAJECZNO = new Region("pajęczański", 3830, 4940, "EPJ", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region BELCHATOW = new Region("bełchatowski", 4000, 4730, "EBE", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region LODZ_POWIAT = new Region("łódzki - powiat", "ELW", UNLOCKED_COLOR, PAINT_SHORTCUT_IN_MAP, new Point[] {new Point(4200, 4400), new Point(4300, 4160)});
    private final Region LODZ_MIASTO = new Region("łódzki - miasto", 4180, 4240, "EL", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, LOCKED_COLOR);
    private final Region RADOMSKO = new Region("radomszczański", 4270, 5100, "ERA", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region PIOTRKOW_MIASTO = new Region("piotrkowski - miasto", 4340, 4660, "EP", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region PIOTRKOW_POWIAT = new Region("piotrkowski - powiat", 4400, 4800, "EPI", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region OPOCZNO = new Region("opoczyński", 4700, 4700, "EOP", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region TOMASZOW_MAZ = new Region("tomaszowski", 4600, 4440, "ETM", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region RAWA_MAZ = new Region("rawski", 4800, 4260, "ERW", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    private final Region SKIERNIEWICE_MIASTO = new Region("skierniewicki - miasto", 4666, 4030, "ES", PAINTABLE, NOT_FORCED, PLAIN, PAINT_SHORTCUT_IN_MAP, UNLOCKED_COLOR);
    public final Region SKIERNIEWICE_POWIAT = new Region("skierniewicki - powiat", "ESK", UNLOCKED_COLOR, PAINT_SHORTCUT_IN_MAP, new Point[] {new Point(4570, 4110), new Point(4710, 4016)});


    private final Region KUTNO_LOW_RES = new Region("kutnowski", 951, 904, LOCKED_COLOR, "EKU ");
    private final Region LECZYCA_LOW_RES = new Region("łęczycki", 951, 856, LOCKED_COLOR, "ELE ");
    private final Region LOWICZ_LOW_RES = new Region("łowicki", 1070, 904, LOCKED_COLOR, "ELC ");
    private final Region ZGIERZ_LOW_RES = new Region("zgierski", 999, 963, LOCKED_COLOR, "EZG ");
    private final Region BRZEZINY_LOW_RES = new Region("brzeziński", 1058, 980, LOCKED_COLOR, "EBR ");
    private final Region PODDEBICE_LOW_RES = new Region("poddębicki", 904, 951, LOCKED_COLOR, "EPD ");
    private final Region PABIANICE_LOW_RES = new Region("pabianicki", 975, 1022, LOCKED_COLOR, "EPA ");
    private final Region LASK_LOW_RES = new Region("łaski", 951, 1070, LOCKED_COLOR, "ELA ");
    private final Region ZDUNSKA_LOW_RES = new Region("zduńskowolski", 913, 1046, LOCKED_COLOR, "EZD ");
    private final Region SIERADZ_LOW_RES = new Region("sieradzki", 856, 1022, LOCKED_COLOR, "ESI ");
    private final Region WIERUSZOW_LOW_RES = new Region("wieruszowski", 785, 1141, LOCKED_COLOR, "EWE ");
    private final Region WIELUN_LOW_RES = new Region("wieluński", 850, 1141, LOCKED_COLOR, "EWI ");
    private final Region PAJECZNO_LOW_RES = new Region("pajęczański", 923, 1189, LOCKED_COLOR, "EPJ ");
    private final Region BELCHATOW_LOW_RES = new Region("bełchatowski", 951, 1118, LOCKED_COLOR, "EBE ");
    private final Region LODZ_POWIAT_LOW_RES = new Region("łódzki - powiat", "ELW ", LOCKED_COLOR, !PAINT_SHORTCUT_IN_MAP, new Point(1022, 1034), new Point(1022, 989));
    private final Region LODZ_MIASTO_LOW_RES = new Region("łódzki - miasto", 999, 999, LOCKED_COLOR, "EL ");
    private final Region RADOMSKO_LOW_RES = new Region("radomszczański", 1022, 1189, LOCKED_COLOR, "ERA ");
    private final Region PIOTRKOW_MIASTO_LOW_RES = new Region("piotrkowski - miasto", 1034, 1103, LOCKED_COLOR, "EP ");
    private final Region PIOTRKOW_POWIAT_LOW_RES = new Region("piotrkowski - powiat", 1046, 1141, LOCKED_COLOR, "EPI ");
    private final Region OPOCZNO_LOW_RES = new Region("opoczański", 1118, 1118, LOCKED_COLOR, "EOP ");
    private final Region TOMASZOW_MAZ_LOW_RES = new Region("tomaszowski", 1094, 1022, LOCKED_COLOR, "ETM ");
    private final Region RAWA_MAZ_LOW_RES = new Region("rawski", 1141, 999, LOCKED_COLOR, "ERW ");
    private final Region SKIERNIEWICE_MIASTO_LOW_RES = new Region("skierniewicki - miasto", 1113, 951, LOCKED_COLOR, "ES ");
    private final Region SKIERNIEWICE_POWIAT_LOW_RES = new Region("skierniewicki - powiat", 1094, 951, LOCKED_COLOR, "ESK ");

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(951, 904),
                new Point(951, 856),
                new Point(1070, 904),
                new Point(999, 963),
                new Point(1058, 980),
                new Point(904, 951),
                new Point(975, 1022),
                new Point(951, 1070),
                new Point(913, 1046),
                new Point(856, 1022),
                new Point(785, 1141),
                new Point(850, 1141),
                new Point(923, 1189),
                new Point(951, 1118),
                new Point(1022, 1034),
                new Point(1022, 989),
                new Point(999, 999),
                new Point(1022, 1189),
                new Point(1034, 1103),
                new Point(1046, 1141),
                new Point(1118, 1118),
                new Point(1094, 1022),
                new Point(1141, 999),
                new Point(1113, 951),
                new Point(1094, 951)
        };
    }


    public final Collection<Region> LODZKIE_POWIATY = Arrays.asList(
            PIOTRKOW_MIASTO, SKIERNIEWICE_MIASTO, SKIERNIEWICE_POWIAT, PIOTRKOW_POWIAT, KUTNO,
            LECZYCA, LOWICZ, ZGIERZ, BRZEZINY, PODDEBICE,
            PABIANICE, LASK, SIERADZ, ZDUNSKA, WIERUSZOW,
            WIELUN, PAJECZNO, BELCHATOW, LODZ_POWIAT,
            RADOMSKO, OPOCZNO, RAWA_MAZ, TOMASZOW_MAZ, LODZ_MIASTO);

    public final Collection<Region> LODZKIE_POWIATY_LOW_RES = Arrays.asList(
            PIOTRKOW_MIASTO_LOW_RES, SKIERNIEWICE_MIASTO_LOW_RES, SKIERNIEWICE_POWIAT_LOW_RES, PIOTRKOW_POWIAT_LOW_RES, KUTNO_LOW_RES,
            LECZYCA_LOW_RES, LOWICZ_LOW_RES, ZGIERZ_LOW_RES, BRZEZINY_LOW_RES, PODDEBICE_LOW_RES,
            PABIANICE_LOW_RES, LASK_LOW_RES, SIERADZ_LOW_RES, ZDUNSKA_LOW_RES, WIERUSZOW_LOW_RES,
            WIELUN_LOW_RES, PAJECZNO_LOW_RES, BELCHATOW_LOW_RES, LODZ_POWIAT_LOW_RES,
            RADOMSKO_LOW_RES, OPOCZNO_LOW_RES, RAWA_MAZ_LOW_RES, TOMASZOW_MAZ_LOW_RES, LODZ_MIASTO_LOW_RES);

//    public static Point[] getCoordinates() {
//        Point[] result = new Point[LODZKIE_POWIATY_LOW_RES.size()+1];
//        int i = 0;
//        for (Region powiat : LODZKIE_POWIATY_LOW_RES) {
//            Point[] regionCoords = powiat.getCoordinates();
//            for (Point regionCoord : regionCoords) {
//                result[i++] = regionCoord;
//            }
//        }
//        return result;
//    }

    private static Lodzkie instance;

    public static Lodzkie getInstance() {
        if (instance == null) {
            instance = new Lodzkie();
        }
        return instance;
    }
}
