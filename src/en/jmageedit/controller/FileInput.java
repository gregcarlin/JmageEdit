package en.jmageedit.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileInput {
    public static final BufferedImage readImage(String file) {
        return readImage(new File(file));
    }
    
    /**
     * Attempts to read an image. Returns null if unreadable.
     * 
     * @param file
     * @return
     */
    public static final BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            return null;
        }
    }
}
