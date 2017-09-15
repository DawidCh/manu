package com.chojnacki.manufaktura.manuregiony.processing;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Properties;

import com.chojnacki.manufaktura.manuregiony.exceptions.ImageNotFoundException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;

/**
 * Utility class used for loading dynamically resources
 */
public class ResourceLoader {

    private static final Logger LOGGER = LogManager.getLogger(ResourceLoader.class);
    private static Properties prop;

    static {
        prop = loadProperties("config.properties");
    }

    private ResourceLoader() {
    }

    public static String getResourceFromExternalFile(String key, String defaultValue, String fileName) {
        String result;
        Properties prop = loadProperties(fileName);
        result = prop.getProperty(key);
        LOGGER.log(Level.DEBUG, "Retrieved {} = {}", key, result);
        return result == null ? defaultValue : result;
    }

    public static String getResourceFromConfigFile(String key, String defaultValue) {
        String result;
        result = prop.getProperty(key);
        LOGGER.log(Level.DEBUG, "Retrieved {} = {}", key, result);
        return result == null ? defaultValue : result;
    }

    public static Properties loadProperties(String fileName) {
        File file = new File(fileName);
        InputStream input;
        try {
            LOGGER.log(Level.DEBUG, "Trying to load properties from external file {}", file.getAbsolutePath());
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            input = ClassLoader.getSystemResourceAsStream(fileName);
            LOGGER.log(Level.DEBUG, "Loading properties from external file failed. Using internal resources");
        }
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public static BufferedImage loadImage(String imageName) throws IOException {
        URL imageURL = ClassLoader.getSystemResource(imageName);
        if (imageURL == null) {
            throw new ImageNotFoundException("Image {} has not been found.");
        }
        LOGGER.log(Level.DEBUG,
                "Loading image file from location: {}" + imageURL.toString());
        return ImageIO.read(imageURL);
    }
}
