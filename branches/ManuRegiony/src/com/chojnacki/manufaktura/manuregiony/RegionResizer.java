package com.chojnacki.manufaktura.manuregiony;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for resizing all the region definitions by devider.
 */
public class RegionResizer {

    private static final float DEVIDER = 4.205729167f;

    public static void main(String... args) throws IOException {
        File folder = new File("C:\\Users\\chojnackid\\IdeaProjects\\kalosh_manuliczek\\branches\\ManuRegiony\\src\\com\\chojnacki\\manufaktura\\manuregiony\\model\\regions");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().contains("Lubelskie"))
            parseFile(file);
        }
    }

    public static void parseFile(File region) throws IOException {
        String backupPath = region.getAbsolutePath() + "_backup";
        File backupFile = new File(backupPath);
        region.renameTo(backupFile);
        BufferedReader br = new BufferedReader(new FileReader(backupFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(region));
        String line;
        while((line = br.readLine()) != null) {
            Pattern pattern = Pattern.compile("\\d{3,4}, \\d{3,4}");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String strCoords[] = matcher.group().split(", ");
                String resultCoords[] = new String[2];
                resultCoords[0] = Integer.toString(Math.round(Float.parseFloat(strCoords[0]) / DEVIDER));
                resultCoords[1] = Integer.toString(Math.round(Float.parseFloat(strCoords[1]) / DEVIDER));
                line = line.replaceAll(strCoords[0], resultCoords[0]);
                line = line.replaceAll(strCoords[1], resultCoords[1]);
            }
            bw.write(line); bw.newLine();
        }
        bw.close();
    }
}
