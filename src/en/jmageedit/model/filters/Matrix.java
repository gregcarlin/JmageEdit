package en.jmageedit.model.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Matrix extends Filter {
    private static final int GREEN_RGB = Color.GREEN.getRGB();
    private static final int BLACK_RGB = Color.BLACK.getRGB();
    private static final int[] ZERO = {GREEN_RGB, GREEN_RGB, GREEN_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, GREEN_RGB, GREEN_RGB, GREEN_RGB}; // 4 x 6
    private static final int[] ONE  = {BLACK_RGB, GREEN_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB}; // 4 x 6

    @Override
    public BufferedImage filter(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        
        BufferedImage outImage = new BufferedImage(w, h, img.getType());
    }
}
