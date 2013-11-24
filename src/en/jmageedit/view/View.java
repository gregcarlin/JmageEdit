package en.jmageedit.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import en.jmageedit.model.Model;
import en.jmageedit.model.filters.Filter;

public class View {
    private static final int START_WIDTH = 500;
    private static final int START_HEIGHT = 250;
    private final JFrame imageFrame = new JFrame();
    private final JLabel imageLbl = new JLabel();
    private BufferedImage currentImage;
    
    private final JLabel preImageLbl = new JLabel("Open an image above.");
    
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
        
        JMenuItem scale = new JMenuItem("Change Size");
        scale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                View.this.model.getGUIHooks().onScale();
            }});
        edit.add(scale);
        
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
        preImageLbl.setBounds(START_WIDTH / 2 - 70, START_HEIGHT / 2 - 30, 200, 50);
        imageFrame.add(preImageLbl);
        
        imageLbl.setVisible(true);
        imageFrame.add(imageLbl);
        
        imageFrame.setResizable(false);
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageFrame.setVisible(true);
    }
    
    public void setImage(BufferedImage img) {
        currentImage = img;
        
        if(img == null) {
            preImageLbl.setVisible(true);
            imageFrame.setBounds(imageFrame.getX(), imageFrame.getY(), START_WIDTH, START_HEIGHT);
            return;
        }
        
        preImageLbl.setVisible(false);
        
        int width = img.getWidth();
        int height = img.getHeight();
        
        imageLbl.setIcon(new ImageIcon(img));
        imageLbl.setBounds(0, 0, width, height);
        
        imageFrame.setBounds(imageFrame.getX(), imageFrame.getY(), width, height);
        
        if(!imageLbl.isVisible()) imageLbl.setVisible(true);
    }
    
    /**
     * Asks the user to choose a file. Returns null if no file selected.
     * 
     * @return
     */
    public File showFileChooser() {
        JFileChooser fc = new JFileChooser();
        int choice = fc.showOpenDialog(imageFrame);
        return choice == JFileChooser.APPROVE_OPTION ? fc.getSelectedFile() : null;
    }
    
    /**
     * Shows an error message.
     * 
     * @param message
     */
    public void error(String message) {
        JOptionPane.showMessageDialog(imageFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Ask the user a question.
     * 
     * @param question
     * @return Answer
     */
    public String ask(String question) {
        return JOptionPane.showInputDialog(imageFrame, question);
    }
    
    public BufferedImage getCurrentImage() {
        return currentImage;
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
