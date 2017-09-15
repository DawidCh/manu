package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Kujawsko-pomorskie region
 */
public class KujawskoPomorskie {

    private final Region SEPOLENSKI = new Region("sępoleński", 684, 559, false, "CSP");
    private final Region NAKIELSKI = new Region("nakielski", 685, 634, false, "CKL");
    private final Region ZNINSKI = new Region("żniński", 708, 723, false, "CZN");
    private final Region TUCHOLSKI = new Region("tucholski", 744, 519, false, "CTU");
    private final Region BYDGOSKI = new Region("bydgoski", 751, 615, false, "CB");
    private final Region MOGILENSKI = new Region("mogileński", 758, 774, false, "CMO");
    private final Region INOWROCLAWSKI = new Region("inowrocławski", 803, 727, false, "CIN");
    private final Region SWIECKI = new Region("świecki", 828, 530, false, "CSW");
    private final Region CHELMISKI = new Region("chełmiński", 839, 594, false, "CCH");
    private final Region RADZIEJOWSKI = new Region("radziejowski", 856, 781, false, "CRA");
    private final Region TORUNSKI = new Region("toruński", 869, 674, false, "CT");
    private final Region GRUDZIADZKI_MIASTO = new Region("grudziądzki - miasto", 882, 545, false, "CG");
    private final Region ALEKSANDROWSKI = new Region("aleksandrowski", 882, 726, false, "CAL");
    private final Region WABRZESKI = new Region("wąbrzeski", 907, 600, false, "CWA");
    private final Region GRUDZIADZKI = new Region("grudziądzki", 914, 544, false, "CGR");
    private final Region LIPNOWSKI = new Region("lipnowski", 960, 720, false, "CLI");
    private final Region GOLUBSKO_DOBRZYNSKI = new Region("golubsko - dobrzyński", 925, 647, false, "CGD");
    private final Region BRODNICKI = new Region("brodnicki", 969, 587, false, "CBR");
    private final Region RYPINSKI = new Region("rypiński", 984, 665, false, "CRY");
    private final Region WLOCLAWSKI = new Region("włocławski", 950, 800, false, "CW", "CWL");


    public final Collection<Region> KUJAWSKO_POM_POWIATY = Arrays.asList(SEPOLENSKI, NAKIELSKI, ZNINSKI, TUCHOLSKI, BYDGOSKI, MOGILENSKI, INOWROCLAWSKI, SWIECKI,
            CHELMISKI, RADZIEJOWSKI, TORUNSKI, GRUDZIADZKI, ALEKSANDROWSKI, WABRZESKI, LIPNOWSKI, GOLUBSKO_DOBRZYNSKI, BRODNICKI, RYPINSKI, GRUDZIADZKI_MIASTO, WLOCLAWSKI);

    private static KujawskoPomorskie instance;

    public static KujawskoPomorskie getInstance() {
        if (instance == null) {
            instance = new KujawskoPomorskie();
        }
        return instance;
    }
    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(684, 559),
                new Point(685, 634),
                new Point(708, 723),
                new Point(719, 710),
                new Point(744, 519),
                new Point(751, 615),
                new Point(758, 774),
                new Point(758, 774),
                new Point(803, 727),
                new Point(804, 710),
                new Point(828, 530),
                new Point(839, 594),
                new Point(856, 781),
                new Point(869, 674),
                new Point(882, 545),
                new Point(882, 726),
                new Point(907, 600),
                new Point(914, 544),
                new Point(923, 793),
                new Point(925, 647),
                new Point(964, 722),
                new Point(969, 587),
                new Point(984, 665)};
    }
}
