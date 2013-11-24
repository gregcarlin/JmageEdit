package en.jmageedit.model.filters;

import java.awt.image.BufferedImage;

import en.jmageedit.view.View;

public abstract class Filter {
    public static View view;
    
    public enum Type {
        GREYSCALE(new Greyscale(), "Greyscale"),
        MATRIX(new Matrix(), "Matrix"),
        INVERT(new Invert(), "Invert");
        
        private final Filter filter;
        private final String name;
        
        private Type(Filter filter, String name) {
            this.filter = filter;
            this.name = name;
        }
        
        public BufferedImage filter(BufferedImage img) {
            return filter.filter(img);
        }
        
        public String getName() {
            return name;
        }
    }
    
    public abstract BufferedImage filter(BufferedImage img);
}
