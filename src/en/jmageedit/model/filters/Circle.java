package en.jmageedit.model.filters;

import java.awt.image.BufferedImage;

import com.jhlabs.image.CircleFilter;

public class Circle extends Filter {
    private final CircleFilter cf = new CircleFilter();
    
    @Override
    public BufferedImage filter(BufferedImage img) {
        BufferedImage bi = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        bi = cf.filter(img, bi);
        return bi;
    }
}
