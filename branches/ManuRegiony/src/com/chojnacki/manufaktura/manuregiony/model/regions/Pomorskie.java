package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Class which contains pomorskie region
 */
public class Pomorskie {

    private static Pomorskie instance;
    public Region SLUPSKI = new Region("słupski", "GSL", "pl", new Point(591, 283));
    public Region SLUPSKI_MIASTO = new Region("słupski - miasto", "GS", "pl", new Point(606, 273));
    public Region LEBORSKI = new Region("lęborski", "GLE", "pl", new Point(716, 239));
    public Region WEJHEROWSKI = new Region("wejherowski", "GWE", "pl", new Point(761, 233));
    public Region PUCKI = new Region("pucki", "GPU", "pl", new Point(803, 204));
    public Region BYTOWSKI = new Region("bytowski", "GBY", "pl", new Point(656, 364));
    public Region KARTUSKI = new Region("kartuski", "GKA", "pl", new Point(768, 318));
    public Region KOSCIEZYNA = new Region("kościeżyna", "GKS", "pl", new Point(759, 390));
    public Region GDYNSKI_MIASTO = new Region("gdyński - miasto", "GA", "pl", new Point(841, 263));
    public Region SOPOT_MIASTO = new Region("sopocki - miasto", "GSP", "pl", new Point(849, 285));
    public Region GDANSKI_MIASTO = new Region("gdański - miasto", "GD", "pl", new Point(849, 300));
    public Region GDANSKI = new Region("gdański", "GDA", "pl", new Point(856, 346));
    public Region NOWODWORSKI = new Region("nowodworski", "GND", "pl", new Point(935, 333));
    public Region CZLUCHOWSKI = new Region("czuchowski", "GCZ", "pl", new Point(627, 470));
    public Region CHOJNICKI = new Region("chojnicki", "GCH", "pl", new Point(695, 455));
    public Region STAROGARDZKI = new Region("starogardzki", "GST", "pl", new Point(840, 440));
    public Region TCZEWSKI = new Region("tczewski", "GTC", "pl", new Point(880, 424));
    public Region MALBORSKI = new Region("malborski", "GMB", "pl", new Point(928, 381));
    public Region SZTUMSKI = new Region("sztumski", "GSZ", "pl", new Point(951, 422));
    public Region KWIDZYNSKI = new Region("kwidzyńsi", "GKW", "pl", new Point(925, 475));

    public final Collection<Region> POMORSKIE_POWIATY = new HashSet<>(Arrays.asList(SLUPSKI, SLUPSKI_MIASTO, LEBORSKI, WEJHEROWSKI, PUCKI, BYTOWSKI, KARTUSKI, KOSCIEZYNA,
            GDYNSKI_MIASTO, SOPOT_MIASTO, GDANSKI, GDANSKI_MIASTO, NOWODWORSKI, CZLUCHOWSKI, CHOJNICKI, STAROGARDZKI, TCZEWSKI, MALBORSKI, SZTUMSKI, KWIDZYNSKI));

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(591, 283),
                new Point(606, 273),
                new Point(627, 470),
                new Point(656, 364),
                new Point(695, 455),
                new Point(716, 239),
                new Point(759, 390),
                new Point(761, 233),
                new Point(768, 318),
                new Point(803, 204),
                new Point(849, 285),
                new Point(849, 300),
                new Point(841, 263),
                new Point(849, 285),
                new Point(840, 440),
                new Point(856, 346),
                new Point(880, 424),
                new Point(925, 475),
                new Point(928, 381),
                new Point(935, 333),
                new Point(951, 422)};
    }

    public static Pomorskie getInstance() {
        if (instance == null) {
            instance = new Pomorskie();
        }
        return instance;
    }
}
