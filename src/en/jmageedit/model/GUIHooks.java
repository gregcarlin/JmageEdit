package en.jmageedit.model;

import java.awt.image.BufferedImage;
import java.io.File;

import en.jmageedit.controller.FileInput;
import en.jmageedit.model.filters.Filter;
import en.jmageedit.view.View;

public class GUIHooks {
    private final View view;
    
    public GUIHooks(View view) {
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
}
