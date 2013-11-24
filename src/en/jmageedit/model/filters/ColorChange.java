package en.jmageedit.model.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorChange extends Filter {

    @Override
    public BufferedImage filter(BufferedImage img) {
        int red = view.askInt("How much red?");
        int green = view.askInt("How much green?");
        int blue = view.askInt("How much blue?");
        int a = view.askInt("How opaque?");
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        BufferedImage bi = new BufferedImage(w, h, img.getType());
        int[] pixels = new int[w * h];
        pixels = bi.getRGB(0, 0, w, h, pixels, 0, w);
        for(int i=0; i<pixels.length; i++) {
            Color c = new Color(pixels[i]);
            Color cc = new Color(c.getRed() + red * a, c.getGreen() + green * a, c.getBlue() + blue * a);
            pixels[i] = cc.getRGB();
        }
        bi.setRGB(0, 0, w, h, pixels, 0, w);
        
        return bi;
    }
}
