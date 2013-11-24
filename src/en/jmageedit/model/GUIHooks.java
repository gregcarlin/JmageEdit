package en.jmageedit.model;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import en.jmageedit.controller.FileInput;
import en.jmageedit.model.filters.Filter;
import en.jmageedit.view.View;

public class GUIHooks {
    private final View view;
    
    public GUIHooks(View view, Model model) {
        this.view = view;
    }
    
    /**
     * Called when user clicks File->Open
     */
    public void onOpen() {
        File f = view.showFileChooser();
        if(f == null) return;
        BufferedImage bi = FileInput.readImage(f);
        if(bi == null) {
            view.error("Error reading image file.");
        } else {
            view.addRecent(f);
            view.setImage(bi);
        }
    }
    
    /**
     * Called when user clicks Filter->(type)
     * 
     * @param type Type of filter requested
     */
    public void onFilter(Filter.Type type) {
        BufferedImage bi = view.getCurrentImage();
        if(bi == null) {
            view.error("No image currently open.");
            return;
        }
        
        view.setImage(type.filter(bi));
    }
    
    /**
     * Called when user clicks Edit->Change Size
     */
    public void onScale() {
        BufferedImage bi = view.getCurrentImage();
        if(bi == null) {
            view.error("No image currently open.");
            return;
        }
        
        int w = view.askInt("New width?");
        if(w < 0) return;
        int h = view.askInt("New height?");
        if(h < 0) return;
        
        // http://www.mkyong.com/java/how-to-resize-an-image-in-java/
        BufferedImage resizedImage = new BufferedImage(w, h, bi.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bi, 0, 0, w, h, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        view.setImage(resizedImage);
    }
}
