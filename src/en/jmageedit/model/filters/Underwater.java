package en.jmageedit.model.filters;

import java.awt.image.BufferedImage;

import com.jhlabs.image.SwimFilter;

public class Underwater extends Filter {
    private final SwimFilter sf = new SwimFilter();
    
    @Override
    public BufferedImage filter(BufferedImage img) {
        BufferedImage bi = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        sf.setAmount(50);
        sf.setTime(50);
        sf.setStretch(10);
        sf.setAngle(45);
        bi = sf.filter(img, bi);
        return bi;
    }
}
