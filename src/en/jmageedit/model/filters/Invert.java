package en.jmageedit.model.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Invert extends Filter {

    @Override
    public BufferedImage filter(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        
        int[] pixels = new int[width * height];
        pixels = img.getRGB(0, 0, width, height, pixels, 0, width);
        for(int i=0; i<pixels.length; i++) {
            Color c = new Color(pixels[i]);
            Color cc = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
            pixels[i] = cc.getRGB();
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);
        return img;
    }
}
