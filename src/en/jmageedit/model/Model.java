package en.jmageedit.model;

import en.jmageedit.view.View;

public class Model {
    private final View view = new View(this);
    private final GUIHooks guiHooks = new GUIHooks();
    
    public Model() {
        
    }
    
    public GUIHooks getGUIHooks() {
        return guiHooks;
    }
}
