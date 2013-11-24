package en.jmageedit.model;

import en.jmageedit.model.filters.Filter;
import en.jmageedit.view.View;

public class Model {
    private final View view = new View(this);
    private final GUIHooks guiHooks = new GUIHooks(view, this);
    
    public Model() {
        Filter.view = view;
    }
    
    public GUIHooks getGUIHooks() {
        return guiHooks;
    }
}
