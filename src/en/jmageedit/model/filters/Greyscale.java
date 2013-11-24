package en.jmageedit.model.filters;

import java.awt.*;
import java.awt.image.*;

import javax.swing.ImageIcon;

public class Greyscale extends Filter {

    @Override
    public BufferedImage filter(BufferedImage img) {
    	   
    	int width = img.getWidth();
    	int height = img.getHeight();
    	int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
    	
    	for(int x = 0; x < pixels.length; x++) {
    		Color c = new Color(pixels[x]);
    		int total = 0;
    		total += c.getRed();
    		total += c.getBlue();
    		total += c.getGreen();
    		total = total / 3;
    		Color nColor = new Color(total, total, total);
    		pixels[x] = nColor.getRGB();
    	}
    	
    	img.setRGB(0, 0, width, height, pixels, 0, width);
    	
    	return img;
    }
    

}
