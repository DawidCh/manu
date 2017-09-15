package com.chojnacki.manufaktura.pixelfinder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChojnackiD on 2016-03-15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File(args[0]));
        int width = image.getWidth();
        int height = image.getHeight();
        List<Point> grayPixels = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(i, j);
                if (makeARGB(159, 159, 159) == rgb) {
                    Point point = new Point(i, j);
                    grayPixels.add(point);
                    System.out.print(point.toString() +",");
                }
            }
        }
        System.out.println("\nFound: " + grayPixels.size());
    }

    private static int makeARGB(int red, int green, int blue) {
        return 255 << 24 | red << 16 | green << 8 | blue;
    }
}
