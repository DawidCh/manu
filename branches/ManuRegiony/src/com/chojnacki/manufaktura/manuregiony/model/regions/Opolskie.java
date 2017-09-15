package com.chojnacki.manufaktura.manuregiony.model.regions;

import com.chojnacki.manufaktura.manuregiony.model.Point;
import com.chojnacki.manufaktura.manuregiony.model.Region;

import java.util.Arrays;
import java.util.Collection;

/**
 * Opolskie region
 */
public class Opolskie {

    private final Region NAMYSLOWSKI = new Region("namysłowski", 704, 1205, false, "ONA");
    private final Region KLUCZBORSKI = new Region("kluczborski", 778, 1208, false, "OKL");
    private final Region OLESKI = new Region("oleski", 835, 1223, false, "OOL");
    private final Region BRZESKI = new Region("brzeski", 650, 1250, false, "OB");
    private final Region OPOLSKI = new Region("opolski", 736, 1299, false, "OPO");
    private final Region STRZELECKI = new Region("strzelecki", 800, 1350, false, "OST");
    private final Region PRUDNICKI = new Region("prudnicki", 693, 1383, false, "OPR");
    private final Region NYSKI = new Region("nyski", 630, 1350, false, "ONY");
    private final Region KEDZIRZYNSKO_KOZIELSKI = new Region("kędzierzyńsko_kozielski", 773, 1414, false, "OK");
    private final Region GLUBCZYCKI = new Region("głubczycki", 719, 1457, false, "OGL");

    public final Collection<Region> OPOLSKIE_POWIATY = Arrays.asList(NAMYSLOWSKI, KLUCZBORSKI, OLESKI, BRZESKI, OPOLSKI, STRZELECKI, PRUDNICKI, NYSKI, KEDZIRZYNSKO_KOZIELSKI, GLUBCZYCKI);

    public static Point[] getCoordinates() {
        return new Point[]{
                new Point(630, 1350),
                new Point(659, 1272),
                new Point(693, 1383),
                new Point(704, 1205),
                new Point(719, 1457),
                new Point(736, 1299),
                new Point(773, 1414),
                new Point(778, 1208),
                new Point(797, 1339),
                new Point(835, 1223)};
    }

    private static Opolskie instance;

    public static Opolskie getInstance() {
        if (instance == null) {
            instance = new Opolskie();
        }
        return instance;
    }
}
