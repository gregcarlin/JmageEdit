package en.jmageedit.model.filters;

import java.awt.image.BufferedImage;

public abstract class Filter {
    public enum Type {
        GREYSCALE(new Greyscale(), "Greyscale");
        
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
