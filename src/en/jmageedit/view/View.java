package en.jmageedit.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import en.jmageedit.model.Model;
import en.jmageedit.model.filters.Filter;

public class View {
    private static final int START_WIDTH = 500;
    private static final int START_HEIGHT = 250;
    private final JFrame imageFrame = new JFrame();
    private final JLabel imageLbl = new JLabel();
    
    private final JMenuBar menuBar = new JMenuBar();
    
    private final Model model;
    
    public View(Model model) {
        this.model = model;
        
        JMenu file = new JMenu("File");
        
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                View.this.model.getGUIHooks().onOpen();
            }});
        file.add(open);
        
        menuBar.add(file);
        
        JMenu edit = new JMenu("Edit");
        
        // TODO edit stuff
        
        menuBar.add(edit);
        
        JMenu filter = new JMenu("Filter");
        
        for(Filter.Type f : Filter.Type.values()) {
            JMenuItem jmi = new JMenuItem(f.getName());
            final Filter.Type ff = f; // cheating
            jmi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    View.this.model.getGUIHooks().onFilter(ff);
                }});
            filter.add(jmi);
        }
        
        menuBar.add(filter);
        
        imageFrame.setJMenuBar(menuBar);
        
        imageFrame.setTitle("JmageEdit");
        imageFrame.setBounds(getCentered(START_WIDTH, START_HEIGHT));
        imageFrame.setLayout(null); // very bad. do not do.
        JLabel lbl = new JLabel("Open an image above.");
        lbl.setBounds(START_WIDTH / 2 - 70, START_HEIGHT / 2 - 30, 200, 50);
        imageFrame.add(lbl);
        
        imageLbl.setVisible(true);
        imageFrame.add(imageLbl);
        
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageFrame.setVisible(true);
    }
    
    public void setImage(BufferedImage img) {
        if(!imageLbl.isVisible()) imageLbl.setVisible(true);
        
        int width = img.getWidth();
        int height = img.getHeight();
        
        imageLbl.setBounds(0, 0, width, height);
        imageLbl.setIcon(new ImageIcon(img));
    }
    
    /**
     * Given bounds of window, returns rectangle with those bounds centered on the screen.
     * 
     * @param width
     * @param height
     * @return
     */
    private static final Rectangle getCentered(int width, int height) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return new Rectangle(d.width / 2 - width / 2, d.height / 2 - height / 2, width, height);
    }
}
